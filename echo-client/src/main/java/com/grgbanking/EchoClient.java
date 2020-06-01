package com.grgbanking;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;

    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start()throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();//1.创建Bootstrap
            b.group(group)//2.指定EventLoopGroup来处理客户端事件，由于我们NIO传输，所以用到了NioEventLoopGroup的实现
                    .channel(NioSocketChannel.class)//3.使用的Channel类型是一个用于NIO传输，也可以使用和服务器不一样的类型
                    .remoteAddress(new InetSocketAddress(host, port))//4.设置服务器的InetSocketAddress
                    .handler(new ChannelInitializer<SocketChannel>() {//5.当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channelpipeline
                        @Override
                        public void initChannel(SocketChannel ch)throws Exception{
                            ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();//6.连接到远程，等待连接完成
            f.channel().closeFuture().sync();//7.阻塞直到Channel关闭
        } finally {
            group.shutdownGracefully().sync();//8.调用shutdownGracefully来关闭线程池和释放所有资源
        }
    }

    public static void main(String[] args)throws Exception{
//        if(args.length != 2){
//            System.err.println("Usage: " + EchoClient.class.getSimpleName() + "<host> <port>");
//            return;
//        }
//        final String host = args[0];
//        final int port = Integer.parseInt(args[1]);

        final String host = "localhost";
        final int port = 8990;

        new EchoClient(host,port).start();
    }
}