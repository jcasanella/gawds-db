package com.gawds.db.handler;

import com.gawds.utils.Network;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

// Indicates that the handler can be shared safety with multiple channels
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServerHandler.class);

    private final ChannelFutureListener trafficResponse = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                LOG.info("Response send Ok");
            } else {
                LOG.error("Error sending the response", future.cause());
                future.channel()
                        .close();
            }
        }
    };

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final SocketAddress socketAddress = ctx.channel().remoteAddress();
        LOG.info("Client connected from {}", Network.getIP(socketAddress));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        try {
            LOG.info("Server received {}", buf.toString(CharsetUtil.UTF_8));

            // Send the answer to Client
            ctx.write(Unpooled.copiedBuffer("Ok", CharsetUtil.UTF_8))
                    .addListener(trafficResponse);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        // Flushes pending msg and closes the channel
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Error processing", cause);
        ctx.close();
    }
}
