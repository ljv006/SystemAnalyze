import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
public class ServerTransfer extends Thread {
	ServerSocket serverSocket = null;
	Socket MySocket = null;  
    BufferedReader reader = null;  
    PrintWriter wtr = null;  
    BufferedReader keyin = null;
    ObjectOutputStream os = null;
    User user = null;
    public ServerTransfer()  
    {  
        try  
        {  
        	serverSocket = new ServerSocket(Server.Transfer_port);
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
    }  
    public void run()  
    {  
        try  
        {  
        	MySocket = serverSocket.accept();
            os = new ObjectOutputStream(MySocket.getOutputStream());
            //从控制层获取命令
            while (true) {
            if (!Server.qq.isEmpty()) {
            	User user = Server.qq.peek();
            	if (Server.db.isfind(user) && user.type == "regist") {
            		user.type = "existed";
            		Server.qq.poll();
            	} else if (Server.db.isfind(user) && user.type == "login"){
            		user.type = "login_succeed";
            		Server.qq.poll();
            	} else if (!Server.db.isfind(user) && user.type == "regist") {
            		Server.db.insert(user);
            		user.type = "regist_succeed";
            		Server.qq.poll();
            	} else if (!Server.db.isfind(user) && user.type == "login") {
            		user.type = "login_failed";
            		Server.qq.poll();
            	}
            	os.writeObject(user); 
            	os.flush();
            }
           }
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
