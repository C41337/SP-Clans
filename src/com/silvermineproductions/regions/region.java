package com.silvermineproductions.regions;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysqlcmd;
import com.silvermineproductions.regions.regions_function;

public class region 
{	
	static HashMap<String, Location> hashloc1 = new HashMap<String, Location>();
	static HashMap<String, Location> hashloc2 = new HashMap<String, Location>();
	
	public static void exec_region(Player p, String[] args)
	{		
		if(mysqlcmd.check_Leader(p))
		{
			if(args[1].equalsIgnoreCase("create"))
			{
				Location loc1 = hashloc1.get(p.getName());
				Location loc2 = hashloc2.get(p.getName());
				String clName = mysqlcmd.getclName(mysqlcmd.getClidofMember(p.getName()));
				if(loc1 == null)
				{
					p.sendMessage("§cPlease set the first point");
				} 
				else if(loc2 == null)
				{
					p.sendMessage("§cPlease set the second point");
				}
				else if(loc1.getWorld() != loc2.getWorld())
				{
					p.sendMessage("§cThe points must be in the same world");
				}
				else if(regions_function.Volume(loc1, loc2) > 2000)
				{
					p.sendMessage(ChatColor.RED + "You define " + regions_function.Volume(loc1, loc2) + "/2000");
				}
				else if(!regions_function.check_2regions(loc1, loc2))
				{
					p.sendMessage(ChatColor.RED + "Your defined region is too near to another clans region");
				}
				else if(regions_function.check_region(clName))
				{
					regions.updateRegions(clName, loc1, loc2);
					p.sendMessage(ChatColor.GREEN + "Your clan region is succesfully updated");
				}
				else
				{
					regions.insertRegions(clName, loc1, loc2);
					p.sendMessage(ChatColor.GREEN + "Your clan region is succesfully set");
				}
				
			}
			else
			{
				p.sendMessage("/clan rg create");
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have permissions to perform this command");
		}
	}
	

}
