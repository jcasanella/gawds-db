package com.gawds.db.handler;

import com.gawds.utils.Network;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

// Indicates that the handler can be shared safety with multiple channels
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServerHandler.class);

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final SocketAddress socketAddress = ctx.channel().remoteAddress();
        LOG.info("Client connected from {}", Network.getIP(socketAddress));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        Try.of(() -> {
            LOG.info("Server received {}", buf.toString(CharsetUtil.UTF_8));

            // Writes the received msg
            ctx.write(Unpooled.copiedBuffer("Ok", CharsetUtil.UTF_8));
            return null;
        }).onSuccess(s -> LOG.info("Server send response"))
                .onFailure(f -> LOG.error("Error reading from the Channel"))
                .andFinally(() -> ReferenceCountUtil.release(msg));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // Flushes pending msg and closes the channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Error processing", cause);
        ctx.close();
    }
}
