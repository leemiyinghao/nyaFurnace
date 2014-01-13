package com.longCat.nyaFurnace;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.Sound;

public final class furnaceListener implements Listener {
	private Sound sound;
	private int repate,interval;
	public furnaceListener(Sound _sound,int _repate,int _interval){
		sound = _sound;
		repate = _repate;
		interval = _interval;
	}
	@EventHandler
	public void smelt(FurnaceSmeltEvent event) throws InterruptedException {
		if(event.getSource().getAmount() <= 1){
			Location loc = event.getBlock().getLocation();
			for(int i = 0;i<repate;i++){
				event.getBlock().getWorld().playSound(loc, sound,1,1);
				Thread.sleep(interval);
			}
		}
	}
}