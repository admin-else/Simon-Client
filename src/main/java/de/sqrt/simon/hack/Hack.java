package de.sqrt.simon.hack;

import java.util.LinkedHashMap;

public abstract class Hack {
	private final String name;
	private final String description;

	private boolean enabled;

	private LinkedHashMap<String, Double> settings = new LinkedHashMap<>();

	public Hack(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String interact(String setting, Double value) {
		if (setting == null)
			return getDescription();
		if (value != -1) {
			if (settings.containsKey(setting)) {
				settings.put(setting, value);
				return "The setting " + setting + " is now set to " + value + ".";
			} else
				return "There is no setting called " + setting + ".";
		}
		//Edge Case
		switch (setting) {
		case "enable":{
			return setEnabled(true);
		}
		case "disenable":
			return setEnabled(false);
		case "toggle":
			return setEnabled(!enabled);
		default:
			return "There is no setting called " + setting + ".";
		}
	}

	protected void addSetting(String key,Double defaultsetting) {
		if(settings.containsKey(key))throw new IllegalArgumentException("The setting "+key+" already exists");
		settings.put(key, defaultsetting);
	}

	public final String getName() {
		return name;
	}

	protected String getDescription() {
		return description;
	}

	public final boolean isEnabled() {
		return enabled;
	}

	protected String setEnabled(boolean enable) {
		//Overkill (:
		if(this.enabled==enable) {
			if(enable) {
				return getName()+" is already enabled.";
			}else return getName()+" is already disenabled.";
		}
		this.enabled = enable;
		if(enable) {
			onEnable();
			return getName()+" is now enabled";
		}
		onDisable();
		return getName()+" is now disenabled";
	}

	protected void onEnable() {

	}

	protected void onDisable() {

	}

	protected void onUpdate() {

	}
}