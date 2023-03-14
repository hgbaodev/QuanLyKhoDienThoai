package config;

import java.sql.Connection;
import java.sql.DriverManager;
public class JDBCUtil {
	public static Connection getConnection() {
		Connection result = null;
		try {
			// Dang ky MySQL Driver voi DriverManager
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//Cac thong so
			String url = "jdbc:mySQL://localhost:3306/warehousemanagement";
			String userName = "root";
			String password = "";
			//Tao ket noi 
			result = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
                        System.out.println("Conenct database err");
		}
		return result;
	}
	public static void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
