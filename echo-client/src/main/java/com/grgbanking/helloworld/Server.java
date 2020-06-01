package com.grgbanking.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private int port;

    public Server(int port){
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();//处理客户端的链接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//进行网络通信
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)//绑定两个线程组
                    .channel(NioServerSocketChannel.class)//设置noi
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)//设置tcp缓冲区
                    .option(ChannelOption.SO_SNDBUF,32*1024)//设置发送数据缓冲大小
                    .option(ChannelOption.SO_RCVBUF,32*1024)//设置接收数据缓冲大小
                    .childOption(ChannelOption.SO_KEEPALIVE,true);//保持连接
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server(8379).run();
    }
}
