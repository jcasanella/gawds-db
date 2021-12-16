package com.gawds.utils;

import com.gawds.exception.GawdsAddressException;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Network {
    public static String getIP(final SocketAddress socketAddress) throws GawdsAddressException {
        if (socketAddress instanceof InetSocketAddress) {
            InetAddress inetAddress = ((InetSocketAddress) socketAddress).getAddress();
            if (inetAddress instanceof Inet4Address) {
                return "IPv4: " + inetAddress;
            } else if (inetAddress instanceof Inet6Address) {
                return "IPv6: " + inetAddress;
            } else {
                throw new GawdsAddressException("Not an IP address");
            }
        } else {
            throw new GawdsAddressException("Not an internet protocol socket");
        }
    }
}
