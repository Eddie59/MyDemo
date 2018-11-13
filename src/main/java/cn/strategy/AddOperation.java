package cn.strategy;

/**
 * AddOperation class
 *
 * @author Administrator
 * @date
 */
public class AddOperation implements IOperation {
    @Override
    public int doOperation(int i, int j) {
        return i + j;
    }
}
