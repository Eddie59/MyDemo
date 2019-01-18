package cn.javaio.nio;

import org.junit.Test;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * ServerSocketChannelTest class
 *
 * @author Administrator
 * @date
 */
public class ServerSocketChannelTest {
    private static final int SIZE = 1024;

    public static void server() {
        //创建ServerSocketChannel对象
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            //是否是阻塞模式
            serverSocketChannel.configureBlocking(false);
            //绑定监听的端口号
            serverSocketChannel.bind(new InetSocketAddress(9801));

            ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
            while (true) {
                //方法监听到有新的连接时,客户端传来的数据都在channel中
                //非阻塞模式，如果没有新的连接，返回null；阻塞模式，会一直阻塞到有新连接
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                    //有连接进来，输出连接信息
                    InetSocketAddress remoteAddress = (InetSocketAddress)channel.getRemoteAddress();
                    System.out.println("service:" + remoteAddress.getAddress());
                    System.out.println("port:" + remoteAddress.getPort());
                    //从channel读取"客户端写入channel"的数据到byteBuffer
                    channel.read(byteBuffer);
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        System.out.println("byteBuffer:" + (char) byteBuffer.get());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void client(String serverName) {
        //打开一个SocketChannel并连接到互联网上的某台服务器
        try (SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9801));

            //连接上以后，向服务器发送数据
            ByteBuffer byteBuffer=ByteBuffer.allocate(SIZE);
            byteBuffer.put(new String("我是"+serverName).getBytes());
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
//                数据从buffer存到channel
                socketChannel.write(byteBuffer);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String... args) throws Exception {
        try {
            new Thread(() -> server()).start();
            Thread.sleep(10000);
            new Thread(() -> client("server1")).start();
            new Thread(() -> client("server2")).start();
        } catch (Exception e) {

        }

    }

}
