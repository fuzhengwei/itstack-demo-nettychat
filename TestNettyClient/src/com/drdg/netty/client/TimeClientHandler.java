package com.drdg.netty.client;

import java.util.logging.Logger;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter{

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		InformationPacket.Group group = (Group) msg;
		System.out.println("====>"+group.getServerConnectEnum().name());
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	
	
}
