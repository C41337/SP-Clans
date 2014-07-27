package com.silvermineproductions.regions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.silvermineproductions.mysql.mysql;

public class regions_function 
{
	public static boolean check_region(String name)
	{
		Statement stmt;
		try {
			
			stmt = mysql.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1 FROM region WHERE name='" + name + "'");
			rs.next();			
			if(rs.getInt("1") == 1)
			{
				return true;
			}
			else
			{
				return false;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static int Volume(Location loc1, Location loc2)
	{
		int V = 0;
		
		int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		int l = maxX - minX;
		int b = maxY - minY;
		int h = maxZ- minZ;
		
		V = l*b*h;
		
		return V;
	}
	
	public static boolean check_2regions(Location loc1, Location loc2)
	{
		int minX1 = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY1 = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ1 = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		int maxX1 = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY1 = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ1 = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		double[] Mittelwert1 = new double[3];
		Mittelwert1 = Mittelpunkt(maxX1, maxY1, maxZ1, minX1, minY1, minZ1);
		
		double r1 = vektorlaenge(Mittelwert1);
		
		World world = loc1.getWorld();
		
		try {
			Statement stmt = mysql.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM region");
			
			while(rs.next())
			{
				int maxX2 = rs.getInt("maxX");
				int maxY2 = rs.getInt("maxY");
				int maxZ2 = rs.getInt("maxZ");
				
				int minX2 = rs.getInt("minX");
				int minY2 = rs.getInt("minY");
				int minZ2 = rs.getInt("minZ");
				
				World sqlWorld = Bukkit.getWorld(rs.getString("world"));
				
				if(world != sqlWorld)
				{
					return false;
				}
				double[] Mittelwert2 = new double[3];
				Mittelwert2 = Mittelpunkt(maxX2, maxY2, maxZ2, minX2, minY2, minZ2);
				double r2 = vektorlaenge(Mittelwert2);
				
				double Mittelwert3[] = new double[3];
				Mittelwert3[0] = Math.abs(Mittelwert1[0] - Mittelwert2[0]);
				Mittelwert3[1] = Math.abs(Mittelwert1[1] - Mittelwert2[1]);
				Mittelwert3[2] = Math.abs(Mittelwert1[2] - Mittelwert2[2]);
				
				double entfernung = vektorlaenge(Mittelwert3);
				
				if(entfernung > r1 + r2 + 10)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static double vektorlaenge(double[] mw)
	{
		double r = 0;
		r = Math.sqrt(mw[0]*mw[0] + mw[1]*mw[1] + mw[2]*mw[2]);		
		return r;
	}
	
	public static double[] Mittelpunkt(int maxX, int maxY, int maxZ, int minX, int minY, int minZ)
	{
		double[] Mittelpunkt = new double[3];
		
		Mittelpunkt[0] = 0.5 * (maxX - minX);
		Mittelpunkt[1] = 0.5 * (maxY - minY);
		Mittelpunkt[2] = 0.5 * (maxZ - minZ);
		
		return Mittelpunkt;
	}
}
