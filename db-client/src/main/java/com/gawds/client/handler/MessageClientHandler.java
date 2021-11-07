package com.gawds.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class MessageClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    private static final Logger LOG = LogManager.getLogger();

    public MessageClientHandler() {
        this.firstMessage = Unpooled.buffer(256);
        IntStream.range(0, firstMessage.capacity())
                .forEach(x -> this.firstMessage.writeByte((byte) x));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
        LOG.info("Client {} connected", ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        LOG.info("A new message is received");
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        LOG.info("No more messages");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
