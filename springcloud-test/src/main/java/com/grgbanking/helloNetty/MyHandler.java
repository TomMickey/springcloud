package com.grgbanking.helloNetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端发送的消息:" + s);
        channelHandlerContext.writeAndFlush("您发来的消息转为大写为；"  +s.toUpperCase());
    }
}
