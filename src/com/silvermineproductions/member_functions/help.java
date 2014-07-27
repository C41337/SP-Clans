package com.silvermineproductions.member_functions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.cmd.clanCmd;
import com.silvermineproductions.mysql.mysqlcmd;

public class help 
{
	public static boolean helps(String[] args, Player p)
	{
		if(args.length == 1)
		{		
			p.sendMessage(ChatColor.BLUE + "Clans help");
			p.sendMessage(ChatColor.BLUE + "-----------------------------------------------------");
			p.sendMessage(ChatColor.YELLOW + "/clan form <name> [leader]   //form a new clan for " + clanCmd.form_price);
			p.sendMessage(ChatColor.YELLOW + "/clan list,l   //Show all clans");
			p.sendMessage(ChatColor.YELLOW + "/clan join <Clan Name>   //Applcate to a Clan");
			p.sendMessage(ChatColor.YELLOW + "/clan req [clan name] [yes|no]   //Answer a request");
			p.sendMessage(ChatColor.YELLOW + "/clan leave   //Leave your  Clan");
			p.sendMessage(ChatColor.YELLOW + "/clan home    //Teleports to the Clans home point");
			p.sendMessage(ChatColor.YELLOW + "/clan <Clan Name>    //Show all Information about the Clan");
			p.sendMessage(ChatColor.YELLOW + "/clan <Clan Name> [ally, war]   //Show the allies or enemies of a clan");
			if(mysqlcmd.leader(p.getName()) != 0 || p.hasPermission("Clans.*"))
			{
				p.sendMessage(ChatColor.DARK_RED + "/clan help leader   //Leader Commands");
			}
			if(p.hasPermission("Clans.*"))
			{
				p.sendMessage(ChatColor.DARK_RED + "/clan help admin   //admin Commands");
			}
			return true;
		}
		else
		{
		
			if(args[1].equalsIgnoreCase("leader") && (mysqlcmd.leader(p.getName()) != 0 || p.hasPermission("Clans.*")))
			{
				p.sendMessage(ChatColor.DARK_RED + "Clans Leader help");
				p.sendMessage(ChatColor.DARK_RED + "-----------------------------------------------------");
				p.sendMessage(ChatColor.YELLOW + "/clan fire <name>    //Fires a Member");
				p.sendMessage(ChatColor.YELLOW + "/clan inv <name>    //Invites a player to your Clan");
				p.sendMessage(ChatColor.YELLOW + "/clan tag <tag>    //Change CLAN-Tag");
				p.sendMessage(ChatColor.YELLOW + "/clan setDesc <text>    //Set the Description of the Clan");
				p.sendMessage(ChatColor.YELLOW + "/clan sethome    //Set clans home");
				p.sendMessage(ChatColor.YELLOW + "/clan mR    //Show all Member requests");
				p.sendMessage(ChatColor.YELLOW + "/clan mR <Member Name> <yes|no>    //Answer Member request");
				p.sendMessage(ChatColor.YELLOW + "/clan mod <Member Name>    //Promote a player to moderator");
				p.sendMessage(ChatColor.YELLOW + "/clan demod <Member Name>    //Demote a player to moderator");
				p.sendMessage(ChatColor.YELLOW + "/clan region/rg create    //Define a region");
				p.sendMessage(ChatColor.YELLOW + "/clan ally <clan name>    //Define a region");
				
				return true;
			}
			if(args[1].equalsIgnoreCase("admin") && p.hasPermission("Clans.*"))
			{
				p.sendMessage(ChatColor.DARK_RED + "Clans Admin help");
				p.sendMessage(ChatColor.DARK_RED + "-----------------------------------------------------");
				p.sendMessage(ChatColor.YELLOW + "/clan create <Clan Name> <Leader Name>    //creates a Clan");
				p.sendMessage(ChatColor.YELLOW + "/clan del, delete <Clan Name>    //Delete a Clan");
				p.sendMessage(ChatColor.YELLOW + "/clan rename <oldName> <newName>    //rename a Clan");
				p.sendMessage(ChatColor.YELLOW + "/clan tag <name> <tag>    //retag a Clan");
				
				return true;
			}
		}
		
		return false;	
	}
}
