package com.silvermineproductions.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.silvermineproductions.admin_leader_functions.Member_Application;
import com.silvermineproductions.admin_leader_functions.ally;
import com.silvermineproductions.admin_leader_functions.changeTag;
import com.silvermineproductions.admin_leader_functions.change_Name;
import com.silvermineproductions.admin_leader_functions.createClan;
import com.silvermineproductions.admin_leader_functions.delete;
import com.silvermineproductions.admin_leader_functions.description;
import com.silvermineproductions.admin_leader_functions.fire;
import com.silvermineproductions.admin_leader_functions.invite;
import com.silvermineproductions.admin_leader_functions.moderator;
import com.silvermineproductions.admin_leader_functions.war;
import com.silvermineproductions.member_functions.chat;
import com.silvermineproductions.member_functions.form;
import com.silvermineproductions.member_functions.help;
import com.silvermineproductions.member_functions.home;
import com.silvermineproductions.member_functions.join;
import com.silvermineproductions.member_functions.leave;
import com.silvermineproductions.member_functions.list;
import com.silvermineproductions.member_functions.request;
import com.silvermineproductions.member_functions.show;
import com.silvermineproductions.regions.region;

public class clanCmd implements CommandExecutor
{
	public static String name = "";
	public static String version = "";
	public static String author = "";
	public static String test = "";
	
	public static int form_price = 0;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{		
		Player p = null;
		if(sender instanceof Player)
		{
			p = (Player)sender;
		}
		
		
		//Kommando clan
		
		if(cmd.getName().equalsIgnoreCase("clan"))
		{
			if(p != null)
			{
				if(args.length == 0)
				{
					p.sendMessage(ChatColor.BLUE + "[Clans]");
					p.sendMessage(ChatColor.GREEN + "Name: " + name);
					p.sendMessage(ChatColor.GREEN + "Version: " + version);
					p.sendMessage(ChatColor.GREEN+ "Author: " + author);
					p.sendMessage("Press /clan help for all commands");
					return true;
				}
				
				// Kommando help
				
				if(args[0].equalsIgnoreCase("help"))
				{
					help.helps(args, p);
					return true;
				}
				
				// Kommando create
				if(args[0].equalsIgnoreCase("create"))
				{
					createClan.create(p, args);
				}
				
				//Kommando list
				if(args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("l"))
				{
					if(args.length != 1)
					{
						p.sendMessage("/clan list/l");
						return true;	
					}
					
					list.listClan(p);
					return true;
				}
								
				//Kommando Change clTag
				
				if(args[0].equalsIgnoreCase("tag"))
				{
					changeTag.change(p, args);
					return true;
				}
				
				//Kommando sethome
				if(args[0].equalsIgnoreCase("sethome"))
				{
					home.sethome(p, args);
					return true;
				}
				
				//Kommando home
				if(args[0].equalsIgnoreCase("home"))
				{
					home.tphome(p);
					return true;
				}
				//Kommando set Description
				if(args[0].equalsIgnoreCase("setDesc"))
				{
					description.setdescription(p, args);
					return true;
				}
				//Kommando set Description
				if(args[0].equalsIgnoreCase("mR"))
				{
					Member_Application.memAppl(p, args);
					return true;
				}
				//Kommando join
				if(args[0].equalsIgnoreCase("join"))
				{
					join.joinMem(p, args);
					return true;
				}
				//Kommando fire
				if(args[0].equalsIgnoreCase("fire"))
				{
					fire.clFire(p, args);
					return true;
				}
				//Kommando leave
				if(args[0].equalsIgnoreCase("leave"))
				{
					leave.leaveMem(p);
					return true;
				}
				//Kommando Moderator
				if(args[0].equalsIgnoreCase("mod"))
				{
					moderator.setMod(p, args);
					return true;
				}
				//Kommando deModerator
				if(args[0].equalsIgnoreCase("demod"))
				{
					moderator.deMod(p, args);
					return true;
				}
				//Kommando form
				if(args[0].equalsIgnoreCase("form"))
				{
					form.form_clan(p, args);
					return true;
				}
				//Kommando delete
				if(args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del"))
				{
					delete.delete_Clan(p, args[1]);
					return true;
				}
				//Kommando name
				if(args[0].equalsIgnoreCase("rename"))
				{
					change_Name.changeClName(p, args);
					return true;
				}
				//Kommando invite
				if(args[0].equalsIgnoreCase("inv"))
				{
					invite.invitePlayer(p, args);
					return true;
				}
				//Kommando request
				if(args[0].equalsIgnoreCase("req"))
				{
					request.invReq(p, args);
					return true;
				}
				//Kommando ally
				if(args[0].equalsIgnoreCase("ally"))
				{
					ally.addally(p, args);
					return true;
				}
				//Kommando war
				if(args[0].equalsIgnoreCase("war"))
				{
					war.declwar(p, args);
					return true;
				}
				//Kommando chat
				if(args[0].equalsIgnoreCase("chat"))
				{
					chat.activate_chat(p, args);
					return true;
				}
				
				
				
				
				
				if(args[0].equalsIgnoreCase("region") || args[0].equalsIgnoreCase("rg"))
				{
					region.exec_region(p, args);
					return true;
				}
				
				if(args[0].equalsIgnoreCase("test"))
				{
					p.sendMessage(chat.chat.get(p));
					return true;
				}
				if(args[0].equalsIgnoreCase("loc"))
				{
					p.sendMessage(p.getLocation().toString());
					return true;
				}
				
				if(args.length == 1 || args.length == 2)
				{
					show.showInf(args, p);
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.RED + "This command doesn't exist");
					return true;
				}
				
				
				
			} else
			{
				sender.sendMessage(ChatColor.RED + "Dieser Befehl ist nicht fuer die Konsole bestimmt");
				return true;
			}
		}
		
		return false;
	}
	
	
}
