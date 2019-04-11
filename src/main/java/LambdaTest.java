
public class LambdaTest {
    public static void printString(String s,Print<String> printObj){
        printObj.print(s);
    }
    //调用
    public static void main(String...args){
        printString("hello",(x)-> System.out.println(x));
    }
}

interface Print<T>{
    void print(T x);
}