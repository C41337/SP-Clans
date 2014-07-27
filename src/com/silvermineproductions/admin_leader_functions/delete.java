package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class delete 
{
	public static boolean delete_Clan(Player p, String clName)
	{
		if(p.hasPermission("Clans.del"))
		{
			if(mysqlcmd.check_Clan_Name(clName))
			{
				mysql.update("UPDATE member SET clid=0 WHERE clid='" + mysqlcmd.getClid(clName) + "'");
				mysql.update("DELETE FROM leader WHERE clid='" + mysqlcmd.getClid(clName) + "'");
				mysql.update("DELETE FROM moderator WHERE clid='" + mysqlcmd.getClid(clName) + "'");
				mysql.update("DELETE FROM clans WHERE clName='" + clName + "'");
				
				p.sendMessage("§a You succesfully deleted the Clan §9" + clName);
				
				return true;
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Clan " + clName + " doesnt exist");
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
