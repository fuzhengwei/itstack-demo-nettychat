package com.drdg.netty.server;

public class StartServer {

	
	
	public static void main(String[] args) {
		
		int port = 7397;
		
		NettyServer ns = new NettyServer();
		
		try {
			
			ns.bing(port);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
