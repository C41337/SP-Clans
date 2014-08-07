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
		//Koordinaten holen
		int minX1 = Math.min(loc1.getBlockX(), loc2.getBlockX());
		int minY1 = Math.min(loc1.getBlockY(), loc2.getBlockY());
		int minZ1 = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
		
		int maxX1 = Math.max(loc1.getBlockX(), loc2.getBlockX());
		int maxY1 = Math.max(loc1.getBlockY(), loc2.getBlockY());
		int maxZ1 = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
		
		//in Punkt A umwandeln
		double[] PunktA1 = new double[3];
		PunktA1[0]= minX1;
		PunktA1[1]= minY1;
		PunktA1[2]= minZ1;
		
		//Mittelpunkt1 bestimmen
		double[] Mittelpunkt1 = new double[3];
		Mittelpunkt1 = Mittelpunkt(maxX1, maxY1, maxZ1, minX1, minY1, minZ1);
		
		double[] vektor1 = new double[3];
		vektor1 = vektorAS(PunktA1, Mittelpunkt1);
		
		//radius1 bestimmen
		double r1 = vektorlaenge(vektor1);
		
		World world = loc1.getWorld();
		
		try {
			Statement stmt = mysql.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM region");
			
			while(rs.next())
			{
				//Koordinaten aus Datenbank holen
				int maxX2 = rs.getInt("maxX");
				int maxY2 = rs.getInt("maxY");
				int maxZ2 = rs.getInt("maxZ");
				
				int minX2 = rs.getInt("minX");
				int minY2 = rs.getInt("minY");
				int minZ2 = rs.getInt("minZ");
				
				//Koordinaten in Punkt A2 umwandeln
				double[] PunktA2 = new double[3];
				PunktA2[0]= minX2;
				PunktA2[1]= minY2;
				PunktA2[2]= minZ2;
				
				World sqlWorld = Bukkit.getWorld(rs.getString("world"));
				
				if(world != sqlWorld)
				{
					return false;
				}
				
				//Mittelpunkt2 bestimmen
				double[] Mittelpunkt2 = new double[3];
				Mittelpunkt2 = Mittelpunkt(maxX2, maxY2, maxZ2, minX2, minY2, minZ2);
				
				//vektor2 bestimmen
				double[] vektor2 = new double[3];
				vektor2 = vektorAS(PunktA2, Mittelpunkt2);
				
				//radius berechnen
				double r2 = vektorlaenge(vektor2);
				
				
				double Mittelwert3[] = new double[3];
				Mittelwert3[0] = Math.abs(Mittelpunkt1[0] - Mittelpunkt2[0]);
				Mittelwert3[1] = Math.abs(Mittelpunkt1[1] - Mittelpunkt2[1]);
				Mittelwert3[2] = Math.abs(Mittelpunkt1[2] - Mittelpunkt2[2]);
				
				double entfernung = vektorlaenge(Mittelwert3);
				
				if(entfernung > (r1 + r2 + 10))
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
		
		Mittelpunkt[0] = minX + (0.5 * (maxX - minX));
		Mittelpunkt[1] = minY + (0.5 * (maxY - minY));
		Mittelpunkt[2] = minZ + (0.5 * (maxZ - minZ));
		
		return Mittelpunkt;
	}
	private static double[] vektorAS(double[] A, double[] S)
	{
		double[] vektor = new double[3];
		
		vektor[0] = S[0] - A[0];
		vektor[1] = S[1] - A[1];
		vektor[2] = S[2] - A[2];
		
		return vektor;
	}
}
