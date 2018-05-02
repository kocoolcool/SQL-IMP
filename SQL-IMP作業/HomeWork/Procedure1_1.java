package com.iii.eeit101.chris;

import java.sql.*;


// Insert one employee
public class Procedure1_1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {     
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=db02";
			conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
			//用資料庫抓日期新增的寫法並規定寫入格式,最推薦方式為在DB寫入,因為會有時差及時區的問題
			String insStmt = "insert into playlist values ('2016-12-25 13:00', 1, 'A廳')";
			
			PreparedStatement pstmt = conn.prepareStatement(insStmt);
		
			
			int num = pstmt.executeUpdate();
			System.out.println("insert count = " + num);
			
			pstmt = conn.prepareStatement("SELECT * FROM playlist");
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next()) {
				System.out.println("ptime = " + rs.getDate("ptime") );
				System.out.println("movie = " + rs.getInt("movie"));
				System.out.println("roomid=" + rs.getString("roomid"));
			}
			
			String insStmt1 = "if (select roomid  from playlist where ptime='2016-12-25 13:00:00.000')='A廳' \r\n" + 
					"begin\r\n" + 
					"DECLARE   @i INT = 1,@j int = 1;\r\n" + 
					"DECLARE	@v_row int=(select seat_row from  m_room where roomid='A廳');\r\n" + 
					"DECLARE	@v_col int=(select seat_col from  m_room where roomid='A廳');\r\n" + 
					"DECLARE	@str char(5);\r\n" + 
					"end;\r\n" + 
					"BEGIN\r\n" + 
					"  WHILE ( @i<=@v_row)\r\n" + 
					"    BEGIN\r\n" + 
					"	set @str=''\r\n" + 
					"      WHILE ( @j <=@v_col )\r\n" + 
					"        BEGIN\r\n" + 
					"		insert into seats values ('2016-12-25 13:00', 1, format(@i,'00')+'-'+format(@j,'00'), '0', NULL)	\r\n" + 
					"	SET @j+=1      \r\n" + 
					"    END;\r\n" + 
					"    set @i+=1\r\n" + 
					"     set @j=1\r\n" + 
					"   END;\r\n" + 
					"END;";
			
			PreparedStatement pstmt1 = conn.prepareStatement(insStmt1);
			int num1= pstmt1.executeUpdate();
			System.out.println("num1="+num1);
			pstmt = conn.prepareStatement("SELECT CONVERT(varchar(16), ptime ,121) as ptime,movie,seat_num,sold,ordid FROM seats");
			ResultSet rs1 = pstmt.executeQuery();
			int count=0;
			while(rs1.next()) {
			System.out.println( rs1.getString("ptime")+"  "+ rs1.getInt("movie")+ "  "+rs1.getString("seat_num")+"  "+rs1.getString("sold")+"  "+ rs1.getString("ordid"));
			count++;
			}
			System.out.println("count"+count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch(SQLException e) { 
					e.printStackTrace();
				}
		}
	}// end of main()
}// end of class InsertDemo
