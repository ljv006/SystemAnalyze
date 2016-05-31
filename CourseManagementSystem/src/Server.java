import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.*;
import java.io.*;
public class Server {
	private static Queue<String> q = new ArrayDeque<String>();
	public static void main(String[] args){
		Server server = new Server();
		Command command = server.new Command();
		command.start();
	}
	
	public class MyObject{
		
	}

	private Queue<MyObject> qq;
	public int port = 10023;
		public class Command extends Thread {
			ServerSocket serverSocket = null;
			Socket socket = null;  
	        OutputStream os = null;  
	        public Command() {
	        	try{
	        		serverSocket = new ServerSocket(port);
	        	} 
	        	catch (IOException e)  
	            {  
	                e.printStackTrace();  
	            }  
	        }
	        /*
			public Boolean send(int command){
				try {
					serverSocket =  new ServerSocket(port);
					socket = serverSocket.accept();
					os = socket.getOutputStream();
					os.write(command);
					return true;
					}
					catch(Exception e) {
						e.printStackTrace();
						return false;
					} finally{
						try{
							os.close();
							socket.close();
							serverSocket.close();
						} catch(Exception e) {
							
						}
					}
			}
			*/
			public void run(){
				while (true) {
					System.out.println("Waiting..");
					try {
						socket = serverSocket.accept();
						ServerThread th = new ServerThread(socket);
						th.start();
						sleep(1000);
					}
					catch(Exception e) {
						System.out.println("error");
						e.printStackTrace();
					} finally{
						try{
						} catch(Exception e) {
							
						}
					}
				}
			}
			class ServerThread extends Thread
			{
				Socket sk = null;
				public ServerThread(Socket sk)
				{
					this.sk = sk;
				}
				public void run()
				{
					try
					{
						InputStream is = sk.getInputStream();
						byte[] b = new byte[1024];
						int n = is.read(b);
						q.add(new String(b,0, n));
						if (!(q.isEmpty())) {
							System.out.println(q.peek());
							q.poll();
						}
					}
					catch(IOException e){
						e.printStackTrace();  
					}
				}
			}
		}
		/*
		public class Data{
			ServerSocket serverSocket = null;
			Socket socket = null;  
			ObjectInputStream is = null;  
	        ObjectOutputStream os = null;
			public Boolean send(MyObject mo) throws ClassNotFoundException{
		        try {
		        	serverSocket =  new ServerSocket(port);
					socket = serverSocket.accept();
		            os = new ObjectOutputStream(socket.getOutputStream());  
		            os.writeObject(mo);  
		            os.flush();
		            return true;
		        } catch (IOException ex) {
		        	return false;
		        } finally {  
		            try {  
		                is.close();  
		            } catch(Exception ex) {}  
		            try {  
		                os.close();  
		            } catch(Exception ex) {}  
		            try {  
		                socket.close();  
		            } catch(Exception ex) {}  
		        }   
			}
			public Boolean receive(){
				ObjectInputStream is = null;  
		        ObjectOutputStream os = null;  
		        try {
		        	serverSocket =  new ServerSocket(port);
					socket = serverSocket.accept();
		            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));  
		     

		            Object obj = is.readObject();  
		            MyObject mo = (MyObject)obj;
		            qq.add(mo);
		            return true;
		        } catch (IOException ex) {  
		        	return false;
		        } catch(ClassNotFoundException ex) {  
		            return false; 
		        } finally {  
		            try {  
		                is.close();  
		            } catch(Exception ex) {}  
		            try {  
		                os.close();  
		            } catch(Exception ex) {}  
		            try {  
		                socket.close();  
		            } catch(Exception ex) {}  
		        }   
			}
		}
		*/
}
