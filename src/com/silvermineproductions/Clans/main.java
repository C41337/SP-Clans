package com.silvermineproductions.Clans;


import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.RegisteredServiceProvider;
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
		this.setupEconomy();
		this.setupChat();
		
		loadConfiguration();
		System.out.print("[Clans] Enabled");
		
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
	
    public void loadConfiguration(){
    	this.getConfig().addDefault("Clans.mysql.IP", "localhost");
		this.getConfig().addDefault("Clans.mysql.port", 3306);
		this.getConfig().addDefault("Clans.mysql.user", "user");
		this.getConfig().addDefault("Clans.mysql.password", "");
		this.getConfig().addDefault("Clans.mysql.database", "db");
		this.getConfig().addDefault("Clans.mysql.Jobs_Database", "jobs");
		
		this.getConfig().addDefault("Economy.form_Clan.price", 500000);
		
		this.getConfig().addDefault("CooldownTime", 0);
	    
	    this.getConfig().options().copyDefaults(true); 
	    
	    this.saveConfig();
    }
    
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }

}
