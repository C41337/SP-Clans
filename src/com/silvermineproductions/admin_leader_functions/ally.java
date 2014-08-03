package com.silvermineproductions.admin_leader_functions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class ally 
{
	@SuppressWarnings("deprecation")
	public static void addally(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p))
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Clan_Name(args[1]))
				{
					int clid1 = mysqlcmd.getClidofMember(p.getName());
					
					if(mysqlcmd.check_ally_list(mysqlcmd.getclName(clid1), args[1]) == false)
					{
						mysql.update("INSERT INTO ally (clid1, clid2) VALUES ('" + clid1 + "', '" + mysqlcmd.getClid(args[1]) + "')");
						
						String stleader = mysqlcmd.getMemberName(mysqlcmd.getleader(args[1]));
						if(Bukkit.getPlayerExact(stleader) != null)
						{
							Bukkit.getPlayer(stleader).sendMessage(mysqlcmd.clName(p) + " want to be your ally");
						}
						p.sendMessage(ChatColor.GREEN + "You succesfully send to " + args[1] + "");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "You are already allied");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Clan " + args[1] + " doesn't exist!");
				}
			}
			else
			{
				p.sendMessage("/clan ally <clan name>");
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have permissions to perform this command");
		}
	}
}
