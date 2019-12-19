import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost/RubishGram?useSSL=false";
	String USER = "pmauser";
	String PASS = "password_here";
	
	public Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASS);	
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
