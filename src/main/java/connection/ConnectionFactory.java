package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	public static Connection getConnectionH2(){
		try {
			return DriverManager.getConnection("jdbc:h2:~/test2", "sa", "");
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
