package netty.ch2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * EchoServerHandler class
 *
 * @author Administrator
 * @date
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("server received:" + in.toString(CharsetUtil.UTF_8));
        //write为异步方法，channelRead方法结束后，write可能还没有结束，所以不会释放ByteBuf
        context.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
