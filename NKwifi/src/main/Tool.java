package main;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Tool {

	public static void main(String[] args) {

	/*	String cmd = "start 创翼";
		String newStr =null;
		try {
			byte[] temp = cmd.getBytes("utf-8");
			newStr=new String(temp,"GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    Command.killName("360AP.exe");
	    System.out.println("1");
		wait360apOff();
		Command.runit("nircmd.exe elevate net stop Wlansvc");
		  System.out.println("2");
		waitSvcStop();	
		while(true)
		try {
			Command.runit("nircmd elevate 创翼.lnk");
			  System.out.println("3");
			  
			System.out.println("连接中");
			Thread.sleep(2000);
			
			if(Find.isConnect()){
				break;
			}
			else{
				System.out.println("连接网络失败，重启客户端");
				String nkPid = Find.getNKpid();
				System.out.println("nkPid"+nkPid);
				Command.killNK(nkPid);
				waitNKoff();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitConnectWWW();
		String nkPid = Find.getNKpid();
		System.out.println("nkPid"+nkPid);
		Command.suspend("NK.exe");
		  System.out.println("4");
		waitNKoff();
		Command.runit("nircmd elevate 360AP.exe.lnk");
		for(;;){
			
			if()
		}
		Command.killName(nkProcessName);*/
	

		
	}
	
	public static boolean nircmdExists(String fileName){
		File nircmd= new File(fileName);
		return nircmd.exists();
	}
	public static void waitSvcStop(){
		try {
			for(;;){
				String taskString = Command.runit("net start");
				if (!Find.wlansvc(taskString)){
					break;
				}
				Thread.sleep(200);
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void wait360apOff(){
		try {
			for (;;){
				if (!Find.wifi()){
					break;
				}
				Thread.sleep(200);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void waitConnectWWW(){
		try {
			for (;;){
				if (Find.isConnect()){
					break;
				}
				Thread.sleep(200);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void waitNKoff(){
		try {
			for (;;){
				if (Find.getNK().equals("-1")){    //没找找到NK进程则返回-1
					break;
				}
				Thread.sleep(200);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void cancelWifiState() {
		 Command.killName("360AP.exe");
		 System.out.println("1");
	     wait360apOff();
		 Command.runit("nircmd.exe elevate net stop Wlansvc");
		 System.out.println("2");
		 waitSvcStop();	
	}
}
