package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class change_Name 
{
	public static boolean changeClName(Player p, String args[])
	{
		if(p.hasPermission("Clans.name"))
		{
			if(args.length == 3)
			{
				if(mysqlcmd.check_Clan_Name(args[1]))
				{
					if(mysqlcmd.check_Clan_Name(args[2]) == false)
					{
						mysql.update("UPDATE clans SET clName='" + args[2] + "' WHERE clName='" + args[1] + "'");
						
						p.sendMessage("§6You succesfully updated the clanname from §9" + args[1] + " §6to §9" + args[2]);
						return true;
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Clan " + args[2] + " already exist");
						return true;	
					}
					
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Clan " + args[1] + " doesnt exist");
					return true;	
				}
				
			}
			else
			{
				p.sendMessage("/clan rename <oldName> <newName>");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
			return true;
		}
	}

}
