package com.longCat.nyaFurnace;
//import java.util.Timer;
//import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Location;
import org.bukkit.Material;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.block.Furnace;
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
	public void smelt(final FurnaceBurnEvent event) throws InterruptedException {
		if(event.getFuel().getAmount() <= 1){
			Timer timer = new Timer();
			timer.schedule(new TimerTask(){
				public void run(){
					if(event.getBlock().getType().equals(Material.FURNACE)){//If furnace cool down
						Location loc = event.getBlock().getLocation();
						try {
							for(int i = 0;i<repate;i++){
							event.getBlock().getWorld().playSound(loc, sound,volume,pitch);
							//event.getBlock().getWorld().playEffect(loc, type, data);
							//TODO:
							//	Find out what the type and data should be if I want to call a note partical effect.
							//	I don't understand why they don't put this in Javadoc.
							Thread.sleep(interval);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}, (event.getBurnTime()+1) * 50);
		}
	}
}