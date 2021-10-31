package com.gawds.db.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleTcpChannelHandler extends SimpleChannelInboundHandler<String> {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        LOG.info("Channel Active {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final String msg) {
        LOG.info("Message Received {} from {}", msg, ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush("Test!!!\n");
    }

    public void channelInactive(final ChannelHandlerContext ctx) {
        LOG.info("Channel Inactive {}", ctx.channel().remoteAddress());
    }
}
