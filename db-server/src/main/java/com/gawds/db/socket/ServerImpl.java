package com.gawds.db.socket;

import com.gawds.db.handler.EchoServerInitializer;
import com.google.inject.name.Named;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ServerImpl implements Server {

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private static final int MAX_NUMBER_QUEUED = 128;

    private static final Logger LOG = LoggerFactory.getLogger(ServerImpl.class);

    public ServerImpl(@Named("port") Integer port) {
        this.port = port;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    @Override
    public void start() throws InterruptedException {
        var serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(this.bossGroup, this.workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(this.port))
                .childHandler(new EchoServerInitializer())
                .option(ChannelOption.SO_BACKLOG, MAX_NUMBER_QUEUED)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                LOG.info("Closing server without errors");
            } else {
                LOG.error("Closing server with errors");
            }
        });

        channelFuture.channel().closeFuture().sync();
    }

    @Override
    public void close() {
        LOG.info("Closing Server!!!");
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
