package com.drdg.netty.agreement;

import com.drdg.netty.bean.InformationPacket;

public class MsgAgreement {

	private InformationPacket.Group group;
	private InformationPacket.Login login;
	private InformationPacket.MsgInfo msgInfo;
	
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
			 
			group = InformationPacket.Group.newBuilder()
									 .setLogin(login)
									 .setMsgInfo(msgInfo)
									 .setMsgEnum(InformationPacket.MsgEnum.CheckToLogin)
									 .build();
			
			
		}
		
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
				 .build();
		
		return group;
		
	}
	
}
