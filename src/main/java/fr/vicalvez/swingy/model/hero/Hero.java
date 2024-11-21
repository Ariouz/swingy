package fr.vicalvez.swingy.model.hero;

import java.io.File;

public class Hero {

	private HeroType type;
	private final String name;
	private final HeroLevel level;
	private final HeroStats stats;

	public Hero(HeroType type, String name)
	{
		this.name = name;
		this.type = type;
		this.level = new HeroLevel();
		this.stats = new HeroStats(type);
	}

	public Hero(String name, File saveFile)
	{
		this.name = name;
		this.level = new HeroLevel(name); // todo load
		this.stats = new HeroStats(name); // todo load
		// todo load type
	}

	public HeroType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public HeroLevel getLevel() {
		return level;
	}

	public HeroStats getStats() {
		return stats;
	}
}
