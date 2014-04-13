package org.alanjin.smsmms.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private String connectionUrl;
	private String user;
	private String passwd;
	public DBConn() {
		this("localhost", "3306", "SMS-MMS", "root", "");
	}
	
	public DBConn(String ip, String port, String db, String username,
			String passwd) {
		this.connectionUrl = "jdbc:mysql://" + ip + ":" + port + "/" + db;
		this.user = username;
		this.passwd = passwd;
	}

	public Connection getConnection() throws SQLException{
		//load the driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		//getting connection 
		Connection con = DriverManager.getConnection(connectionUrl, user, passwd);
		return con;
		//com.mysql.jdbc.Driver
	}
}