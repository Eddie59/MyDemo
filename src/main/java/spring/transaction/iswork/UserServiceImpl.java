package spring.transaction.iswork;

public class UserServiceImpl implements UserService {
    @Override
    public void txMethod() {
        txMethod2();
    }

    @Override
    public void txMethod2() {

    }

    @Override
    public void noTxMethod() {
        txMethod2();
    }
}
