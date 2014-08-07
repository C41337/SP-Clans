package com.silvermineproductions.member_functions;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysqlcmd;

public class chat 
{
	public static HashMap<Player, String> chat = new HashMap<Player, String>();
	
	public static boolean activate_chat(Player p, String[] args)
	{
		if(mysqlcmd.getClidofMember(p.getName()) != 0)
		{
			if(args.length == 1)
			{
				if(chat.containsKey(p) && chat.get(p) == null)
				{
					chat.remove(p);
					p.sendMessage(ChatColor.RED + "clan-chat disabled");
					return true;
				}
				else
				{
					if(chat.containsKey(p) && chat.get(p) == "ally")
					{
						chat.remove(p);
						p.sendMessage(ChatColor.RED + "ally-chat disabled");
					}
					chat.put(p, null);
					p.sendMessage(ChatColor.GREEN + "clan-chat enabled");
					return true;
				}
			}
			if(args.length == 2 && args[1].equalsIgnoreCase("ally"))
			{
				if(chat.containsKey(p) && chat.get(p) == "ally")
				{
					chat.remove(p);
					p.sendMessage(ChatColor.RED + "ally-chat disabled");
					return true;
				}
				else
				{
					if(chat.containsKey(p) && chat.get(p) == null)
					{
						chat.remove(p);
						p.sendMessage(ChatColor.RED + "clan-chat disabled");
					}
					chat.put(p, "ally");
					p.sendMessage(ChatColor.GREEN + "ally-chat enabled");
					return true;
				}
			}
			else
			{
				p.sendMessage("/clan chat [ally]");
				return true;
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED + "You aren't part of a clan");
			return true;
		}
	}
}
