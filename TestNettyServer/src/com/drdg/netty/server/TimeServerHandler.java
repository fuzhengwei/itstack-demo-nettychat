package com.drdg.netty.server;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		InformationPacket.Group group = (Group) msg;
		
		switch (group.getMsgEnum().getNumber()) {
		case InformationPacket.MsgEnum.CheckToLogin_VALUE:
			//登录
			InformationPacket.Login login = group.getLogin();
			//输出用户名、密码
			System.out.println(login.getUserName());
			System.out.println(login.getUserPwd());
			MsgAgreement msgAgree = new MsgAgreement(true);
			ctx.writeAndFlush(msgAgree.doGetLoginInfoPacket(login.getUserName(), login.getUserPwd(), InformationPacket.Login.LoinEnum.Success, "OK"));
			break;
		case InformationPacket.MsgEnum.ChatOneToOne_VALUE:
			//1v1
			break;
		case InformationPacket.MsgEnum.ChatOneToAll_VALUE:
			//1vn
			break;
		default:
			break;
		}
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
	
}
