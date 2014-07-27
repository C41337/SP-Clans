package com.silvermineproductions.main;

import org.bukkit.plugin.Plugin;

public class config 
{
	public static void loadConfiguration(Plugin plugin){
    	plugin.getConfig().addDefault("Clans.mysql.IP", "localhost");
		plugin.getConfig().addDefault("Clans.mysql.port", 3306);
		plugin.getConfig().addDefault("Clans.mysql.user", "user");
		plugin.getConfig().addDefault("Clans.mysql.password", "");
		plugin.getConfig().addDefault("Clans.mysql.database", "db");
		plugin.getConfig().addDefault("Clans.mysql.Jobs_Database", "jobs");
		
		plugin.getConfig().addDefault("Economy.form_Clan.price", 500000);
		
		plugin.getConfig().addDefault("CooldownTime", 0);
	    
		plugin.getConfig().options().copyDefaults(true); 
	    
		plugin.saveConfig();
    }
}
