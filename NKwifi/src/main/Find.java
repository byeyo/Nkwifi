package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {	
	/** 
	 * **/
	static String regNK = "[A-Z0-9a-z]([A-Z0-9a-z]+-){4}([A-Za-z0-9]+)(\\.exe)";
	static String regNKPid = "[A-Z0-9a-z]([A-Z0-9a-z]+-){4}([A-Za-z0-9]+)\\s+([0-9]+)";
	static String regWifi = "360AP.exe";
	static String regWlansvc = "WLAN\\sAutoConfig";
	public static void main(String[] args){
			String p = Command.runit("tasklist");
			Pattern r = Pattern.compile(regNKPid);
			Matcher m = r.matcher(p);
			System.out.println(m.find());
			System.out.println(m.group(3));
			m.find();
			System.out.println(m.group(3));
			
		}
	
	static public boolean wifi (){	
		String taskString = Command.runit("tasklist");
		Pattern r = Pattern.compile(regWifi);
		return isMatch(r, taskString)	;			
	}
	


	static private boolean isMatch (Pattern r ,String name){
		Matcher m = r.matcher(name);
		return m.find();
	}
	static public String getNK () {
		String taskString = Command.runit("tasklist");
		Pattern r = Pattern.compile(regNK);
		Matcher m = r.matcher(taskString);
		if (!m.find()){
			return "-1";
		}
		return m.group();
	}
	static boolean isNKExists() {
		String taskString = Command.runit("tasklist");
		Pattern r = Pattern.compile("NK.exe");
		return isMatch(r, taskString);
	}
	static public String getNKpid(){
		String taskString = Command.runit("tasklist");
		Pattern r = Pattern.compile(regNKPid);
		Matcher m = r.matcher(taskString);
		if (!m.find()){
			return "-1";
		}
		return m.group(3);
	}
	static public boolean wlansvc(String taskString){
		return Pattern.compile(regWlansvc).matcher(taskString).find();
	}
	static public boolean isConnect(){
		String taskString = Command.runit("ping www.baidu.com");
		Pattern r = Pattern.compile("时间");
		return isMatch(r, taskString)	;			
	}
	
}
