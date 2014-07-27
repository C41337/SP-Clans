package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class moderator 
{
	public static boolean setMod(Player p, String[] args)
	{
		if(mysqlcmd.leader(p.getName()) != 0)
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Member_Name(args[1]))
				{
					if(mysqlcmd.getClidofMember(args[1]) == mysqlcmd.leader(p.getName()))
					{
						mysql.update("INSERT INTO moderator (mid, clid) VALUES "
								+ "('" + mysqlcmd.getMid(args[1]) + "', '" + mysqlcmd.leader(p.getName()) + "')");
						p.sendMessage(ChatColor.GREEN + "You succesfully promote " + args[1] + " to Moderator");
						
						return true;
					}
					if(mysqlcmd.getClidofMember(args[1]) == 0)
					{
						mysql.update("UPDATE member SET clid='" + mysqlcmd.leader(p.getName()) + "' WHERE memName='" + args[1] + "'");
						mysql.update("INSERT INTO moderator (mid, clid) VALUES "
								+ "('" + mysqlcmd.getMid(args[1]) + "', '" + mysqlcmd.leader(p.getName()) + "')");
						p.sendMessage(ChatColor.GREEN + "You succesfully promote " + args[1] + " to Moderator");
						return true;
					}
					if((mysqlcmd.getClidofMember(args[1]) != mysqlcmd.leader(p.getName())) && mysqlcmd.getClidofMember(args[1]) == 0)
					{
						p.sendMessage(ChatColor.RED + args[1] + " is part of another Clan");
						return true;
					}
					
				}
				else
				{
					p.sendMessage(ChatColor.RED + args[1] + " doesnt exist!");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clan mod <name>");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You dont have Permissions to perform this command");
			return true;
		}
		
		return true;
	}
	
	public static boolean deMod(Player p, String[] args)
	{
		if(mysqlcmd.leader(p.getName()) != 0)
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Member_Name(args[1]))
				{
					if(mysqlcmd.getClidofMember(args[1]) == mysqlcmd.leader(p.getName()))
					{
						mysql.update("DELETE FROM moderator WHERE mid='" + mysqlcmd.getMid(args[1]) + "'");
						p.sendMessage(ChatColor.GREEN + "You succesfully deomote " + args[1] + " to Member");
						return true;
					}
					else
					{
						p.sendMessage(ChatColor.RED + args[1] + " is part of another Clan");
						return true;
					}
					
				}
				else
				{
					p.sendMessage(ChatColor.RED + args[1] + " doesnt exist!");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clan mod <name>");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You dont have Permissions to perform this command");
			return true;
		}
	}

}
