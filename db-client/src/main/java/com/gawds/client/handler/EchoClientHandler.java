package com.gawds.client.handler;

import com.gawds.exception.GawdsAddressException;
import com.gawds.utils.Network;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.SocketAddress;


@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger LOG = LogManager.getLogger();

    private int idx = 0;

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                LOG.info("Message send Ok");
            } else {
                LOG.error("Error sending the message", future.cause());
                future.channel()
                        .close();
            }
        }
    };

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws GawdsAddressException {  // when connection ready
        final SocketAddress socketAddress = ctx.channel().remoteAddress();
        LOG.info("Connection established into {}. Ready to send the msg to the Server", Network.getIP(socketAddress));
        ctx.writeAndFlush(Unpooled.copiedBuffer("This is a test", CharsetUtil.UTF_8))
                .addListener(trafficGenerator);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        LOG.info("Client received: {}", msg.toString(CharsetUtil.UTF_8));
        ctx.write(Unpooled.copiedBuffer("More data: " + idx++, CharsetUtil.UTF_8))
                .addListener(trafficGenerator);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Exception in the client", cause);
        ctx.close();
    }
}
