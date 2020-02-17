package ssh_test;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SSH_TEST {

	/**
	 * JSch Example Tutorial
	 * Java SSH Connection Program
     * @param args
	 */
	public static void main(String[] args) throws Exception {
            comsave("cd / && bash tmp/mk/script.sh <session> <path>");
	}
        
       
        
        
       
        
        static void comsave(String command){
            String host="host-public-ip";
	    String user="<username>";
	    String password="<password>";
	    
	    try{
	    	
                
	    	java.util.Properties config = new java.util.Properties(); 
	    	config.put("StrictHostKeyChecking", "no");
	    	JSch jsch = new JSch();
	    	Session session=jsch.getSession(user, host, 22);
	    	session.setPassword(password);
	    	session.setConfig(config);
	    	session.connect();
	    	System.out.println("Connected");
	    	
	    	Channel channel=session.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command);
	        channel.setInputStream(null);
	        ((ChannelExec)channel).setErrStream(System.err);
	        
	        InputStream in=channel.getInputStream();
	        channel.connect();
                
                File myObj = new File("C:\\Users\\Name\\Desktop\\filename.txt");

                FileWriter myWriter = new FileWriter("C:\\Users\\Name\\Desktop\\filename.txt");
              
                
                
	        byte[] tmp=new byte[1024];
	        while(true){
	          while(in.available()>0){
	            int i=in.read(tmp, 0, 1024);
	            if(i<0)break;
	            myWriter.write(new String(tmp, 0, i));
	          }
	          if(channel.isClosed()){
                    myWriter.close();
	            System.out.println("exit-status: "+channel.getExitStatus());
	            break;
	          }
	          try{Thread.sleep(1000);}catch(InterruptedException ee){}
	        }
                
	        channel.disconnect();
	        session.disconnect();
	        System.out.println("DONE");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }

        }
}