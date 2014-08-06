package com.silvermineproductions.admin_leader_functions;

import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;


public class war 
{
	private static HashSet<Player> endWar = new HashSet<Player>();
	
	@SuppressWarnings("deprecation")
	public static void declwar(Player p, String[] args)
	{
		if(mysqlcmd.check_Leader(p))
		{
			if(args.length != 3)
			{
				if(mysqlcmd.check_Clan_Name(args[1]))
				{
					int clid1 = mysqlcmd.getClidofMember(p.getName()),
							clid2 = mysqlcmd.getClid(args[1]);
					String clName1 = mysqlcmd.clName(p),
							clName2 = args[1];
					
					if(mysqlcmd.check_war(clid1, clid2))
					{
						if(endWar.contains(p))
						{
							mysql.update("DELETE FROM war WHERE "
									+ "(clid1='" + clid1 + "' AND clid2='" + clid2 + "') OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
							endWar.remove(p);
							p.sendMessage(ChatColor.GREEN + "You succesfully end the war between " + clName1 + " and  " + clName2);
							
							Player leader = Bukkit.getPlayer(mysqlcmd.getMemberName(mysqlcmd.getleader(clName2)));
							if(leader != null)
							{
								leader.sendMessage(ChatColor.GREEN + p.getName() + " succesfully end the war between " + clName1 + " and  " + clName2);
							}
						}
						else
						{
							Player leader = Bukkit.getPlayer(mysqlcmd.getMemberName(mysqlcmd.getleader(clName2)));
							if(leader != null)
							{
								if(endWar.contains(leader))
								{
									p.sendMessage(ChatColor.RED + "You already send a request to end this war");
								}
								else
								{
									endWar.add(leader);
									p.sendMessage(ChatColor.GREEN + "You succesfully send a request to end this war");
									p.sendMessage(ChatColor.GREEN + "Please wait until " + leader.getName() + " answered your request");
									leader.sendMessage(ChatColor.DARK_RED + p.getName() + " want to the clan between your clan and " + clName1);
									leader.sendMessage(ChatColor.DARK_RED + "This request will be self destroyed after 5 minutes");
									leader.sendMessage(ChatColor.DARK_RED + "Please type in /clan war " + clName1 + " to end the war");
									
									check_endwar_request(p);
								}
							}
							else
							{
								p.sendMessage(ChatColor.RED + "the other clans leader isn't online, please try again later");
							}
						}
					}
					else if(mysqlcmd.check_allies(clName1, clName2))
					{
						mysql.update("DELETE FROM ally WHERE "
								+ "(clid1='" + clid1 + "' AND clid2='" + clid2 + "') OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
						mysql.update("INSERT INTO war (clid1, clid2) VALUES ('" + clid1 + "', '" + clid2 + "')");
						p.sendMessage(ChatColor.GREEN + "You succesfully declare war on " + clName2);
					}
					else
					{
						mysql.update("INSERT INTO war (clid1, clid2) VALUES ('" + clid1 + "', '" + clid2 + "')");
						p.sendMessage(ChatColor.GREEN + "You succesfully declare war on " + clName2);
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "The clan " + args[1] + " doesn't exist");
				}
			}
			else
			{
				p.sendMessage("/clan war <clan name>");
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You have no permissions to perform this command");
		}
	}
	
	private static void check_endwar_request(final Player p)
	{
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Clans"), new Runnable() { 
				@Override
				public void run()
				{
					endWar.remove(p);
					p.sendMessage(ChatColor.RED + "Time is over to end this war!!");
				}
		}, 300*20);
	}

}
