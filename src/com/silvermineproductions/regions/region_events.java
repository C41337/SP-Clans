package com.silvermineproductions.regions;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.silvermineproductions.main.main;
import com.silvermineproductions.mysql.mysqlcmd;

public class region_events implements Listener 
{
	public region_events(main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player p = event.getPlayer();
		String clName = mysqlcmd.clName(p);
		int clid = mysqlcmd.getClid(clName);
		
		Location loc1 = event.getFrom();
		Location loc2 = event.getTo();
		
		String name1 = mysqlcmd.check_region(loc1);
		String name2 = mysqlcmd.check_region(loc2);
		if(name2 != name1 && name1 == "")
		{
			int clid2 = mysqlcmd.getClid(name2);
			if(mysqlcmd.getClidofMember(p.getName()) == clid2)
			{
				p.sendMessage(ChatColor.GREEN + "You enter your clans home");
			}
			if(mysqlcmd.check_war(clid, clid2))
			{
				p.sendMessage(ChatColor.DARK_RED + "You enter your enemies home");
				if(!p.isOp())
				{
					p.sendMessage(ChatColor.DARK_RED + "You will loose 0.5 hearts every sec");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPlace(BlockPlaceEvent event)
	{
		Player p = event.getPlayer();
		Location loc = event.getBlockAgainst().getLocation();
		
		String rgClan = mysqlcmd.check_region(loc);
		int clid = mysqlcmd.getClidofMember(p.getName());		
		int rgClid = mysqlcmd.getClid(mysqlcmd.check_region(loc));
		
		if(rgClan != "")
		{
			if(clid != rgClid)
			{
				if(!p.isOp())
				{
					p.sendMessage(ChatColor.RED + "You can't place anything in another clans home");
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerPlace(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		
		String rgClan = mysqlcmd.check_region(loc);
		int clid = mysqlcmd.getClidofMember(p.getName());		
		int rgClid = mysqlcmd.getClid(mysqlcmd.check_region(loc));
		
		if(rgClan != "")
		{
			if(clid != rgClid)
			{
				if(!p.isOp())
				{
					p.sendMessage(ChatColor.RED + "You can't break anything in another clans home");
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		
		if(mysqlcmd.check_Leader(p))
		{
			if(p.getItemInHand().getType() == Material.FEATHER)
			{
				Location loc = event.getClickedBlock().getLocation();
				Material cliBlock = event.getClickedBlock().getType();
				
				if(cliBlock.isSolid() && loc != null && cliBlock != null)
				{
					if(event.getAction() == Action.LEFT_CLICK_BLOCK)
					{
						if(mysqlcmd.check_region(loc) == "" 
								|| mysqlcmd.getClid(mysqlcmd.check_region(loc)) == mysqlcmd.getClidofMember(p.getName()))
						{
							region.hashloc1.put(p.getName(), loc);
							p.sendMessage(ChatColor.GREEN + "pos1 set");
							event.setCancelled(true);
						}
						else
						{
							p.sendMessage(ChatColor.RED + "The Block is part of another clans region");
							event.setCancelled(true);
						}
					}
					else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						if(mysqlcmd.check_region(loc) == "" 
								|| mysqlcmd.getClid(mysqlcmd.check_region(loc)) == mysqlcmd.getClidofMember(p.getName()))
						{
							region.hashloc2.put(p.getName(), loc);
							p.sendMessage(ChatColor.GREEN + "pos2 set");
						}
						else
						{
							p.sendMessage(ChatColor.RED + "The Block is part of another clans region");
						}
					}
				}
			}
			
			else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
			{			
				int clid1 = mysqlcmd.getClid(mysqlcmd.check_region(event.getClickedBlock().getLocation()));
				int clid2 = mysqlcmd.getClidofMember(p.getName());
				if(mysqlcmd.check_region(event.getClickedBlock().getLocation()) != "")
				{
					if(clid1 != clid2)
					{
						if(!mysqlcmd.check_war(clid1, clid2))
						{
							p.sendMessage(ChatColor.RED + "You aren't allowed to do this in a another clans area");
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
}
