package com.silvermineproductions.mysql_functions;

import com.silvermineproductions.mysql.mysql;

public class create_table 
{
	public static void createtable(String tbl_name)
	{
		if(tbl_name == "ally")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS ally (" +
					"aid bigint(20) NOT NULL AUTO_INCREMENT, " +
					"clid1 tinyint(4) NOT NULL, " +
					"clid2 tinyint(4) NOT NULL, " +
					"assume enum('0','1') NOT NULL DEFAULT '0', " +
					"PRIMARY KEY (`aid`) " +
					") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14");
		}
		if(tbl_name == "applications")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `applications` (" +
					"`clid` int(4) NOT NULL, " +
					"`midApplicant` int(11) NOT NULL, " +
					"`date` varchar(255) NOT NULL, " +
					"UNIQUE KEY `midApplicant` (`midApplicant`) " +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		}
		if(tbl_name == "clans")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `clans` (" +
					"`clid` tinyint(4) NOT NULL AUTO_INCREMENT, " +
					"`clName` varchar(255) NOT NULL, " +
					"`clTag` varchar(7) DEFAULT NULL, " +
					"`clDescription` text, " +
					"`clHome` varchar(255) DEFAULT NULL, " +
					"PRIMARY KEY (`clid`) " +
					") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18");
		}
		if(tbl_name == "invite")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `invite` (" +
					"`invID` bigint(20) NOT NULL AUTO_INCREMENT, " +
					"`clid` int(10) NOT NULL, " +
					"`mid` bigint(20) NOT NULL, " +
					"PRIMARY KEY (`invID`) " +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1");
		}
		if(tbl_name == "leader")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `leader` (" +
					"`mid` bigint(15) NOT NULL, " +
					"`clid` int(4) NOT NULL, " +
					"`messages` bigint(20) NOT NULL, " +
					"UNIQUE KEY `mid` (`mid`,`clid`) " +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		}
		if(tbl_name == "member")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `member` (" +
					"`mid` bigint(20) NOT NULL AUTO_INCREMENT, " +
					"`memName` varchar(255) NOT NULL, " +
					"`clid` tinyint(4) NOT NULL, " +
					"`clan_chat` enum('0','1') NOT NULL DEFAULT '0', " +
					"PRIMARY KEY (`mid`) " +
					") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8");
		}
		if(tbl_name == "moderator")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `moderator` (" +
					"`mid` bigint(20) NOT NULL, " +
					"`clid` tinyint(4) NOT NULL, " +
					"UNIQUE KEY `mid` (`mid`), " +
					"UNIQUE KEY `clid` (`clid`) " +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1");
		}
		if(tbl_name == "war")
		{
			mysql.update("CREATE TABLE IF NOT EXISTS `war` (" +
					"`wid` bigint(20) NOT NULL AUTO_INCREMENT, " +
					"`clid1` tinyint(4) NOT NULL, " +
					"`clid2` tinyint(4) NOT NULL, " +
					"PRIMARY KEY (`wid`) " +
					") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1");
		}
	}
}
