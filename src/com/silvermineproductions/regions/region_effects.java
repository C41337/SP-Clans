package com.silvermineproductions.regions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.silvermineproductions.mysql.mysqlcmd;

public class region_effects 
{
	public static void region_damage()
	{
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		int count = online.length;
		for(int i = 0; i < count; i++)
		{
			Location loc = online[i].getLocation();
			int clid1 = mysqlcmd.getClid(mysqlcmd.check_region(loc));
			int clid2 = mysqlcmd.getClidofMember(online[i].getName());
			if(clid1 != clid2 && loc != null && mysqlcmd.check_region(loc) != "")
			{
				if(!online[i].isOp())
				{
					if(online[i].getFoodLevel() > 10)
					{
						online[i].setFoodLevel(10);
					}
						online[i].setHealth(online[i].getHealth() - 1);
				}
			}
		}
	}
	
	public static void region_heal()
	{
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		int count = online.length;
		for(int i = 0; i < count; i++)
		{
			Location loc = online[i].getLocation();
			int clid1 = mysqlcmd.getClid(mysqlcmd.check_region(loc));
			int clid2 = mysqlcmd.getClidofMember(online[i].getName());
			if(clid1 == clid2 && loc != null && mysqlcmd.check_region(loc) != "")
			{
				if(!online[i].isOp())
				{
					if(online[i].getFoodLevel() != 20 && online[i].getHealth() != 20)
					{
						online[i].setHealth(online[i].getHealth() + 0.5);
						online[i].setFoodLevel(online[i].getFoodLevel() + 1);
					}
				}
			}
		}
	}
}
