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
public class ClientTransfer extends Thread {
	Socket MySocket = null;  
    BufferedReader reader = null;  
    PrintWriter wtr = null;  
    BufferedReader keyin = null;
    ObjectOutputStream os = null;
    public ClientTransfer()  
    {  
        try  
        {  
            MySocket = new Socket(Client.ip, Client.Transfer_port);  
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
            os = new ObjectOutputStream(MySocket.getOutputStream());
            //从UI层获取用户
            while (true)  
            {  
            	Thread.sleep(1000);
            	if (!Client.qq.isEmpty()) {
            		User user = Client.qq.peek();
                    os.writeObject(user); 
                    os.flush();
                    Client.qq.poll();
                }  
                  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
