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
import java.util.ArrayDeque;
import java.util.Queue;
//�ͻ���
public class Client{
	//����IP
	public static String ip = "172.18.41.171";
	//�����˿�
	public static loginWindow lview = new loginWindow();
	public static registerWindow rview = new registerWindow();
	public static Queue<User> qq = new ArrayDeque<User>();
	public static int Transfer_port = 10024;
	public static int Receive_port = 10023;
	public static void main(String[] args) {
		loginWindow.main(args);
		ClientTransfer ct = new ClientTransfer();
		ClientReceive cr = new ClientReceive();
		ct.start();
		cr.start();
	}
}