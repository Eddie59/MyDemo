package em.servicecenter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServiceCenter implements IServiceCenter {

    private final HashMap<String,Class> serviceRegistry=new HashMap<>();
    private int port;
    private boolean isRunning=false;

    public ServiceCenter(int port){
        this.port=port;
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket=new ServerSocket();
        InetSocketAddress address = new InetSocketAddress(this.port);
        serverSocket.bind(address);
        Socket socket= serverSocket.accept();
    }

    @Override
    public void stop() {
        this.isRunning=false;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(),impl);
    }


    class ServiceTask implements Runnable{



        @Override
        public void run() {

        }
    }
}
