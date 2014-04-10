package org.alanjin.smsmms.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	public Connection getConnection(){
		//load the driver for mysql
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//getting connection 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/SMS-MMS","root","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The connection is not established, returning null "+e.getMessage());
			e.printStackTrace();
		}
		return con;
		//com.mysql.jdbc.Driver
	}
}