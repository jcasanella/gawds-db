package com.gawds.db.network;

import com.google.inject.name.Named;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.InetSocketAddress;

public class ServerImpl implements Server {

    private int port;
    private EventLoopGroup group;

    private static final Logger LOG = LogManager.getLogger();

    public ServerImpl(@Named("port") Integer port) {
        this.port = port;
        this.group = new NioEventLoopGroup();
    }

    @Override
    public void start() throws InterruptedException {
        var serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(this.group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(this.port))
                .childHandler(new EchoServerInitializer());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            if (channelFuture.isSuccess()) {
                LOG.info("Connection success!!!");
            }

            channelFuture.channel().closeFuture().sync();
        } finally {
            close();
        }
    }

    @Override
    public void close() {
        LOG.info("Closing Server!!!");
        group.shutdownGracefully();
    }
}
