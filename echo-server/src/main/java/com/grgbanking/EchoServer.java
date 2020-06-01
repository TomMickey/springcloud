package com.grgbanking;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;


public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        //        if(args.length != 1){
//            System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
//            return;
//        }
//        int port = Integer.parseInt(args[0]);//1.设置端口值
        int port = 8990;//1.设置端口值
        new EchoServer(port).start();//2.启动服务
    }

    public void start() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();//3.创建EventLoopGroup，一个线程
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)//4.创建ServerBootstrap，此处也可放入多个EventLoopGroup
                    .channel(NioServerSocketChannel.class)//5.指定使用NIO的传输Channel，指定信道类型
                    .localAddress(new InetSocketAddress(port))//6.设置socket地址使用所选端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {//7.当有一个新的连接被接受，一个新的子Channel将被创建，ChannelInitializer添加EchoServerHandler到Channel的ChannelPipeline，
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception{
                            ch.pipeline().addLast(
                                    new EchoServerHandler());
                        }
                    });
            ChannelFuture f = b.bind().sync();//8.绑定的服务器，sync等待服务器关闭，调用sync()的原因是当前线程阻塞
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();//9.关闭Channel和块
        } finally {
            group.shutdownGracefully().sync();//10.关闭EventLoopGroup，释放所有资源
        }
    }
}
