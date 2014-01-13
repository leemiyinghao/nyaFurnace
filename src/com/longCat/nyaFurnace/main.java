package com.longCat.nyaFurnace;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin{
	String interduce = ChatColor.GREEN + "nyaFurnace 0.1 Beta\n" + ChatColor.DARK_GRAY + "By Tech. Grp.,LongCat City Gov.";
	public void onEnable(){
		this.saveDefaultConfig();
		Sound sound;
		try{
			Sound.valueOf(this.getConfig().getString("sound"));
			sound = Sound.valueOf(this.getConfig().getString("sound"));
		}catch(Exception e){
			getLogger().log(Level.SEVERE, "The \"sound\" config value is not on the list,set to \"CAT_MEOW\"");
			sound = Sound.CAT_MEOW;
		}
		int repeat = this.getConfig().getInt("repeat");
		int interval = this.getConfig().getInt("interval");
		if(repeat < 1){
			getLogger().log(Level.SEVERE, "The \"repeat\" config value is not positive,set to 1");
			repeat = 1;
		}
		if(interval < 1){
			getLogger().log(Level.SEVERE, "The \"interval\" config value is not positive,set to 1");
			interval = 1;
		}
		getServer().getPluginManager().registerEvents(new furnaceListener(sound, repeat, interval), this);
	}
}
