package cn.strategy;

/**
 * Context class
 *
 * @author Administrator
 * @date
 */
public class Context {
    private IOperation operation;

    public Context(IOperation operation) {
        this.operation = operation;
    }

    public int doOperation(int i, int j) {
        return operation.doOperation(i, j);
    }
}
