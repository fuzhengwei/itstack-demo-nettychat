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

		MsgAgreement msgAgree = new MsgAgreement(true);
		
		//连接服务端后组织信息发送
		ctx.writeAndFlush(msgAgree.doGetLoginInfoPacket("54235045", "Qq12345."));
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		InformationPacket.Group group = (Group) msg;
		
		System.out.println(group.getLogin().getUserName());
		System.out.println(group.getLogin().getUserPwd());
		System.out.println(group.getLogin().getLoginState().name());
		System.out.println(group.getLogin().getFeedBackInfo());
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}
	
	
	
}
