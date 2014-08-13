package com.silvermineproductions.regions;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysqlcmd;

public class region_effects 
{
	public static void region_damage()
	{
		Collection<? extends Player> collonline = Bukkit.getOnlinePlayers();

		for(Player online : collonline)
		{
			if(online.hasPermission("region"))
			{
				Location loc = online.getLocation();
				int clid1 = mysqlcmd.getClid(mysqlcmd.check_region(loc));
				int clid2 = mysqlcmd.getClidofMember(online.getName());
				if(clid1 != clid2 && loc != null && mysqlcmd.check_region(loc) != "" && !mysqlcmd.check_allies(mysqlcmd.getclName(clid1), mysqlcmd.getclName(clid2)))
				{
					if(!online.isOp())
					{
						if(online.getFoodLevel() > 10)
						{
							online.setFoodLevel(10);
						}
						if(online.getHealth() != 0)
						{
							online.setHealth(online.getHealth() - 1);
						}
					}
				}
			}
		}
	}
	
	public static void region_heal()
	{
		Collection<? extends Player> collonline = Bukkit.getOnlinePlayers();
		
		for(Player online : collonline)
		{
			if(online.hasPermission("region"))
			{
				Location loc = online.getLocation();
				int clid1 = mysqlcmd.getClid(mysqlcmd.check_region(loc));
				int clid2 = mysqlcmd.getClidofMember(online.getName());
				if(clid1 == clid2 && loc != null && mysqlcmd.check_region(loc) != "")
				{
					if(!online.isOp())
					{
						if(online.getFoodLevel() != 20 && online.getHealth() != 20)
						{
							online.setHealth(online.getHealth() + 0.5);
							online.setFoodLevel(online.getFoodLevel() + 1);
						}
					}
				}
			}
		}
	}
}
