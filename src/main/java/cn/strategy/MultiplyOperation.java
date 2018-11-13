package cn.strategy;

/**
 * MultiplyOperation class
 *
 * @author Administrator
 * @date
 */
public class MultiplyOperation implements IOperation {
    @Override
    public int doOperation(int i, int j) {
        return i * j;
    }
}
