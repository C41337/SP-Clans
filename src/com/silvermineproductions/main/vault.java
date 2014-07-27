package com.silvermineproductions.main;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class vault 
{
	 public static boolean setupEconomy()
	    {
	        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            main.economy = economyProvider.getProvider();
	        }

	        return (main.economy != null);
	    }
	    public static boolean setupChat()
	    {
	        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
	        if (chatProvider != null) {
	            main.chat = chatProvider.getProvider();
	        }

	        return (main.chat != null);
	    }

}
