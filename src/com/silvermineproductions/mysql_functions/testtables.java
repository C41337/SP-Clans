package com.silvermineproductions.mysql_functions;

import java.sql.ResultSet;
import java.sql.Statement;

import com.silvermineproductions.mysql.mysql;

public class testtables 
{
	public static boolean test_table(String table)
	{
		try{
			
			Statement stmt = mysql.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='" + mysql.db + "' AND TABLE_NAME = '" + table +"'");
			rs.last();
			
			if(rs.getRow() > 0)
			{
				return true;
			} else {
				return false;
			}
			
			
			
		} catch(Exception e){
			System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
		}
		
		return false;		
	}
	
	
}
