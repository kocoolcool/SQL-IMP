package com.iii.eeit101.chris;

import java.sql.*;

// Insert one employee
public class Procedure2 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=db02";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			// 用資料庫抓日期新增的寫法並規定寫入格式,最推薦方式為在DB寫入,因為會有時差及時區的問題
			String insStmt = "insert into playlist values ('2016-12-25 13:00', 1, 'A廳')";

			PreparedStatement pstmt = conn.prepareStatement(insStmt);

			int num = pstmt.executeUpdate();
			System.out.println("insert count = " + num);

			pstmt = conn.prepareStatement("SELECT * FROM playlist");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("ptime = " + rs.getDate("ptime"));
				System.out.println("movie = " + rs.getInt("movie"));
				System.out.println("roomid=" + rs.getString("roomid"));
			}
			
			CallableStatement cstmt = conn.prepareCall("{call gen_seats(?,?,?)}");
			cstmt.setString(1, "2016-12-25 13:00");
			cstmt.setInt(2,1);
			cstmt.setString(3, "A廳");
			cstmt.execute();
			
			
			pstmt= conn.prepareStatement("SELECT CONVERT(varchar(16), ptime ,121) as ptime,movie,seat_num,sold,ordid FROM seats");
			ResultSet rs2 = pstmt.executeQuery();
			int count=0;
			while(rs2.next()) {
			System.out.println( rs2.getString("ptime")+"  "+ rs2.getInt("movie")+ "  "+rs2.getString("seat_num")+"  "+rs2.getString("sold")+"  "+ rs2.getString("ordid"));
			count++;
			}
			System.out.println("count" + count);
		} catch (SQLException e) {
			e.printStackTrace();
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
