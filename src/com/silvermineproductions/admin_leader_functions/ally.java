package com.silvermineproductions.admin_leader_functions;

import java.sql.ResultSet;
import java.sql.Statement;

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
			if(args.length == 1)
			{
				allReq(p, mysqlcmd.getClidofMember(p.getName()));
			}
			else if(args.length == 2)
			{
				if(mysqlcmd.check_Clan_Name(args[1]))
				{
					int clid1 = mysqlcmd.getClidofMember(p.getName());
					
					if(mysqlcmd.check_ally_list(mysqlcmd.getclName(clid1), args[1]) == false)
					{
						mysql.update("INSERT INTO ally (clid1, clid2) VALUES ('" + clid1 + "', '" + mysqlcmd.getClid(args[1]) + "')");
						
						String stleader = mysqlcmd.getMemberName(mysqlcmd.getleader(args[1]));
						if(Bukkit.getPlayer(stleader) != null)
						{
							Bukkit.getPlayer(stleader).sendMessage(mysqlcmd.clName(p) + " want to be your ally");
						}
						p.sendMessage(ChatColor.GREEN + "You succesfully send to " + args[1] + "");
					}
					else
					{
						p.sendMessage(ChatColor.RED + "You are already allies");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Clan " + args[1] + " doesn't exist!");
				}
			}
			else if(args.length == 3)
			{
				if(mysqlcmd.check_ally_request(args[1], mysqlcmd.getclName(mysqlcmd.getClidofMember(p.getName()))))
				{
					int clid1 = mysqlcmd.getClid(args[1]);
					int clid2 = mysqlcmd.getClidofMember(p.getName());
					if(args[2].equalsIgnoreCase("yes"))
					{
						if(mysqlcmd.check_war(clid1, clid2))
						{
							mysql.update("DELETE FROM war WHERE "
									+ "(clid1='" + clid1 + "' AND clid2='" + clid2 + "') OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
						}
						mysql.update("UPDATE ally SET assume='1' WHERE clid1='" + clid1 + "' AND clid2='" + clid2 + "'");
						p.sendMessage(ChatColor.GREEN + "You succesfully accept the ally request");
					}
					else if(args[2].equalsIgnoreCase("no"))
					{
						mysql.update("DELETE FROM ally WHERE clid1='" + clid1 + "' AND clid2='" + clid2 + "'");
						p.sendMessage(ChatColor.GREEN + "You succesfully reject the ally request");
					}
					else
					{
						p.sendMessage("/clan ally <clan name> [yes|no]");
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "You have no ally request from " + args[1]);
				}
				
			}
			else
			{
				p.sendMessage("/clan ally <clan name> [yes|no]");
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You don't have permissions to perform this command");
		}
	}
	
	private static void allReq(Player p, int clid)
	{
		try{
			Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM ally WHERE clid2='" + clid + "' AND assume='0'");
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count == 0)
	        {
	        	p.sendMessage(ChatColor.BLUE + "Ally Requests\n--------------");
	        	p.sendMessage(ChatColor.RED + "You have no ally requests");
	        }
	        else
	        {
	        	p.sendMessage(ChatColor.BLUE + "Ally Requests\n--------------");
		        rs.beforeFirst();
		        while(rs.next())
		        {
		        	if(rs.getInt("clid1") != clid)
		        	{
		        		p.sendMessage(ChatColor.GOLD + mysqlcmd.getclName(rs.getInt("clid1")));
		        	}
		        	if(rs.getInt("clid2") != clid)
		        	{
		        		p.sendMessage(ChatColor.GOLD + mysqlcmd.getclName(rs.getInt("clid2")));
		        	}
		        }
	        }
			
			
		}catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
