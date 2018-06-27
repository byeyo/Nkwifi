package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Command {

	public static void main(String[] args) {
		
		suspendr("NK.exe");
	}

	public static String runit (String cmd){
		  
	  
	    String line = null;  
	    StringBuilder sb = new StringBuilder();  
	    Runtime runtime = Runtime.getRuntime();  
	    try {  
	    Process process = runtime.exec(cmd);  
	    InputStreamReader isr = new InputStreamReader(process.getInputStream(),Charset.forName("GBK"));
	    BufferedReader  bufferedReader = new BufferedReader(isr);  
      
	        while ((line = bufferedReader.readLine()) != null) {  
	            sb.append(line + "\n");  
	        }  
	        isr.close();
	    } catch (IOException e) {  
	        // TODO 自动生成的 catch 块  
	        e.printStackTrace();  
	    }  
	   
	    return sb.toString();
	    //System.out.println(sb.toString());  
	}
	public static void killNK(String pid){
		runit("nircmd elevate taskkill /pid "+pid+" -t -f");
	}
	public static void killName(String name){
		runit("nircmd elevate taskkill /f /t /im "+name);
	}
	public static void suspend(String name) {
		runit("pssuspend64.exe "+name);
	}
	public static void suspendr(String name) {
		runit("pssuspend64.exe -r "+name);
	}
}
