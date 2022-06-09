package de.sqrt.simon;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.sqrt.simon.commands.Command;
import de.sqrt.simon.hack.HackList;


public class Simon implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	
	HackList list = new HackList();
	static Simon simon;

	@Override
	public void onInitialize() {
		ClientTickEvents.END_CLIENT_TICK.register(this::tick);
		
		Command.init();
		
		simon = this;
		
		LOGGER.info("Loaded Simon.");
	}
	private void tick(MinecraftClient client) {
		list.tick();
		
	}
	public static Simon getSimon() {
		return simon;
	}
	
	public HackList getList() {
		return list;
	}
	
}
