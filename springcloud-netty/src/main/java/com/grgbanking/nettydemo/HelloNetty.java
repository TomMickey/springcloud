package com.grgbanking.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloNetty {

    //端口
    private static int port = 9999;

    public static void main(String[] args) throws InterruptedException {
        //负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责处理客户端的请求
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new MyServerInit());
        ChannelFuture future = bootstrap.bind(port).sync().channel().closeFuture().sync();
    }
}
