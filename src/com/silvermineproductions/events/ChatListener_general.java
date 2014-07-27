package com.silvermineproductions.events;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.silvermineproductions.jobs.getTitle;
import com.silvermineproductions.jobs.getjob;
import com.silvermineproductions.main.main;
import com.silvermineproductions.mysql.mysqlcmd;

public class ChatListener_general implements Listener 
{
	public ChatListener_general(main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
    @EventHandler(priority = EventPriority.HIGHEST)    
    public void chatFormat(AsyncPlayerChatEvent event){
    	event.getRecipients().clear();
    	
    	Player p = event.getPlayer();
    	
    	String format = getTitle.getJobTitle(p) + getjob.getJob(p) + "§f" + p.getDisplayName() + "> " + event.getMessage();
    	
    	Collection<? extends Player> collonline = Bukkit.getOnlinePlayers();
    	
    	for(Player online : collonline)
    	{
    		if(mysqlcmd.getClidofMember(online.getName()) == mysqlcmd.getClidofMember(p.getName()))
    		{
    			clanChatFormat(p, online, format);
    		}
    		else if(mysqlcmd.check_allies(p, online))
    		{
    			allyChatFormat(p, online, format);
    		}
    		else if(mysqlcmd.check_war(p, online))
    		{
    			warChatFormat(p, online, format);
    		}
    		else
    		{
    			normalChatFormat(p, online, format);
    		}	
    	}
    }
    
    private static void normalChatFormat(Player sender, Player reciever, String format)
    {
    	
    	if(mysqlcmd.getClidofMember(sender.getName()) != 0)
    	{
    		if(mysqlcmd.clTag(sender) != null)
    		{
		    	if(mysqlcmd.check_Leader(sender))
		    	{
		    		reciever.sendMessage("<**" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Moderator(sender))
		    	{
		    		reciever.sendMessage("<*" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
		    		reciever.sendMessage("<" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
    		}
    		else
    		{
    			if(mysqlcmd.check_Leader(sender))
		    	{
    				reciever.sendMessage("<**" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Moderator(sender))
		    	{
    				reciever.sendMessage("<*" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
    				reciever.sendMessage("<" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    		}
    	}
   		else
   		{
   			reciever.sendMessage("<" + format);
    	}
    	
    }
    
    private static void clanChatFormat(Player sender, Player reciever, String format)
    {
    	
    	if(mysqlcmd.getClidofMember(sender.getName()) != 0)
    	{
    		if(mysqlcmd.clTag(sender) != null)
    		{
		    	if(mysqlcmd.check_Leader(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.GREEN + "**" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Moderator(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.GREEN + "*" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
		    		reciever.sendMessage("<" + ChatColor.GREEN + "" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
    		}
    		else
    		{
    			if(mysqlcmd.check_Leader(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.GREEN + "**" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Moderator(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.GREEN + "*" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
    				reciever.sendMessage("<" + ChatColor.GREEN + "" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    		}    			
    	}
    	else
   		{
   			reciever.sendMessage("<" + format);
    	}
    }
    
    private static void allyChatFormat(Player sender, Player reciever, String format)
    {
    	
    	if(mysqlcmd.getClidofMember(sender.getName()) != 0)
    	{
    		if(mysqlcmd.clTag(sender) != null)
    		{
		    	if(mysqlcmd.check_Leader(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.GOLD + "**" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Moderator(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.GOLD + "*" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
		    		reciever.sendMessage("<" + ChatColor.GOLD + "" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
    		}
    		else
    		{
    			if(mysqlcmd.check_Leader(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.GOLD + "**" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Moderator(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.GOLD + "*" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
    				reciever.sendMessage("<" + ChatColor.GOLD + "" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    		}
    	}
   		else
   		{
   			reciever.sendMessage("<" + format);
    	}
    	
    }
    
    private static void warChatFormat(Player sender, Player reciever, String format)
    {
    	
    	if(mysqlcmd.getClidofMember(sender.getName()) != 0)
    	{
    		if(mysqlcmd.clTag(sender) != null)
    		{
		    	if(mysqlcmd.check_Leader(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.DARK_RED + "**" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Moderator(sender))
		    	{
		    		reciever.sendMessage("<" + ChatColor.DARK_RED + "*" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
		    	if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
		    		reciever.sendMessage("<" + ChatColor.DARK_RED + "" + mysqlcmd.clTag(sender) + " §f" + format);
		    	}
    		}
    		else
    		{
    			if(mysqlcmd.check_Leader(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.DARK_RED + "**" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Moderator(sender))
		    	{
    				reciever.sendMessage("<" + ChatColor.DARK_RED + "*" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    			if(mysqlcmd.check_Leader(sender) == false && mysqlcmd.check_Moderator(sender) == false)
		    	{
    				reciever.sendMessage("<" + ChatColor.DARK_RED + "" + mysqlcmd.clName(sender) + " §f" + format);
		    	}
    		}
    	}
   		else
   		{
   			reciever.sendMessage("<" + format);
    	}
    	
    }

}
