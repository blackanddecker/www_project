package Utils;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
	String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/RubishGram?useSSL=false";
	String USER = "root";
	String PASS = "1e2d3c4b5a";
	
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
