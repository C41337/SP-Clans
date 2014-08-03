package com.silvermineproductions.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class mysqlcmd 
{
	
	public static boolean check_Clan_Name(String name)
	{
        try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT clid FROM clans WHERE clName='" + name + "'");
        	rs.last();
        	int count = rs.getRow();
        	
        	if(count == 1){
        		return true;
        	} else {
        		return false;
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	public static boolean check_Member_Name(String name)
	{
        try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT mid FROM member WHERE memName='" + name + "'");
        	rs.last();
        	int count = rs.getRow();
        	
        	if(count != 0){
        		return true;
        	} else {
        		return false;
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	public static int getMid(String name)
	{
		int n = 0;
        
        try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT mid FROM member WHERE memName='" + name + "'");
        	while (rs.next()) {
            	n = rs.getInt("mid");
            }
               
        }catch(Exception e){
                System.err.println(e);
        }
		
		return n;
	}
	
	
	public static int getClid(String name)
	{
		
		int n = 0;

        try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT clid FROM clans WHERE clName='" + name + "'");
        	while (rs.next()) {
            	n = rs.getInt("clid");
            }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }

        return n;
	}

	
	public static int leader(String p)
	{
		int n = 0;
		
		try{
			if(check_Member_Name(p))
			{
				
	        	Statement stmt = mysql.con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT clid FROM leader WHERE mid='" + getMid(p) + "'");
	        	
	        	while(rs.next()){
	        		n = rs.getInt("clid");
	        	}
			} else
			{
				n = 0;
			}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return n;
	}
	
	public static String getclHome(String name)
	{
		String loc = "";
		
		 try{
			 if(check_Member_Name(name))
			 {
				 if(getClidofMember(name) != 0)
				 {
		        	Statement stmt = mysql.con.createStatement();
		        	ResultSet rs = stmt.executeQuery("SELECT clHome FROM clans WHERE clid='" + getClidofMember(name) + "'");
		        	
		        	while(rs.next()){
		        		loc = rs.getString("clHome");
		        	}
				 }
			 }
	               
	        }catch(Exception e){
	        	System.err.println("Konnte SQL nicht ausführen!");
	            e.printStackTrace();
	        }
			return loc;
	}
	
	public static int getClidofMember(String name)
	{
		int n = 0;
		
		 try{
	        	Statement stmt = mysql.con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT clid FROM member WHERE memName='" + name + "'");
	        	
	        	while(rs.next()){
	        		n = rs.getInt("clid");
	        	}
	               
	        }catch(Exception e){
	        	System.err.println("Konnte SQL nicht ausführen!");
	            e.printStackTrace();
	        }
			return n;
	}
	
	public static boolean check_Clan_Tag(String clTag)
	{
		try{
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT clid FROM clans WHERE clTag='" + clTag + "' LIMIT 1");
        	rs.last();
        	int count = rs.getRow();
        	
        	if(count != 0){
        		return true;
        	} else {
        		return false;
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	public static String serial(Location l) 
	{
		return new String(l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ() + "," + l.getWorld().getName());
	}
	
	public static Location deserial(String s) {
		
		if(s == null || s == "")
		{
			return null;
		}
		String[] a = s.split(",");
		World w = Bukkit.getWorld(a[3]);
		if(w == null) {
		w = Bukkit.getWorlds().get(0);
		}
		int x = Integer.parseInt(a[0]);
		int y = Integer.parseInt(a[1]);
		int z = Integer.parseInt(a[2]);
		Location l = new Location(w,x,y,z);
		return l;
	}
	
	public static int getApplications(Player p)
	{
		int n = 0;
		
		try{
			if(leader(p.getName()) != 0)
			{
				
	        	Statement stmt = mysql.con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT messages FROM leader WHERE mid='" + getMid(p.getName()) + "'");
	        	
	        	while(rs.next())
	        	{
	        		n = rs.getInt("messages");
	        	}
	        	
	        	
			} else
			{
				n = 0;
			}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return n;
	}
	
	public static String clName(Player p)
    {
    	String name = "";
    	
    	try{
    		if(check_Member_Name(p.getName()))
    		{
	    		if(getClidofMember(p.getName()) != 0)
	    		{
		        	Statement stmt = mysql.con.createStatement();
		        	ResultSet rs = stmt.executeQuery("SELECT clName FROM clans WHERE clid='" + getClidofMember(p.getName()) + "'");
		        	
		        	while(rs.next()){
		        		name = rs.getString("clName");
		        	}
	    		}
    		}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
    	
    	
    }
	public static String getclName(int clid)
    {
    	String name = "";
    	
    	try{
    		Statement stmt = mysql.con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT clName FROM clans WHERE clid='" + clid + "'");
		        	
		    while(rs.next()){
		    	name = rs.getString("clName");
		    }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
    	
    	
    }
	
	public static String clTag(Player p)
    {
    	String name = "";
    	
    	try{
    		if(check_Member_Name(p.getName()))
    		{
	    		if(getClidofMember(p.getName()) != 0)
	    		{
		        	Statement stmt = mysql.con.createStatement();
		        	ResultSet rs = stmt.executeQuery("SELECT clTag FROM clans WHERE clid='" + getClidofMember(p.getName()) + "'");
		        	
		        	while(rs.next()){
		        		name = rs.getString("clTag");
		        	}
	    		}
    		}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
    	
    	
    }
	public static String clTag(String clName)
    {
    	String name = "";
    	
    	try{
    		Statement stmt = mysql.con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT clTag FROM clans WHERE clName='" + clName + "'");
		        	
		    while(rs.next()){
		    name = rs.getString("clTag");

		}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
    	
    	
    }
	public static String clDesc(String clName)
    {
    	String name = "";
    	
    	try{
    		Statement stmt = mysql.con.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT clDescription FROM clans WHERE clName='" + clName + "'");
		        	
		    while(rs.next()){
		    name = rs.getString("clDescription");

		}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
    	
    	
    }
	
	public static int all_Member(int clid)
	{
		int n = 0;
		try{
			
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM member WHERE clid='" + clid + "'");
        	rs.last();
        	n = rs.getRow();
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		
		return n;
	}
	
	public static String getMemberName(int mid)
	{
		String name = "";
		try{
			
        	Statement stmt = mysql.con.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT memName FROM member WHERE mid='" + mid + "'");
        	while(rs.next())
        	{
        		name = rs.getString("memName");
        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return name;
		
	}
	public static int getleader(String clName)
	{
		int n = 0;
		
		try{
	
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT mid FROM leader WHERE clid='" + getClid(clName) + "'");
	        	
	        while(rs.next()){
	        	n = rs.getInt("mid");
	        }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return n;
	}
	
	public static int getModerator(String clName)
	{
		int n = 0;
		
		try{
				
	        	Statement stmt = mysql.con.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT mid FROM moderator WHERE clid='" + getClid(clName) + "'");
	        	
	        	while(rs.next()){
	        		n = rs.getInt("mid");
	        	}
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return n;
	}
	
	public static boolean check_Moderator(Player p)
	{
		
		try{
	
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT clid FROM moderator WHERE mid='" + getMid(p.getName()) + "' LIMIT 1");
	        
	        rs.last();
	        if(rs.getRow() == 1)
	        {
	        	return true;
	        }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean check_Leader(Player p)
	{
		
		try{
	
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT clid FROM leader WHERE mid='" + getMid(p.getName()) + "' LIMIT 1");
	        
	        rs.last();
	        if(rs.getRow() == 1)
	        {
	        	return true;
	        }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
	public static String get_all_member(String clName)
	{
		String member = "";
		
		try{
	
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT memName FROM member WHERE clid='" + getClid(clName) + "'");
	        
	        rs.last();
	        if(rs.getRow() == 1)
	        {
	        	member = " -";
	        }
	        else
	        {
	        	rs.beforeFirst();
		        while(rs.next()){
		        	if(rs.getString("memName") != getMemberName(getleader(clName)))
		        	{
		        		member = rs.getString("memName") + " ," + member;
		        	}
		        }
	        }
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return member;
	}
	
	public static String[] getClanMember(Player p)
	{
				
		try{
	
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT memName FROM member WHERE clid='" + getClid(p.getName()) + "'");
	        
	        rs.last();
	        int count = rs.getRow();
	        String[] member = new String[count];
	        
	        rs.beforeFirst();
	        int i = 0;
	        while(rs.next())
	        {
	        	member[i] = rs.getString("memName");
	        }
			return member;
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return null;
	}
	
	public static boolean check_allies(Player p1, Player p2)
	{
		try{
			
			int clid1 = getClidofMember(p1.getName());
			int clid2 = getClidofMember(p2.getName());
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM ally WHERE (clid1='" + clid1 + "' AND clid2='" + clid2 + "' AND assume='1') "
	        		+ "OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "' AND assume='1')");
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count == 1)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	public static boolean check_allies(String cl1, String cl2)
	{
		try{
			
			int clid1 = getClid(cl1);
			int clid2 = getClid(cl2);
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM ally WHERE (clid1='" + clid1 + "' AND clid2='" + clid2 + "' AND assume='1') "
	        		+ "OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "' AND assume='1')");
	        
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count != 0)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean check_ally_list(String cl1, String cl2)
	{
		try{
			
			int clid1 = getClid(cl1);
			int clid2 = getClid(cl2);
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM ally WHERE (clid1='" + clid1 + "' AND clid2='" + clid2 + "') "
	        		+ "OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
	        
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count != 0)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
	public static boolean check_war(Player p1, Player p2)
	{
		try{
			
			int clid1 = getClidofMember(p1.getName());
			int clid2 = getClidofMember(p2.getName());
			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM war WHERE (clid1='" + clid1 + "' AND clid2='" + clid2 + "') "
	        		+ "OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count == 1)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	public static boolean check_war(int clid1, int clid2)
	{
		try{			
	        Statement stmt = mysql.con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM war WHERE (clid1='" + clid1 + "' AND clid2='" + clid2 + "') "
	        		+ "OR (clid1='" + clid2 + "' AND clid2='" + clid1 + "')");
	        
	        rs.last();
	        int count = rs.getRow();
	        if(count == 1)
	        {
	        	return true;
	        }
	        else
	        {
	        	return false;
	        }
	        
               
        }catch(Exception e){
        	System.err.println("Konnte SQL nicht ausführen!");
            e.printStackTrace();
        }
		return false;
	}
	
	public static String check_region(Location loc)
	{
		int x = loc.getBlockX(), y = loc.getBlockY(), z = loc.getBlockZ();
		World world = loc.getWorld();
		
		String name = "";
		
		try {
			Statement stmt = mysql.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM region");
			
			while(rs.next())
			{
				name = rs.getString("name");
				int maxX = rs.getInt("maxX");
				int maxY = rs.getInt("maxY");
				int maxZ = rs.getInt("maxZ");
				
				int minX = rs.getInt("minX");
				int minY = rs.getInt("minY");
				int minZ = rs.getInt("minZ");
				
				World sqlWorld = Bukkit.getWorld(rs.getString("world"));
				
				if(sqlWorld == world && x <= maxX && x >= minX && y <= maxY && y >= minY && z <= maxZ && z >= minZ)
				{
					return name;
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static int getMessages(Player p)
	{
		String name = p.getName();
		int mid = getMid(name);
		
		try{
		
		 if(check_Leader(p) || check_Leader(p))
		 {
			 int clid = getClidofMember(name);
			 
			 Statement stmt = mysql.con.createStatement();
			 ResultSet rs = stmt.executeQuery(""
			 		+ "SELECT * FROM applications, ally WHERE clid2='" + clid + "' AND ally.assume='0'");
			 rs.last();
			 return rs.getRow();
		 }
		 else
		 {
			 Statement stmt = mysql.con.createStatement();
			 ResultSet rs = stmt.executeQuery(
					 "SELECT * FROM invite, applications WHERE mid='" + mid + "' OR midApplicant='" + mid +"'");
			 rs.last();
			 return rs.getRow();
		 }
		}catch(Exception e)
		{
			System.out.println(e);
			return 0;
		}
		
	}
	
}
