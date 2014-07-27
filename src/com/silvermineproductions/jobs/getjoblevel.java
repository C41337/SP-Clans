package com.silvermineproductions.jobs;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;

public class getjoblevel 
{
	public static int getLevel(Player p)
	{
		int lvl = 0;
		
		try{
        	Statement stmt = mysql.Jcon.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT level FROM jobs WHERE username='" + p.getName() + "'");
        	while (rs.next()) {
            	lvl = rs.getInt("level");
            }
               
        }catch(Exception e){
                System.err.println(e);
        }
		
		return lvl;
	}
}
