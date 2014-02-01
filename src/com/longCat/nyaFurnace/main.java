package com.longCat.nyaFurnace;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin{
	String interduce = ChatColor.GREEN + "nyaFurnace 0.12 'ISUZU'\n" + ChatColor.DARK_GRAY + "By Tech. Grp.,LongCat City Gov.";
	Plugin plugin = this;
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
		boolean defMute = this.getConfig().getBoolean("defMute");
		float volume = 1.0F;
		//TODO: 
		//	Adjust the volume,3.0F is too big.
		//		I think 1.0F is enough,that is the biggest default value.
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
			pitch = 1F;
		}
		if(repeat < 1){
			getLogger().log(Level.SEVERE, "The \"repeat\" config value is not positive,set to 1");
			repeat = 1;
		}
		if(interval < 1){
			getLogger().log(Level.SEVERE, "The \"interval\" config value is not positive,set to 1");
			interval = 1;
		}
		if(pitch < 0.5F){
			getLogger().log(Level.SEVERE, "The \"pitch\" config value is not positive,set to 6");
			pitch = 0.5198F;
		}
		getServer().getPluginManager().registerEvents(new furnaceSmeltEmptyListener(sound, repeat, interval,volume,pitch,defMute), this);
		getServer().getPluginManager().registerEvents(new furnaceFuelEmptyListener(sound, repeat, interval,volume,pitch,defMute), this);
	}
	final public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("nyafurnace")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("reload")){
					if(sender.hasPermission("nyaFurnace.reload")){
						Timer timer = new Timer();
						timer.schedule(new TimerTask(){
							public void run(){
								HandlerList.unregisterAll(plugin);
								org.bukkit.event.inventory.FurnaceSmeltEvent.getHandlerList().unregister(plugin);
								org.bukkit.event.inventory.FurnaceBurnEvent.getHandlerList().unregister(plugin);
								Bukkit.getPluginManager().disablePlugin(plugin);
								sender.sendMessage(ChatColor.YELLOW + "nyaFurnace disable");
								Bukkit.getPluginManager().enablePlugin(plugin);
								sender.sendMessage(ChatColor.YELLOW + "nyaFurnace enable");
							}
						}, 200);
					}
					return true;
				}
			}
			sender.sendMessage(interduce);
			sender.sendMessage(ChatColor.YELLOW + "Usage: /nyaFurnace reload");
			return true;
		}
		return false;
	}
}
