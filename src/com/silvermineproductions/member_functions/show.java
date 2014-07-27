package com.silvermineproductions.member_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.ally_war.ally_functions;
import com.silvermineproductions.ally_war.war_functions;
import com.silvermineproductions.mysql.mysqlcmd;

public class show 
{
	public static boolean showInf(String[] args, Player p)
	{
		if(mysqlcmd.check_Clan_Name(args[0]))
		{
			if(args.length == 1)
			{
				int clid = mysqlcmd.getClid(args[0]);
				
				p.sendMessage("\n" + ChatColor.AQUA + args[0] + " (" + list.onlineMember(clid) 
						+ "/" + mysqlcmd.all_Member(clid) + ")\n---------------------");
				p.sendMessage(ChatColor.YELLOW + "Tag: " + mysqlcmd.clTag(args[0]));
				p.sendMessage(ChatColor.YELLOW + "Desc. : " + mysqlcmd.clDesc(args[0]));
				p.sendMessage(ChatColor.YELLOW + "Leader : " + mysqlcmd.getMemberName(mysqlcmd.getleader(args[0])));
				p.sendMessage(ChatColor.YELLOW + "Moderator : " + mysqlcmd.getMemberName(mysqlcmd.getModerator(args[0])));
				p.sendMessage(ChatColor.YELLOW + "Member : " + mysqlcmd.get_all_member(args[0]));
				return true;
			}
			if(args.length == 2)
			{
				if(args[1].equalsIgnoreCase("ally"))
				{
					String allies = ally_functions.get_allies(args[0]);
					if(allies == "")
					{
						p.sendMessage(ChatColor.RED + args[0] + " has no allies");
						return true;
					}
					p.sendMessage(ChatColor.GOLD + "Allies of " + args[0] + ":");
					p.sendMessage(ChatColor.GOLD + "----------------");
					p.sendMessage(allies);
					return true;
				}
				if(args[1].equalsIgnoreCase("war"))
				{
					String enemies = war_functions.get_enemies(args[0]);
					if(enemies == "")
					{
						p.sendMessage(ChatColor.RED + args[0] + " has no war with any Clan");
						return true;
					}
					p.sendMessage(ChatColor.DARK_RED + args[0] + " is in war with:");
					p.sendMessage(ChatColor.DARK_RED + "----------------");
					p.sendMessage(enemies);
					return true;
				}
				else
				{
					p.sendMessage("/clan " + args[0] + " [ally, war]");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clan " + args[0] + " [ally, war]");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + args[0] + " doesnt exist!");
			return true;
		}
	}
}
