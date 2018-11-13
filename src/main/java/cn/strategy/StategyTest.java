package cn.strategy;

/**
 * StategyTest class
 *
 * @author Administrator
 * @date
 */
public class StategyTest {
    public static void main(String...args){
        IOperation operation=new AddOperation();
        Context context=new Context(operation);
        System.out.println(context.doOperation(1,2));

        operation=new SubstractOperation();
        context=new Context(operation);
        System.out.println(context.doOperation(1,2));

        operation=new MultiplyOperation();
        context=new Context(operation);
        System.out.println(context.doOperation(1,2));
    }
}
