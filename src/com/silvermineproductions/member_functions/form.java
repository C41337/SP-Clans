package com.silvermineproductions.member_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.cmd.clanCmd;
import com.silvermineproductions.main.main;
import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;


public class form 
{
	
	@SuppressWarnings("deprecation")
	public static boolean form_clan(Player p, String[] args)
	{
		if(args.length == 2)
		{
			if(mysqlcmd.check_Clan_Name(args[1]) == false)
			{
				if(mysqlcmd.check_Leader(p) == false)
				{
					if(main.economy.has(p.getName(), clanCmd.form_price))
					{
						main.economy.withdrawPlayer(p.getName(), clanCmd.form_price);
						
						mysql.update("INSERT INTO clans (clName) VALUES ('" + args[1] + "')");
						int clid = mysqlcmd.getClid(args[1]);
						int mid = 0;
						String pName = p.getName();
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
						p.sendMessage("§2[§fMoney§2] Balance: §f" + main.economy.getBalance(p.getName()) + " " + main.economy.currencyNamePlural());
						return true;
					}
					else
					{
						p.sendMessage(ChatColor.RED + "You need " + clanCmd.form_price + " to buy a clan");
						p.sendMessage("§2[§fMoney§2] Balance: §f" + main.economy.getBalance(p.getName()) + " " + main.economy.currencyNamePlural());
						return true;
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "You cant be leader of more than one clan");
					p.sendMessage(ChatColor.RED + "Please type " + ChatColor.WHITE + "/clan form <name> <leader>"
							+ ChatColor.RED + " to buy a clan for another player");
					return true;
				}
				
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Clan Name is already used!");
				return true;
			}
		}
		if(args.length == 3)
		{
			if(mysqlcmd.check_Clan_Name(args[1]) == false)
			{
				if(mysqlcmd.check_Clan_Name(args[1]) == false)
				{
					if(main.economy.has(p.getName(), clanCmd.form_price))
					{
						main.economy.withdrawPlayer(p.getName(), clanCmd.form_price);
						
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
						p.sendMessage("§2[§fMoney§2] Balance: §f" + main.economy.getBalance(p.getName()) + " " + main.economy.currencyNamePlural());
						return true;
					}
					else
					{
						p.sendMessage(ChatColor.RED + "You need " + clanCmd.form_price + " to buy a clan");
						p.sendMessage("§2[§fMoney§2] Balance: §f" + main.economy.getBalance(p.getName()) + " " + main.economy.currencyNamePlural());
						return true;
					}
				
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Clan Name is already used!");
					return true;
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Clan Name is already used!");
				return true;
			}	
		}
		else
		{
			p.sendMessage("/clan form <name> [leader]");
			return true;
		}
	}
}