package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class createClan 
{
	public static boolean create(Player p, String[] args)
	{
		if(p.hasPermission("Clans.create"))
		{
			if(args.length != 3)
			{
				p.sendMessage("/clan create <name> <leader>");
				return true;
			} else
			{
				
				if(mysqlcmd.check_Clan_Name(args[1]) == false)
				{
				
					mysql.update("INSERT INTO clans (clName) VALUES ('" + args[1] + "')");
					int clid = mysqlcmd.getClid(args[1]);
					int mid = 0;
					String pName = args[2];
					if(mysqlcmd.check_Member_Name(pName) == false)
					{
						mysql.update("INSERT INTO member (memName, clid) VALUES ('" + pName + "', '" + clid +"')");
						mid = mysqlcmd.getMid(pName);
						mysql.update("INSERT INTO leader (mid, clid, messages) VALUES ('" + mid + "', '" + clid + "', '0')");
					} else
					{
						mysql.update("UPDATE member SET clid='" + clid + "' WHERE memName='" + pName + "'");
						mid = mysqlcmd.getMid(pName);
						mysql.update("INSERT INTO leader (mid, clid, messages) VALUES ('" + mid + "', '" + clid + "', '0')");
					}
					
					p.sendMessage(ChatColor.GREEN + "You succesfully created the Clan " + ChatColor.BLUE + args[1]);
					
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.RED + "The Clan Name " + ChatColor.GREEN + args[1] + ChatColor.RED + " is already taken!");
				}
			}
		} else
		{
			p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
			return true;
		}
		return false;
	}
}
