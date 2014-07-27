package com.silvermineproductions.member_functions;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class home 
{
	
	private static HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	public static int cooldown = 0;
	
	
	public static boolean sethome(Player p, String[] args)
	{		
		if(args.length == 1)
		{
			if(mysqlcmd.check_Leader(p) || mysqlcmd.check_Moderator(p))
			{			
				String loc = mysqlcmd.serial(p.getLocation());
				mysql.update("UPDATE clans SET clHome='" + loc + "' WHERE clid='" + mysqlcmd.getClidofMember(p.getName()) + "'");
				p.sendMessage(ChatColor.GOLD + "Clans Home set");
				return true;
			}
			else
			{
				p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
				return true;
			}
		}
		if(args.length == 2)
		{
			if(p.hasPermission("Clans.sethome"))
			{
				String loc = mysqlcmd.serial(p.getLocation());
				mysql.update("UPDATE clans SET clHome='" + loc + "' WHERE clid='" + mysqlcmd.getClid(args[2]) + "'");
				p.sendMessage(ChatColor.GOLD + "Clans Home set");
				
				return true;
			}
			else
			{
				p.sendMessage(ChatColor.RED + "You don't have Permissions to perform this command!");
				return true;
			}
		}
		else
		{
			p.sendMessage("/clan sethome");
			return true;
		}
	}
	
	public static boolean tphome(Player p)
	{
		if(mysqlcmd.getclHome(p.getName()) != "")
		{
		     
			Location loc = mysqlcmd.deserial(mysqlcmd.getclHome(p.getName()));
			if(loc == null)
			{
				p.sendMessage(ChatColor.RED + "Your Clans home is not set");
				return true;
			}
			
			Long time = System.currentTimeMillis();
			if(cooldowns.containsKey(p.getName()))
			{
				Long lastUsage = cooldowns.get(p.getName());
				
				if(lastUsage + cooldown*1000 > time)
				{
					Long stilltime = ((lastUsage + cooldown*1000) - time) / 1000;
					p.sendMessage("" + ChatColor.RED + stilltime + "sec " + ChatColor.WHITE + "cooldown");
					return true;
				}
			}
			
			p.teleport(loc);
			cooldowns.put(p.getName(), time);
			return true;
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You aren't part of any Clan!!");
			return true;
		}
	}
}
