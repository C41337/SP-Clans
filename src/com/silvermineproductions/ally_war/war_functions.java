package com.silvermineproductions.ally_war;

import java.sql.ResultSet;
import java.sql.Statement;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class war_functions 
{
	public static String get_enemies(String clName)
	{		
		String enemy = "";
		try{
			
			int ownClid = mysqlcmd.getClid(clName);
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM war WHERE (clid1=" + ownClid + " OR clid2=" + ownClid + ")");
	        
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
	        		enemy = enemy + mysqlcmd.getclName(rs.getInt("clid1")) + "\n";
	        	}
	        	if(rs.getInt("clid2") != ownClid)
	        	{
	        		enemy = enemy + mysqlcmd.getclName(rs.getInt("clid2")) + "\n";
	        	}
	        }
			return enemy;
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		
		return enemy;
	}
}
