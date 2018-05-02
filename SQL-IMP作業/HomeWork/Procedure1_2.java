package com.iii.eeit101.chris;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

// Insert one employee
public class Procedure1_2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=DB01";
			conn = DriverManager.getConnection(connUrl, "sa", "password");
			String delStmt = "delete from playlist;	delete from seats;";
			PreparedStatement delstmt = conn.prepareStatement(delStmt);
			delstmt.executeUpdate();

			String insStmt = "INSERT INTO playlist VALUES (?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
			pstmt.setString(1, "2016-12-25 13:00");
			pstmt.setInt(2, 1);
			pstmt.setString(3, "A廳");
			pstmt.executeUpdate();

			PreparedStatement pstmtup = conn.prepareStatement("SELECT * FROM m_room where roomid= ?;");
			pstmtup.setString(1, "A廳");

			ResultSet rs = pstmtup.executeQuery();
			PreparedStatement pstmtin = conn.prepareStatement("Insert into seats values(?,?,?,'0',null);");
			while (rs.next()) {

				for (int i = 1; i <= rs.getInt(2); i++) {
					for (int j = 1; j <= rs.getInt(3); j++) {

						pstmtin.setString(1, "2016-12-25 13:00");
						pstmtin.setInt(2, 1);
						pstmtin.setString(3, String.format("%02d-%02d", i, j));
						pstmtin.executeUpdate();
					}
				}
			}
			pstmtin.close();
			rs.close();
			pstmtup.close();
			pstmt.close();
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
