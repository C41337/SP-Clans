package com.silvermineproductions.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.silvermineproductions.main.main;
import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.mysqlcmd;

public class EventListener implements Listener 
{
	public EventListener(main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		if(mysqlcmd.check_Member_Name(p.getName()) == false)
		{
			mysql.update("INSERT INTO member (memName, clid) VALUES ('" + p.getName() + "', 0)");
		}
		else
		{
			p.sendMessage(ChatColor.BLUE + "[Clans]" +ChatColor.WHITE + " You have " + mysqlcmd.getMessages(p) + " new messages");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamage(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Projectile)
		{
			Projectile projectile = (Projectile) event.getDamager();
			
			if(projectile.getShooter() instanceof Player)
			{
				Player damager = (Player) projectile.getShooter();
				Player hitter = (Player) event.getEntity();
				
					
				if((mysqlcmd.getClidofMember(hitter.getName()) == mysqlcmd.getClidofMember(damager.getName())) || mysqlcmd.check_allies(damager, hitter))
				{
						event.setCancelled(true);
				}
			}
			else
			{
				event.setCancelled(false);
			}
			
		}
		
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player)
		{
			Player hitter = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			
			if((mysqlcmd.getClidofMember(hitter.getName()) == mysqlcmd.getClidofMember(damager.getName())) || mysqlcmd.check_allies(damager, hitter))
			{
				event.setCancelled(true);
			}
			else
			{
				event.setCancelled(false);
			}
		}
	}
}

