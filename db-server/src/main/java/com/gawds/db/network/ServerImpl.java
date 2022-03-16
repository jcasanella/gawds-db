package com.gawds.db.network;

import com.gawds.db.config.ServerConfig;
import com.gawds.db.guice.annotations.ConfigAnnotation;
import com.gawds.db.handler.EchoServerInitializer;
import com.google.inject.Inject;
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

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerConfig serverConfig;
    private static final int MAX_NUMBER_QUEUED = 128;

    private static final Logger LOG = LoggerFactory.getLogger(ServerImpl.class);

    @Inject
    public ServerImpl(@ConfigAnnotation final ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    @Override
    public void start() throws InterruptedException {
        var port = this.serverConfig.getPort()
                .getOrElseThrow(() -> new RuntimeException("Server port not defined"));
        var serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(this.bossGroup, this.workerGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new EchoServerInitializer())
                .option(ChannelOption.SO_BACKLOG, MAX_NUMBER_QUEUED)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                LOG.info("Server starting in port {}", port);
            } else {
                LOG.error("Server can not start, errors in server");
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
