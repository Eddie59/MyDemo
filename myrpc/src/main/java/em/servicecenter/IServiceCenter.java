package em.servicecenter;

import java.io.IOException;

public interface IServiceCenter {
    void start() throws IOException;

    void stop();

    int getPort();

    boolean isRunning();

    void register(Class serviceInterface, Class impl);
}
