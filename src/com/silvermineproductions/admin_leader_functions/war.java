package com.silvermineproductions.admin_leader_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;


public class war 
{
	public war(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p))
		{
			if(mysqlcmd.check_Clan_Name(args[1]))
			{
				int clid1 = mysqlcmd.getClidofMember(p.getName()),
						clid2 = mysqlcmd.getClid(args[1]);
				String clName1 = mysqlcmd.clName(p),
						clName2 = args[1];
				
				if(mysqlcmd.check_war(clid1, clid2))
				{
					
				}
				else if(mysqlcmd.check_allies(clName1, clName2))
				{
					mysql.update("DELETE FROM ally WHERE "
							+ "(clid1='" + clid1 + "' AND clid2='" + clid2 + "') OR (clid1='" + clid2 + "' AND clid2='" + clid2 + "')");
					mysql.update("INSERT INTO war (clid1, clid2) VALUES ('" + clid1 + "', '" + clid2 + "')");
					p.sendMessage(ChatColor.GREEN + "You succesfully declare war on " + clName2);
				}
				else
				{
					mysql.update("INSERT INTO war (clid1, clid2) VALUES ('" + clid1 + "', '" + clid2 + "')");
					p.sendMessage(ChatColor.GREEN + "You succesfully declare war on " + clName2);
				}
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You have no permissions to perform this command");
		}
	}

}
