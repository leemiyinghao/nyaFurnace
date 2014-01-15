package com.longCat.nyaFurnace;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.Sound;

public final class furnaceFuelEmptyListener implements Listener {
	private Sound sound;
	private int repate,interval;
	private float volume,pitch;
	public furnaceFuelEmptyListener(Sound _sound,int _repate,int _interval,float _volume,float _pitch){
		sound = _sound;
		repate = _repate;
		interval = _interval;
		volume = _volume;
		pitch = _pitch;
	}
	@EventHandler
	public void smelt(FurnaceBurnEvent event) throws InterruptedException {
		if(event.getFuel().getAmount() <= 1){
			Location loc = event.getBlock().getLocation();
			for(int i = 0;i<repate;i++){
				event.getBlock().getWorld().playSound(loc, sound,volume,pitch);
				Thread.sleep(interval);
			}
		}
	}
}