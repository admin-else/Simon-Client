package de.sqrt.simon.hack;

public abstract class Hack
{
	private final String name;
	
	private boolean enabled;
	
	public Hack(String name) {
		this.name = name;
	}
	
	public final String getName()
	{
		return name;
	}
	
	public final boolean isEnabled()
	{
		return enabled;
	}
	
	public final void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	protected void onEnable()
	{
		
	}
	
	protected void onDisable()
	{
		
	}
}