package com.drdg.netty.agreement;

import java.util.List;

import com.drdg.netty.bean.InformationPacket;
import com.drdg.netty.bean.InformationPacket.Group.User;

public class MsgAgreement {

	private InformationPacket.Group group;
	private InformationPacket.Login login;
	private InformationPacket.MsgInfo msgInfo;
	private InformationPacket.Group.User user;
	
	@SuppressWarnings("unused")
	private MsgAgreement(){}
	
	public MsgAgreement(boolean bool){
		
		if(bool){
			
			login = InformationPacket.Login.newBuilder()
									 .setUserName("")
									 .setUserPwd("")
									 .setLoginState(InformationPacket.Login.LoinEnum.Request)
									 .setFeedBackInfo("")
									 .build();
			
			msgInfo = InformationPacket.MsgInfo.newBuilder()
									   .setSendUser("")
									   .setSendToUser("")
									   .setSendInfo("")
									   .build();
			
			user = InformationPacket.Group.User.newBuilder()
								               .setId("")
								               .setUserName("")
								               .setUserPwd("")
								               .build();
			
			
			group = InformationPacket.Group.newBuilder()
									 .setLogin(login)
									 .setMsgInfo(msgInfo)
									 .setMsgEnum(InformationPacket.MsgEnum.ReuqestToConnect)
									 .setServerConnectEnum(InformationPacket.Group.ServerConnectEnum.Request)
									 .addUserList(user)
									 .build();
			
			
		}
		
	}
	
	/**
	 * get connect server agreement Group
	 * @param serverConnectEnum
	 * @return
	 */
	public InformationPacket.Group doGetConnectServerInfoPacket(InformationPacket.Group.ServerConnectEnum serverConnectEnum){
		group = InformationPacket.Group.newBuilder()
				 .setLogin(login)
				 .setMsgInfo(msgInfo)
				 .setMsgEnum(InformationPacket.MsgEnum.ReuqestToConnect)
				 .setServerConnectEnum(serverConnectEnum)
				 .addUserList(user)
				 .build();
		
		return group;
	}
	
	/**
	 * get login agreement Group 
	 * @param userName
	 * @param userPwd
	 * @return InformationPacket.Group
	 */
	public InformationPacket.Group doGetLoginInfoPacket(String userName,String userPwd){
		
		InformationPacket.Login login = InformationPacket.Login.newBuilder()
									       				 .setUserName(userName)
									       				 .setUserPwd(userPwd)
									       				 .setLoginState(InformationPacket.Login.LoinEnum.Request)
									       				 .setFeedBackInfo("")
									       				 .build();
		
		group = InformationPacket.Group.newBuilder()
				 .setLogin(login)
				 .setMsgInfo(msgInfo)
				 .setMsgEnum(InformationPacket.MsgEnum.CheckToLogin)
				 .setServerConnectEnum(InformationPacket.Group.ServerConnectEnum.Success)
				 .addUserList(user)
				 .build();
		
		return group;
		
	}
	
	/**
	 * get checked login agreement Group 
	 * @param userName
	 * @param userPwd
	 * @param loginEnum
	 * @param feedBackInfo
	 * @return
	 */
	public InformationPacket.Group doGetLoginInfoPacket(String userName,String userPwd,InformationPacket.Login.LoinEnum loginEnum,String feedBackInfo){
		
		
		InformationPacket.Login login = InformationPacket.Login.newBuilder()
									       				 .setUserName(userName)
									       				 .setUserPwd(userPwd)
									       				 .setLoginState(loginEnum)
									       				 .setFeedBackInfo(feedBackInfo)
									       				 .build();
		
		group = InformationPacket.Group.newBuilder()
				 .setLogin(login)
				 .setMsgInfo(msgInfo)
				 .setMsgEnum(InformationPacket.MsgEnum.CheckToLogin)
				 .setServerConnectEnum(InformationPacket.Group.ServerConnectEnum.Success)
				 .addUserList(user)
				 .build();
		
		return group;
		
	}
	
	/**
	 * get chat friends list
	 * @param userList
	 * @return
	 */
	public InformationPacket.Group doGetChatFriendsListInfoPacket(List<User> userList){
		
		InformationPacket.Group.Builder groupBuilder = InformationPacket.Group.newBuilder();
		groupBuilder.setLogin(login);
		groupBuilder.setMsgInfo(msgInfo);
		groupBuilder.setMsgEnum(InformationPacket.MsgEnum.ChatToFriend);
		groupBuilder.setServerConnectEnum(InformationPacket.Group.ServerConnectEnum.Success);
		for (User user : userList) {
			groupBuilder.addUserList(user);
		}
		group = groupBuilder.build();
		
		return group;
	}
	
	/**
	 * get group send info packet
	 * @param userName
	 * @param msgStr
	 * @return
	 */
	public InformationPacket.Group doGetGroupSendInfoPacket(String userName,String msgStr){
		
		msgInfo = InformationPacket.MsgInfo.newBuilder()
				   .setSendUser(userName)
				   .setSendToUser("")
				   .setSendInfo(msgStr)
				   .build();
		
		group = InformationPacket.Group.newBuilder()
				 .setLogin(login)
				 .setMsgInfo(msgInfo)
				 .setMsgEnum(InformationPacket.MsgEnum.ChatOneToAll)
				 .setServerConnectEnum(InformationPacket.Group.ServerConnectEnum.Success)
				 .addUserList(user)
				 .build();
		
		return group;
		
	}
	
}
