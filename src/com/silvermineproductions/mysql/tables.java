package com.silvermineproductions.mysql;

import com.silvermineproductions.mysql_functions.create_table;
import com.silvermineproductions.mysql_functions.testtables;

public class tables 
{
	public static void checktables()
	{
		if(testtables.test_table("ally") == false)
		{
			create_table.createtable("ally");
		}
		if(testtables.test_table("applications") == false)
		{
			create_table.createtable("applications");
		}
		if(testtables.test_table("clans") == false)
		{
			create_table.createtable("clans");
		}
		if(testtables.test_table("invite") == false)
		{
			create_table.createtable("invite");
		}
		if(testtables.test_table("leader") == false)
		{
			create_table.createtable("leader");
		}
		if(testtables.test_table("member") == false)
		{
			create_table.createtable("member");
		}
		if(testtables.test_table("moderator") == false)
		{
			create_table.createtable("moderator");
		}
		if(testtables.test_table("war") == false)
		{
			create_table.createtable("war");
		}
		if(testtables.test_table("region") == false)
		{
			create_table.createtable("region");
		}
	}
}
