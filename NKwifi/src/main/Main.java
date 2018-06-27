package main;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.LinkedList;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
public class Main {
    BufferedReader reader;
    PrintWriter writer;
    OutputStream byteWriter;
    static JLabel statusLabel;
    Socket sock;
    JFrame frame;
	public static void main(String[] args) {
			new Main().go();
	}
	
    public void go() {
        // build GUI
        frame = new JFrame("启动器");
        JPanel mainPanel = new JPanel();
        statusLabel = new JLabel("",JLabel.CENTER);    
        statusLabel.setSize(350,100);
        JButton setButton = new JButton("设置netKeeper路径");
        JButton startButton = new JButton("保护wifi");
        JButton endButton = new JButton("解除保护");
        setButton.addActionListener(new SetButtonListener());
        startButton.addActionListener(new StartButtonListener());
        endButton.addActionListener(new EndButtonListener());
        mainPanel.add(setButton);
        mainPanel.add(startButton);
        mainPanel.add(endButton);
        mainPanel.add(statusLabel);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400,100);
        frame.setVisible(true);
    } // close go
  
    public class SetButtonListener implements ActionListener {
    	 final JFileChooser  fileDialog = new JFileChooser();
         JButton showFileDialogButton = new JButton("Open File");
        public void actionPerformed(ActionEvent ev) {
        	   int returnVal = fileDialog.showOpenDialog(frame);
               if (returnVal == JFileChooser.APPROVE_OPTION) {
                  java.io.File file = fileDialog.getSelectedFile();
                  statusLabel.setText("File Selected :" 
                  + file.getAbsolutePath());  
                createPathTxt(file.getAbsolutePath());
               }
               else{
                  statusLabel.setText("Open command cancelled by user." );           
               }      
            }
      }
   
    public class StartButtonListener implements ActionListener {
       public void actionPerformed(ActionEvent ev) {
    	   statusLabel.setText("Open command cancelled by user." );     
       	   	    String path = getPath();
       	   	    if(path == null && !Find.isNKExists()) {
       	   	    	JOptionPane.showMessageDialog(null, "请先配置创翼路径或手动启动创翼", "错误", JOptionPane.ERROR_MESSAGE);
       	   	    	return;
       	   	    }
       	   	statusLabel.setText("");
			startAndSuspend(path);
           }
     }
    public class EndButtonListener implements ActionListener {
          public void actionPerformed(ActionEvent ev) {
          	  Command.suspendr("NK.exe");
          	  statusLabel.setText("已经解除wifi保护" ); 
              }
        }
   public static void createPathTxt(String filePath) {
	   File pathTxt = new File("nkPath.txt");
	   FileOutputStream fos  = null;
       if(pathTxt.exists()) {
     	  pathTxt.delete();
       }
       try {
		pathTxt.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       FileWriter fw;
	try {
		fw = new FileWriter(pathTxt, true);
		  BufferedWriter bw = new BufferedWriter(fw);
	       bw.write(filePath);
	       bw.flush();
	       bw.close();
	       fw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
   }
   public static String getPath(){
	   File nkPath = new File("nkPath.txt");
	   String result = null;
	   BufferedReader buf = null;
		String line = null;
		if(!nkPath.exists()) {
			return null;
		}
		try {
			buf = new BufferedReader(new InputStreamReader(new FileInputStream(nkPath.getAbsolutePath()), "utf-8"));
			line = buf.readLine();
			File f = new File(line);
			if(f.exists()&&line.contains("NetKeeper.exe")){
				result = line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(buf != null){
				try {
					buf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
   }
   public static void startAndSuspend(String path) {
	   File file = null;
	   int count = 0;
	   //根据自己使用的wifi软件重写
	   Tool.cancelWifiState();
	   while(true) {
			try {
				if(path != null) {
					 file = new File(path);
					 Desktop.getDesktop().open(new File(file.getAbsolutePath()));
				}	
				 Thread.sleep(500);
				if(Find.isConnect()){
					break;
				}	
				count ++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
			Tool.waitConnectWWW();
			Command.suspend("NK.exe");
			statusLabel.setText("现在可以开启wifi");
   }
}