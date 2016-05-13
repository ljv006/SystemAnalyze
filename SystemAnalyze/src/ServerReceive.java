import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.*;
import java.io.*;
public class ServerReceive extends Thread{
			ServerSocket serverSocket = null;
			Socket socket = null;   
	        BufferedReader rdr = null;  
	        PrintWriter wtr = null;
	        ObjectInputStream is = null;  
	        ObjectOutputStream os = null;
	        public ServerReceive() {
	        	try{
	        		serverSocket = new ServerSocket(Server.Receive_port);
	        	} 
	        	catch (IOException e)  
	            {  
	                e.printStackTrace();  
	            }  
	        }
			public void run(){
				try {
					socket = serverSocket.accept();
					while (true) {
						Thread.sleep(1000);
						int num = Server.qq.size();
						System.out.println(num);
						System.out.println("Waiting..");
							if (socket != null) {
				                try {
				                	is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				                 
								try {
									User user = (User)is.readObject();
									Server.qq.add(user);
								}
								catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
				                
							}
						}
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
}
