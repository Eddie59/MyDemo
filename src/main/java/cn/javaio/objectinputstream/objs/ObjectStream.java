package cn.javaio.objectinputstream.objs;

import java.io.*;

/**
 * ObjectStream class
 *
 * @author Administrator
 * @date
 */
public class ObjectStream {
    private TV t;
    private final static String OUTPUT="C:\\Users\\Administrator\\Desktop\\weiwei1.txt";

    public ObjectStream(){
        t=new TV();
        t.setNice(true);
        t.setDescribe("very nice~~");
        t.setPrice(800);
        t.setWeigth(200.23);
        for(int i=0;i<10;i++){
            for(int j=0;j<4;j++) {
                t.list.get(i).add("[" + (i + 3) + "," + j + "]");
            }
        }
    }

    public void write(TV tv) throws FileNotFoundException, IOException {
        File file=new File(OUTPUT);
        TV []data=new TV[2];
        data[0]=tv;
        data[1]=tv;
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file,false));
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
        System.out.println("write over~!~~");
    }

    public void read(TV tv) throws FileNotFoundException, IOException, ClassNotFoundException{
        File file=new File(OUTPUT);
        ObjectInputStream inputStream =new ObjectInputStream(new FileInputStream(file));
        TV []vv=(TV[])inputStream.readObject();
        for(TV v:vv){
            System.out.println(v.getDescribe());
            System.out.println(v.getPrice());
            System.out.println(v.isNice());
            System.out.println(v.getWeigth());
            System.out.println(v.getList());
        }
    }

    public static void main(String[] args) throws Exception {
        ObjectStream stream=new ObjectStream();
        stream.write(stream.t);
        stream.read(stream.t);

    }


}
