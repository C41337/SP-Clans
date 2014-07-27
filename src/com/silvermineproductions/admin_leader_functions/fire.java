package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class fire 
{
	public static boolean clFire(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p) || mysqlcmd.check_Moderator(p))
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Member_Name(args[1]))
				{
					if(mysqlcmd.getClidofMember(args[1]) == mysqlcmd.getClidofMember(p.getName()))
					{
						mysql.update("UPDATE member SET clid='0' WHERE memName='" + args[1] + "'");
						p.sendMessage(ChatColor.GREEN + "Succesfully fired " + args[1]);
						
						p.performCommand("mail send " + args[1] + " You are fired");				
						return true;
					}
					else
					{
						p.sendMessage(ChatColor.RED + args[1] + " isnt part of your Clan");
						return true;
					}
					
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Player " + args[1] + "doesnt exist");
					return true;
				}
				
			}
			else
			{
				p.sendMessage("/clan fire <name>");
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
