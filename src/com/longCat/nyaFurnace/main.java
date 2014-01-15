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
		float volume = 3.0F;
		//TODO: 
		//	Adjust the volume,3.0F is too big. 
		float pitch;
		if(
			sound.equals(Sound.NOTE_BASS) ||
			sound.equals(Sound.NOTE_BASS_DRUM) ||
			sound.equals(Sound.NOTE_BASS_GUITAR) ||
			sound.equals(Sound.NOTE_PIANO) ||
			sound.equals(Sound.NOTE_PLING) ||
			sound.equals(Sound.NOTE_SNARE_DRUM) ||
			sound.equals(Sound.NOTE_STICKS)
		){
			pitch = (float)(this.getConfig().getDouble("pitch") * 0.033F) + 0.5F;
		}else{
			pitch = 0.5F;
		}
		if(repeat < 1){
			getLogger().log(Level.SEVERE, "The \"repeat\" config value is not positive,set to 1");
			repeat = 1;
		}
		if(interval < 1){
			getLogger().log(Level.SEVERE, "The \"interval\" config value is not positive,set to 1");
			interval = 1;
		}
		if(pitch < 1){
			getLogger().log(Level.SEVERE, "The \"pitch\" config value is not positive,set to 1");
			pitch = 1;
		}
		getServer().getPluginManager().registerEvents(new furnaceSmeltEmptyListener(sound, repeat, interval,volume,pitch), this);
		getServer().getPluginManager().registerEvents(new furnaceFuelEmptyListener(sound, repeat, interval,volume,pitch), this);
	}
}
