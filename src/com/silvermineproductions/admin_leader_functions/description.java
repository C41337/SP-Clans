package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class description 
{
	public static boolean setdescription(Player p, String[] args)
	{
		if(args.length >= 1)
		{
			if(mysqlcmd.leader(p.getName()) != 0 || p.hasPermission("Clans.setDesc"))
			{
				String desc = "";
				
				for(int i = 1; i <= (args.length - 1); i++)
				{
					desc = desc + args[i] + " ";					
				}
				
				mysql.update("UPDATE clans SET clDescription='" + desc + "' WHERE clid='" + mysqlcmd.leader(p.getName()) + "'");
				p.sendMessage(ChatColor.GREEN + "Your Clan-Description has been updated!");
				
				return true;
			}
			else
			{
				p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
				return true;
			}
		}
		else
		{
			p.sendMessage("/clan setDesc <text>");
			return true;
		}
		
	}
}
