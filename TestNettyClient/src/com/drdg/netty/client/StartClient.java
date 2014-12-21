package com.drdg.netty.client;

public class StartClient {

	public static void main(String[] args) {
		
		String inetHost = "127.0.0.1";
		int inetPort = 8080;
		
		NettyClient nc = new NettyClient();
		try {
			nc.connect(inetPort, inetHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
