package de.sqrt.simon.hack;

import de.sqrt.simon.hacks.Flying;

public class HackList {
	public final Flying fly = new Flying("fly");
	
	public void tick() {
		fly.tick();
	}
	
}
