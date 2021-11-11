package com.gawds.db.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class EchoServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new EchoServerHandler());
    }
}
