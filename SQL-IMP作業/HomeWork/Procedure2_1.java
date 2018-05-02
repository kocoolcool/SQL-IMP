package com.iii.eeit101.chris;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

// Insert one employee
public class Procedure2_1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=DB02";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			String delStmt = "delete from playlist;	delete from seats;";
			PreparedStatement delstmt = conn.prepareStatement(delStmt);
			delstmt.executeUpdate();
			CallableStatement cstmt = conn.prepareCall("{call gen_seats(?,?,?)}");
			cstmt.setString(1, "2016-12-25 13:00");
			cstmt.setInt(2,1);
			cstmt.setString(3, "A廳");
			cstmt.execute();
			
			cstmt.close();     //若沒close 則會出現189筆資料
			delstmt.close();
			
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Message : " + e.getMessage()); // 可利用DatabaseMetaData裡getDatabaseProductName()
			System.out.println("Vendor code : " + e.getErrorCode()); // 配合switch 來判斷ErrorCode() 使得錯誤代碼具有Portable
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class InsertDemo
