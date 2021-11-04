package com.gawds.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MessageClientHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(final ChannelHandlerContext ctx, Object msg) {
        ByteBuf buffer = (ByteBuf) msg;

    }
}
