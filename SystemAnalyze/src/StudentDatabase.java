import java.io.*;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
*/
public class StudentDatabase {
	static String fileName = System.getProperty("user.dir") + "\\src\\Student.txt";
	static int ID = 0;
	static int name =1;
	static int password = 2;
	public boolean isfind(User user) throws IOException {
		FileReader fileReader = new FileReader(fileName);
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String out = "";
		for (out = bufferedReader.readLine(); out != null; out = bufferedReader.readLine()) {
			String studentInfo[] = out.split(" ");
			if (user.ID.equals(studentInfo[ID])) {
				return true;
			}
		}
		bufferedReader.close();
		fileReader.close();
		return false;
	}
	public boolean insert(User user) throws IOException {
		if (isfind(user)) {
			return false;
		}
		FileWriter fileWriter = new FileWriter(fileName, true);
		String input = user.ID;
		input += " ";
		input += user.name;
		input += " ";
		input += user.password;
		input += " ";
		input += user.identity;
		input += "\r\n";
		fileWriter.write(input);
		fileWriter.close();
		return true;
	}
	public boolean delete(User user) throws IOException{
		if (!isfind(user)) {
			return false;
		}
		FileWriter fileWriter = new FileWriter("temp.txt");
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String out = "";
		for (out = bufferedReader.readLine(); out != null; out = bufferedReader.readLine()) {
			String studentInfo[] = out.split(" ");
			if (!user.ID.equals(studentInfo[ID])) {
				fileWriter.write(out + "\r\n");
			}
		}
		fileWriter.close();
		bufferedReader.close();
		
		fileWriter = new FileWriter(fileName);
		fileReader = new FileReader("temp.txt");
		bufferedReader = new BufferedReader(fileReader);
		for (out = bufferedReader.readLine(); out != null; out = bufferedReader.readLine()) {
			fileWriter.write(out + "\r\n");
		}
		fileWriter.close();
		fileReader.close();
		
		File file = new File("temp.txt");
		if (file.exists()) {
			file.delete();
		}
		return true;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException/*, SQLException*/ {
		/*
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:student.db");
		Statement statement = connection.createStatement();
		*/
		StudentDatabase studentDatabase = new StudentDatabase();
		User user = new User("13331145", "ÁÖºÆÎÄ", "123", "teacher");
		System.out.println(studentDatabase.isfind(user));
		System.out.println(studentDatabase.insert(user));
		System.out.println(studentDatabase.delete(user));
		System.out.println(studentDatabase.delete(user));
		System.out.println(studentDatabase.insert(user));
	}
}