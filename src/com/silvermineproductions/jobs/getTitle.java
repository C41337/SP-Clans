package com.silvermineproductions.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class getTitle 
{
	public static String getJobTitle(Player p)
	{
		String title = "";
		
		int lvl = getjoblevel.getLevel(p);
		
		title = Level(lvl);
		
		
		return title;
	}
	
	private static String Level(int lvl)
	{
		
		String title = "";
		
		
		File titleFile= new File("plugins/Jobs/", "titleConfig.yml");
		
		FileConfiguration conf = YamlConfiguration.loadConfiguration(titleFile);
		conf.options().pathSeparator('/');
		
		try {
			conf.load(titleFile);
			
			ConfigurationSection titleSection = conf.getConfigurationSection("Titles");
			
			List<String> ArrayTitleList = new ArrayList<String>();
			
		    for (String titleKey : titleSection.getKeys(false)) {
		    	
		    	ArrayTitleList.add(titleKey);
		    }
		    
		    String[] allTitle = new String[ArrayTitleList.size()];
		    allTitle = ArrayTitleList.toArray(allTitle);
		    
		    if(ArrayTitleList.size() == 0 || allTitle.length == 0 || lvl == 0)
		    {
		    	return "";
		    }
		    else
		    { 
			    for(int i = 0; i < allTitle.length; i++)
			    {
			    	
			    	ConfigurationSection titlesSection = titleSection.getConfigurationSection(allTitle[i]);
			    	ConfigurationSection titlesSection2 = null;			    	
			    	if(i < (allTitle.length - 1))
			    	{
			    		titlesSection2 = titleSection.getConfigurationSection(allTitle[i+1]);
			    	}
			    	else
			    	{
			    		titlesSection2 = titleSection.getConfigurationSection(allTitle[i]);
			    	}
			    	
			    	int levelReq = titlesSection.getInt("levelReq");

					int levelReq1 = titlesSection2.getInt("levelReq");
			   
			    	if((lvl >= levelReq && lvl < levelReq1) || (levelReq == levelReq1))
			    	{			    	
				        String titleShortName = titlesSection.getString("ShortName");
				        String titleColor = titlesSection.getString("ChatColour");
				        
				        return ChatColor.valueOf(titleColor) + titleShortName;
			    	}		        
			    }
		    }
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return title;
	}
}
