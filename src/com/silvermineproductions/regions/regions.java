package com.silvermineproductions.regions;

import org.bukkit.Location;
import org.bukkit.World;
import com.silvermineproductions.mysql.mysql;

public class regions 
{
	
	public static void updateRegions(String clName, Location loc1, Location loc2)
	{
		int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		World world = loc1.getWorld();
		
		mysql.update("UPDATE region SET " + 
				"minX='" + minX + "', " +
				"minY='" + minY + "', " +
				"minZ='" + minZ + "', " +
				"maxX='" + maxX + "', " +
				"maxY='" + maxY + "', " +
				"maxZ='" + maxZ + "', " +
				"world='" + world.getName() + "' " +
				"WHERE name='" + clName + "'");
	}
	
	public static void insertRegions(String clName, Location loc1, Location loc2)
	{
		int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		World world = loc1.getWorld();
		
		mysql.update("INSERT INTO region (name, minX, minY, minZ, maxX, maxY, maxZ, world) VALUES("
				+ "'" + clName +"',"
				+ "'" + minX +"',"
				+ "'" + minY +"',"
				+ "'" + minZ +"',"
				+ "'" + maxX +"',"
				+ "'" + maxY +"',"
				+ "'" + maxZ +"',"
				+ "'" + world.getName() +"'"
				+ ")");
	}
}
