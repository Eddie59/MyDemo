package cn.javaio.objectinputstream.oneobj;

import java.io.*;

/**
 * ObjectStream class
 *
 * @author Administrator
 * @date
 */
public class ObjectStream {
    private TV t;
    private final static String OUTPUT="C:\\Users\\Administrator\\Desktop\\weiwei.txt";

    public ObjectStream(){
        t=new TV();
        t.setNice(true);
        t.setDescribe("very nice");
        t.setPrice(800);
        t.setWeigth(200.23);
        for(int i=0;i<10;i++){
            for(int j=0;j<4;j++)
            {
                t.list.get(i).add("["+(i+3)+","+j+"]");
            }
        }
    }

    /**
     * ObjectOutputStream 以16进制写入文件
     * @param tv
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void write(TV tv) throws FileNotFoundException, IOException {
        File file=new File(OUTPUT);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file,false));
        objectOutputStream.writeObject(tv);
        objectOutputStream.close();
        System.out.println("write over~!~~");
    }

    public void read(TV tv) throws FileNotFoundException, IOException, ClassNotFoundException{
        File file=new File(OUTPUT);
        ObjectInputStream inputStream =new ObjectInputStream(new FileInputStream(file));
        TV v=(TV) inputStream.readObject();
        System.out.println(v.getDescribe());
        System.out.println(v.getPrice());
        System.out.println(v.isNice());
        System.out.println(v.getWeigth());
        System.out.println(v.getList());
    }

    /**
     *  FileOutputStream  二进制写入
     * @throws Exception
     */
    public void writeChar() throws Exception{
        String output="C:\\Users\\Administrator\\Desktop\\ww.txt";
        String s="上来的飞机上开发是卡洛斯的房间里谁";
        File file=new File(output);
        FileOutputStream stream=new FileOutputStream(file);
        stream.write(s.getBytes());
        stream.close();
    }

    public void readChar()throws Exception{
        String output="C:\\Users\\Administrator\\Desktop\\ww.txt";
        File file=new File(output);
        FileInputStream stream=new FileInputStream(file);
        byte b[]=new byte[200];
        while(stream.read(b, 0, 200)!=-1){
            String string=new String(b);
            System.out.println(string);
        }
        stream.close();
    }

    public static void main(String[] args) throws Exception {
        ObjectStream stream=new ObjectStream();

        stream.write(stream.t);
        stream.read(stream.t);

		stream.writeChar();
		stream.readChar();
    }

}
