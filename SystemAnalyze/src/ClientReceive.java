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
public class ClientReceive extends Thread {
	Socket MySocket = null;  
    BufferedReader reader = null;  
    PrintWriter wtr = null;  
    BufferedReader keyin = null;
    ObjectInputStream is = null;
    User user = null;
    public ClientReceive()  
    {  
        try  
        {  
            MySocket = new Socket(Client.ip, Client.Receive_port);  
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
            is = new ObjectInputStream(MySocket.getInputStream());           
            while (true)  
            {  
            	Thread.sleep(1000);
            	User user = (User)is.readObject();
                if (user.type == "login_succeed")  
                {  
                	Client.lview.Login_Succeed();
                } else if (user.type == "login_failed") {
                	Client.lview.Login_Failed();
                } else if (user.type == "regist_succeed") {
                	Client.rview.Regist_Succeed();
                } else if (user.type == "regist_failed") {
                	Client.rview.Regist_Failed();
                }
                  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
