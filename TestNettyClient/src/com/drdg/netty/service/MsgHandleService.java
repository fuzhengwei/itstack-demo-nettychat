package com.drdg.netty.service;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;

import com.drdg.netty.agreement.MsgAgreement;
import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group.ServerConnectEnum;
import com.drdg.netty.bean.InformationPacket.Group.User;
import com.drdg.netty.bean.InformationPacket.Login;
import com.drdg.netty.bean.InformationPacket.Login.LoinEnum;
import com.drdg.netty.bean.InformationPacket.MsgInfo;
import com.drdg.netty.bean.UserBean;
import com.drdg.netty.control.CoreBusinessControl;

public class MsgHandleService {
	
	public static ChannelHandlerContext channel;
	public static CoreBusinessControl coreBusinessControl;
	private static MsgAgreement msgAgree = new MsgAgreement(true);
	
	static public void doMsgForShunt(InformationPacket.Group group){
		
		switch (group.getMsgEnum().getNumber()) {
		//连接服务端反馈
		case InformationPacket.MsgEnum.ReuqestToConnect_VALUE:
			
			NoticeConnectState(group.getServerConnectEnum());
			
			break;
		case InformationPacket.MsgEnum.CheckToLogin_VALUE:
			
			NoticeLoginState(group.getLogin().getLoginState(),group.getLogin());
			
			break;
		case InformationPacket.MsgEnum.ChatOneToOne_VALUE:
			
			break;
		case InformationPacket.MsgEnum.ChatOneToAll_VALUE:
			NoticeReceivedMsgInfo(group.getMsgInfo());
			break;
		case InformationPacket.MsgEnum.ChatToFriend_VALUE:
			
			NoticeRefreshFriendsList(group.getUserListList());
			
			System.out.println("收到群消息");
			
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * 
	 * @param msgInfo
	 */
	private static void NoticeReceivedMsgInfo(MsgInfo msgInfo) {
		
		coreBusinessControl.doReceivedMsgInfo(msgInfo);
		
	}

	/**
	 * 
	 * @param userListList
	 */
	private static void NoticeRefreshFriendsList(List<User> userListList) {
		coreBusinessControl.doRefreshFriendList(userListList);
	}

	/**
	 * 通知登录状态
	 * @param loginState
	 * @param login
	 */
	private static void NoticeLoginState(LoinEnum loginState, Login login) {
		
		if(loginState == Login.LoinEnum.Success){
			coreBusinessControl.doChangeGroupChatView(login);
		}else{
			
		}
		
	}

	/**
	 * 通知连接状态
	 * @param serverConnectEnum
	 */
	private static void NoticeConnectState(ServerConnectEnum serverConnectEnum) {
		System.out.println("发送登录信息"+serverConnectEnum);
		if(serverConnectEnum == InformationPacket.Group.ServerConnectEnum.Success){
			coreBusinessControl.doCheckLogin();
			System.out.println("发送登录信息");
		}else{
			
		}
		
	}

	static public void doCheckLogin(UserBean user){
		channel.writeAndFlush(msgAgree.doGetLoginInfoPacket(user.getUserName(), user.getUserPwd()));
	}
	
	
	static public void doSendMsgStr(String userName,String msgStr){
		channel.writeAndFlush(msgAgree.doGetGroupSendInfoPacket(userName, msgStr));
	}
	
}
