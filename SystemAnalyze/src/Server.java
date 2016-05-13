import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.*;
import java.io.*;
public class Server {
	public static String ip = "192.168.1.102";
	public static int Transfer_port = 10023;
	public static int Receive_port = 10024;
	public static StudentDatabase db = new StudentDatabase();
	public static Queue<User> qq = new ArrayDeque<User>();
	public static void main(String[] args){
		ServerReceive sr = new ServerReceive();
		ServerTransfer sf = new ServerTransfer();
		sr.start();
		sf.start();
	}
}

