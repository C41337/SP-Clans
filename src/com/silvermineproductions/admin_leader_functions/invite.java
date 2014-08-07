package com.silvermineproductions.admin_leader_functions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class invite 
{
	@SuppressWarnings("deprecation")
	public static boolean invitePlayer(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p))
		{
			if(args.length == 2)
			{
				if(mysqlcmd.check_Member_Name(args[1]))
				{
					mysql.update("INSERT INTO invite (clid, mid) VALUES ('" + mysqlcmd.getClidofMember(p.getName()) 
							+ "', '" + mysqlcmd.getMid(args[1]) + "')");
					
					p.sendMessage("You succesfully invited " + args[1] + " to your Clan");	
					
					
					Player rec = Bukkit.getPlayer(args[1]);
					if(rec != null)
					{
						rec.sendMessage(ChatColor.GREEN + "You are invited by " + p.getName() + " to join the Clan " 
								+ mysqlcmd.clName(p));
					}

					return true;
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Player " + args[1] +" was never on this server!");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clan inv <name>");
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
