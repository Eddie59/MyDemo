package cn.strategy;

/**
 * SubstractOperation class
 *
 * @author Administrator
 * @date
 */
public class SubstractOperation  implements IOperation{
    @Override
    public int doOperation(int i, int j) {
        return i-j;
    }
}
