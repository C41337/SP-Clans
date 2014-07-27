package com.silvermineproductions.ally_war;

import java.sql.ResultSet;
import java.sql.Statement;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class ally_functions 
{
	public static String get_allies(String clName)
	{		
		String allies = "";
		try{
			
			int ownClid = mysqlcmd.getClid(clName);
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM ally WHERE (clid1=" + ownClid + " OR clid2=" + ownClid + ") AND assume='1'");
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count == 0)
	        {
	        	return "";
	        }
	        
	        rs.beforeFirst();
	        while(rs.next())
	        {
	        	if(rs.getInt("clid1") != ownClid)
	        	{
	        		allies = allies + mysqlcmd.getclName(rs.getInt("clid1")) + "\n";
	        	}
	        	if(rs.getInt("clid2") != ownClid)
	        	{
	        		allies = allies + mysqlcmd.getclName(rs.getInt("clid2")) + "\n";
	        	}
	        }
			return allies;
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		
		return allies;
	}

}
