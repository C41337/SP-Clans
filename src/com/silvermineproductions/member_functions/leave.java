package com.silvermineproductions.member_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class leave 
{
	public static boolean leaveMem(Player p)
	{
		if(mysqlcmd.getClidofMember(p.getName()) != 0)
		{
			if(mysqlcmd.leader(p.getName()) != 0)
			{
				p.sendMessage(ChatColor.RED + "You are Leader of a Clan, please change Leader or ask a Admin to leave your Clan");
				return true;
			}
			else
			{
				mysql.update("UPDATE member SET clid='0' WHERE memName='" + p.getName() + "'");
				p.sendMessage(ChatColor.GREEN + "Succesfully leave the Clan  " + mysqlcmd.clName(p));
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You arent part of any Clan");
			return true;
		}
	}

}
