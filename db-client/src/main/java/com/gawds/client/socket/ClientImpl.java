package com.gawds.client.socket;

import com.gawds.client.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

public class ClientImpl implements Client {

    private EventLoopGroup workerGroup;

    private static final Logger LOG = LogManager.getLogger();

    public ClientImpl() {
        this.workerGroup = new NioEventLoopGroup();
    }

    @Override
    public void start(String host, int port) throws InterruptedException {
        var bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });

        LOG.info("Client connecting");
        ChannelFuture cf = bootstrap.connect(host, port).sync();
        cf.channel().closeFuture().sync();
    }

    @Override
    public void close() {
        try {
            LOG.info("Client closing the EventLoopGroup");
            workerGroup.shutdownGracefully().sync();
        } catch(InterruptedException ex) {
            LOG.error("Exception closing the EventLoopGroup", ex);
        }
    }
}
