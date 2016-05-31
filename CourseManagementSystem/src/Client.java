import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
//�ͻ���
public class Client{
	static String ip = "172.18.41.171";
	static int port = 10023;
	public static void main(String[] args) {
        Client client = new Client();
        Command command = client.new Command();
        command.start();
	}
	//��Ҫ�������
	public class MyObject{
		
	}
	//������������
	private Queue<String> q;
	//����Ķ������
	private Queue<MyObject> qq;
	//����������
	public class Command extends Thread{
		//��ʼ����Ҫ�õ�������
		Socket Mysocket = null;
		OutputStream os = null;
		InputStream is = null;
		PrintWriter pw = null;
        BufferedReader keyin = null;
        BufferedReader reader = null;
		//�����������û�з�������ʱ����true
		public Command() {
		    keyin = new BufferedReader(new InputStreamReader(System.in));
		    try{
		        Mysocket = new Socket(ip, port);
		    }
		    catch (Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		public void run(){
			try{
			//�������ӵ�IP��ַ��ָ���˿ںŵ����׽���
			/*
			Mysocket = new Socket(ip, port);
			os = Mysocket.getOutputStream();
			PrintWriter pw=new PrintWriter(os); 
			pw.write(command);
			pw.flush();
			Mysocket.shutdownOutput();
			return true;
			} catch(Exception e){
				return false;
			} finally{
				try{
					Mysocket.close();
				} catch(Exception e) {
					return false;
				}
			*/
			reader = new BufferedReader(new InputStreamReader(Mysocket.getInputStream()));
			pw = new PrintWriter(Mysocket.getOutputStream());
			String get = keyin.readLine();
			while (true) {
			    if (get != null & get.length() > 0)
			    {
			        pw.println(get);
			        pw.flush();
			    }
			    
			}
		    }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
		}
		/*
		//�����������û�з�������ʱ����false
		public Boolean receive(String ip, int port){
			try{
				Mysocket = new Socket(ip, port);
				is = Mysocket.getInputStream();
				byte[] b = new byte[1024];  
	            int n = is.read(b);
	            //��ȡ�õ�������������
	            q.add(new String(b, 0, n));
				return true;
				} catch(Exception e){
					return false;
				} finally{
					try{
						Mysocket.close();
					} catch(Exception e) {
						return false;
					}
				}
					
		}	
	}
	//����������
	public class Data{
		Socket Mysocket = null;  
        ObjectOutputStream os = null;  
        ObjectInputStream is = null; 
		public Boolean send(String ip, int port){
			  try {  
	                Mysocket = new Socket(ip, port);  
	                os = new ObjectOutputStream(Mysocket.getOutputStream());  
	                Object o = new Object();  
	                os.writeObject(o);  
	                os.flush();
	                return true;
	            } catch(IOException ex) {  
	            		return false;
	            } finally {  
	                try {  
	                    is.close();  
	                } catch(Exception ex) {}  
	                try {  
	                    os.close();  
	                } catch(Exception ex) {
	                	return false;
	                }  
	                try {  
	                    Mysocket.close();  
	                } catch(Exception ex) {}  
	            }  
	    }  
		public Boolean receive(String ip, int port) throws ClassNotFoundException{
              try {  
            	  Mysocket = new Socket(ip, port); 
                  is = new ObjectInputStream(new BufferedInputStream(Mysocket.getInputStream()));  
                  Object obj = is.readObject();  
                  if (obj != null) {
                  	//�����Զ�������
                      MyObject mo= (MyObject)obj;
                      qq.add(mo);
                      return true;
                  }
	            } catch(IOException ex) {  
	            		return false;
	            } finally {  
	                try {  
	                    is.close();  
	                } catch(Exception ex) {}  
	                try {  
	                    os.close();  
	                } catch(Exception ex) {
	                	return false;
	                }  
	                try {  
	                    Mysocket.close();  
	                } catch(Exception ex) {}  
	            }
			return null;  
	        }  
		}
		*/
}
	
}
