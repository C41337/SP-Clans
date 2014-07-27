package com.silvermineproductions.jobs;

import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;

public class getjob 
{
	public static String getJob(Player p)
	{
		String name = "";
		
		try{
        	Statement stmt = mysql.Jcon.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT job FROM jobs WHERE username='" + p.getName() + "'");
        	
        	rs.last();
        	if(rs.getRow() == 0)
        	{
        		return "";
        	}
        	else
        	{
        		rs.beforeFirst();
	        	while (rs.next()) {
	            	name = rs.getString("job");
	            }
        	}
               
        }catch(Exception e){
                System.err.println(e);
        }
		
		name = config.getJobsConfig(name);
		
		return name;
	}
}
