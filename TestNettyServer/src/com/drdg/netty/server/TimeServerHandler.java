package com.drdg.netty.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group;
import com.drdg.netty.service.MsgHandleService;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class TimeServerHandler extends ChannelHandlerAdapter {

	MsgAgreement msgAgree = new MsgAgreement(true);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		MsgHandleService.channelGroup.add(ctx.channel());
		MsgHandleService.userMap.put(ctx.channel().id().toString(), ctx);
		
		System.out.println("登录"+MsgHandleService.channelGroup.size());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		MsgHandleService.channelGroup.remove(ctx.channel());
		MsgHandleService.userMap.remove(ctx.channel().id().toString());
		MsgHandleService.userList.remove(ctx.channel().id().toString());
		System.out.println("退出");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		InformationPacket.Group group = (Group) msg;
		
		switch (group.getMsgEnum().getNumber()) {
		case InformationPacket.MsgEnum.CheckToLogin_VALUE:
			//登录
			InformationPacket.Login login = group.getLogin();
			//返回登录信息
			ctx.writeAndFlush(msgAgree.doGetLoginInfoPacket(login.getUserName(), login.getUserPwd(), InformationPacket.Login.LoinEnum.Success, "OK"));
			//组织好友列表信息
			InformationPacket.Group.User.Builder userBean = InformationPacket.Group.User.newBuilder();
			userBean.setId(ctx.channel().id().toString());
			userBean.setUserName(login.getUserName());
			userBean.setUserPwd("");
			MsgHandleService.userList.put(ctx.channel().id().toString(), userBean.build());
			
			//群发送好友列表
			MsgHandleService.channelGroup.writeAndFlush(msgAgree.doGetChatFriendsListInfoPacket(MsgHandleService.userList.values()));
			
			break;
		case InformationPacket.MsgEnum.ChatOneToOne_VALUE:
			//1v1
			break;
		case InformationPacket.MsgEnum.ChatOneToAll_VALUE:
			//1vn
			MsgHandleService.channelGroup.writeAndFlush(group);
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
