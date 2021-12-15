package com.gawds.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.SocketAddress;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.Inet6Address;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  // when connection ready
        LOG.info("Connection established into {}. Ready to send the msg to the Server", getIP(ctx));
        ctx.writeAndFlush(Unpooled.copiedBuffer("This is a test", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        LOG.info("Client received: {}", msg.toString(CharsetUtil.UTF_8));
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOG.error("Exception in the client", cause);
        ctx.close();
    }

    private String getIP(final ChannelHandlerContext ctx) throws Exception {
        final SocketAddress socketAddress = ctx.channel().remoteAddress();
        if (socketAddress instanceof InetSocketAddress) {
            InetAddress inetAddress = ((InetSocketAddress) socketAddress).getAddress();
            if (inetAddress instanceof Inet4Address) {
                return "IPv4: " + inetAddress;
            } else if (inetAddress instanceof Inet6Address) {
                return "IPv6: " + inetAddress;
            } else {
                throw new Exception("Not an IP address");
            }
        } else {
            throw new Exception("Not an internet protocol socket");
        }
    }
}
