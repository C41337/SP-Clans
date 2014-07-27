package com.silvermineproductions.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class config 
{
	public static String getJobsConfig(String job)
	{
		File myFile= new File("plugins/Jobs/", "jobConfig.yml");
		
		FileConfiguration conf = YamlConfiguration.loadConfiguration(myFile);
		conf.options().pathSeparator('/');
		
		try {
			conf.load(myFile);
		
			ConfigurationSection jobsSection = conf.getConfigurationSection("Jobs");
			ConfigurationSection jobSection = jobsSection.getConfigurationSection(job);
			
			String jobShortName = jobSection.getString("shortname");
			String jobColor = jobSection.getString("ChatColour");
			
			
			return ChatColor.valueOf(jobColor) + jobShortName + " ";
		
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
		
		return "";
	}
}
