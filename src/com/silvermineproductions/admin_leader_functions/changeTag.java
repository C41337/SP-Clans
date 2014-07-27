package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class changeTag 
{
	public static boolean change(Player p, String[] args)
	{
		if(args.length == 2)
		{
			if(mysqlcmd.check_Leader(p) || mysqlcmd.check_Moderator(p))
			{
					if(args[1].length() < 7)
					{
						if(mysqlcmd.check_Clan_Tag(args[1]) == false)
						{
							mysql.update("UPDATE clans SET clTag = '" + args[1] + "' WHERE clid = '" + mysqlcmd.leader(p.getName()) + "'");
							p.sendMessage(ChatColor.GREEN + "Clan-Tag has been succesfully changed");
							return true;
						} else
						{
							p.sendMessage(ChatColor.RED + args[1] + " is already taken!");
							return true;
						}
					} else
					{
						p.sendMessage(ChatColor.RED + "The Clan-Tag has to be less than 7 letters.");
						return true;
					}
			}
			else {
				p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
				return true;
			}
		}
		if(args.length == 3)
		{

			if(p.hasPermission("Clans.tag"))
			{
					if(mysqlcmd.check_Clan_Name(args[1]))
						if(args[2].length() <= 7)
						{
							if(mysqlcmd.check_Clan_Tag(args[2]) == false)
							{
								mysql.update("UPDATE clans SET clTag = '" + args[2] + "' WHERE clid = '" + mysqlcmd.getClid(args[1]) + "'");
								p.sendMessage(ChatColor.GREEN + "Clan-Tag has been succesfully changed");
								return true;
							} else
							{
								p.sendMessage(ChatColor.RED + args[2] + " is already taken!");
								return true;
							}
						} else
						{
							p.sendMessage(ChatColor.RED + "The Clan-Tag has to be less than 7 letters.");
							return true;
						}
					else {
						p.sendMessage(ChatColor.RED + "The Clan " + args[1] + " does not exist");
						return true;
					}
				
			}
			
			
	}
	else
	{
		p.sendMessage("/clan tag <CL-Tag>");
		return true;
	}
		
		return false;	
	}
}
