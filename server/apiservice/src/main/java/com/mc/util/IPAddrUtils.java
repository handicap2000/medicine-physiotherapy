package com.mc.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPAddrUtils {
    public static String getIpAddress() {
        try {
//        从网卡中获取IP
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = allNetInterfaces.nextElement();
//                用于排除回送接口，非虚拟网卡，未在使用中的网络接口
                if (!networkInterface.isLoopback() && !networkInterface.isVirtual() && networkInterface.isUp()) {
//                    返回和网络接口绑定的所有IP地址
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip instanceof InetAddress) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("IP地址获取失败" + e.toString());
            ;
        }
        return null;
    }
}
