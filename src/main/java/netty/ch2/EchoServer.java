package netty.ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * EchoServer class
 *
 * @author Administrator
 * @date
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //事件处理，接受新连接读写数据等
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //ChannelInitializer类，一个新的连接被接受时，会创建一个子Handle，一个Handle包含一个Channelpipleline
                    //这个新创建的Handle就是initChannel方法的参数channel，方法里面为这个channel添加EchoServerHandler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(serverHandler);
                        }
                    });
            //线程阻塞，直到绑定完毕
            ChannelFuture future = b.bind().sync();
            //线程阻塞，直到channel关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            //channel关闭以后，就可以整个关闭了
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String... args) throws Exception {
        new EchoServer(2598).start();
    }

}
