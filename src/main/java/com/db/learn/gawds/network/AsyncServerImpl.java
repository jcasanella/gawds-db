package com.db.learn.gawds.network;

import com.google.inject.name.Named;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;

public class AsyncServerImpl implements Server, Closeable {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private int port;

    private static final Logger LOG = LogManager.getLogger();

//    public AsyncServerImpl() {
//
//    }

    public AsyncServerImpl(@Named("port") Integer port) {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        this.port = port;
    }

    @Override
    public void start() throws InterruptedException {
        var serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new SimpleTcpChannelInitializer())
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            if (channelFuture.isSuccess()) {
                LOG.info("Connection success!!!");
            }

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            LOG.warn("Error starting the server!!!");
            throw ex;
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        LOG.info("Closing Server!!!");
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
