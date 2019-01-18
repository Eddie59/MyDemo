package cn.javaio.nio;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannelTest class
 *
 * @author Administrator
 * @date
 */
public class FileChannelTest {


    @Test
    public void writeFile() throws Exception {
        RandomAccessFile accessFile = new RandomAccessFile("E://file1.txt", "rw");
        FileChannel fc = accessFile.getChannel();

        //每次把channel的position设置到文件最好，从最后位置开始写入，每次都append到文件最后
        fc.position(accessFile.length());
        ByteBuffer byteBuffer = ByteBuffer.wrap("abc             ".getBytes());
        printBuffer(byteBuffer);

        //write byteBuffer to channel
        fc.write(byteBuffer);
        printChannel(fc);
        printBuffer(byteBuffer);

        //执行clear方法，表示数据读完了，可以重新载入数据了，把limit指向capacity，表示最多可把buffer装满；然后把position指向0位，表示从buffer的开始位置装数据
        byteBuffer.clear();
        //从0处开始put数据到buffer
        byteBuffer.put(new String(",a good boy").getBytes());
        //调用flip方法时，意思是说，数据我准备好了，可以来读了，这时应该把limit的位置设置为第一个没数据的位置，因为后面就没有数据可读了；而从哪时开始读呢，当然从开始位置读起，所以把position位置指向0位；
        byteBuffer.flip();
        //从0处开始写到limit位置
        fc.write(byteBuffer);

        printChannel(fc);
        fc.close();
    }

    @Test
    public void readFile() throws Exception {
        File file = new File("E://file1.txt");

        try (FileInputStream inputStream = new FileInputStream(file);
             FileChannel fileChannel = inputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            int n = 0;
            //从fileChannel读数据到byteBuffer，每次读取5个byte
            while (fileChannel.read(byteBuffer) != -1) {
                //数据准备好了,可以读了
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    System.out.println((char) byteBuffer.get());
                }
                //数据使用过以后，把指针复位，表示可以从头开始写了
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTo() throws Exception {
        try {
            RandomAccessFile fromFile = new RandomAccessFile("E://file1.txt", "rw");
            FileChannel fromChannel = fromFile.getChannel();
            RandomAccessFile toFile = new RandomAccessFile("E://file3.txt", "rw");
            FileChannel toChannel = toFile.getChannel();

            long position = 0;
            long count = fromChannel.size();
            fromChannel.transferTo(position, count, toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  通过nio读取文件，并写入另一文件中
     *  使用了，从通道channel中读取数据，然后写入通道channel中
     */
    @Test
    public void run() throws Exception {
        Resource resource = new ClassPathResource("log4j.xml");
        File f = resource.getFile();
        //Channel是从传统流中获取的
        try (FileChannel inputChannel = new FileInputStream(f).getChannel();
             FileChannel outChannel = new FileOutputStream("out.txt").getChannel()) {
            System.out.println(inputChannel.position() + "  " + outChannel.position());

            //把channel中的数据映射到buffer中,因为FileInputStream得到的FileChannel是只读的，所以模式为READ_ONLY
            MappedByteBuffer bufferRead = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
            System.out.println(bufferRead.position() + "  " + bufferRead.limit() + "  " + bufferRead.capacity());
            System.out.println(inputChannel.position() + "  " + outChannel.position());

            //这个不是把数据写入buffer，而是把buffer里面的数据通过Channel输出，所以outChannel的position指针会移动
            outChannel.write(bufferRead);
            System.out.println(bufferRead.position() + "  " + bufferRead.limit() + "  " + bufferRead.capacity());
            System.out.println(inputChannel.position() + "  " + outChannel.position());


        } catch (Exception exp) {
            System.out.println(exp);
        }
    }

    private void printBuffer(Buffer buffer) {
        System.out.println("position:" + buffer.position() + " " + " limit:" + buffer.limit() + " capacity:" + buffer.capacity());
    }

    private void printChannel(FileChannel channel) throws Exception {
        System.out.println("position:" + channel.position() + " ");
    }

}
