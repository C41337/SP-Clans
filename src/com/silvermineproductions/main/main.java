package com.silvermineproductions.main;


import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.java.JavaPlugin;

import com.silvermineproductions.cmd.clanCmd;
import com.silvermineproductions.events.ChatListener_general;
import com.silvermineproductions.events.EventListener;
import com.silvermineproductions.member_functions.home;
import com.silvermineproductions.mysql.mysql;
import com.silvermineproductions.mysql.tables;
import com.silvermineproductions.regions.region_effects;
import com.silvermineproductions.regions.region_events;

public class main extends JavaPlugin 
{
	public static Economy economy = null;
	public static Chat chat = null;
	
	@Override
	public void onEnable() 
	{		
		vault.setupEconomy();
		vault.setupChat();
		
		config.loadConfiguration(this);
		System.out.print("[SP-Clans] Enabled");
		
		clanCmd.name = this.getDescription().getName();
		clanCmd.version = this.getDescription().getVersion();
		clanCmd.author = this.getDescription().getAuthors().get(0);
		
		mysql.host = this.getConfig().getString("Clans.mysql.IP");
		mysql.port = this.getConfig().getInt("Clans.mysql.port");
		mysql.user = this.getConfig().getString("Clans.mysql.user");
		mysql.pw = this.getConfig().getString("Clans.mysql.password");
		mysql.db = this.getConfig().getString("Clans.mysql.database");
		mysql.Jdb = this.getConfig().getString("Clans.mysql.Jobs_Database");
		mysql.connect();
		
		tables.checktables();
		
		clanCmd.form_price = this.getConfig().getInt("Economy.form_Clan.price");
		home.cooldown = this.getConfig().getInt("CooldownTime");
		
		getCommands();
		
		new EventListener(this);
		new ChatListener_general(this);	
		new region_events(this);
		
		startScheduleTasks();
	}
	
	@Override
	public void onDisable()
	{
		mysql.close();
	}
			
	private void startScheduleTasks() {
		
		getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable(){

			@Override
			public void run() {
				mysql.reconnect();
			}
			
		}, 300*20, 300*20);
		
		
		getServer().getScheduler().runTaskTimerAsynchronously(this, new Runnable(){
		@Override
		public void run() {			
			region_effects.region_damage();
			region_effects.region_heal();
		}
		
	}, 1*20, 1*20);
		
	}

	private void getCommands() {
		getCommand("clan").setExecutor(new clanCmd());		
	}

}
