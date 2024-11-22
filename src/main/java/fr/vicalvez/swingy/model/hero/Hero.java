package fr.vicalvez.swingy.model.hero;

import fr.vicalvez.swingy.validators.EnumConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.File;

public class Hero {

	@NotNull(message = "Invalid hero type")
	private HeroType type;

	@Size(min=2, max=20, message="Hero's name length must be between 2 and 20 chars")
	private String name;

	private HeroLevel level;
	private HeroStats stats;

	public Hero(HeroType type)
	{
		this.type = type;
	}

	public Hero(String name, File saveFile)
	{
		this.name = name;
		this.level = new HeroLevel(name); // todo load
		this.stats = new HeroStats(name); // todo load
		// todo load type
	}

	public void loadDefaults()
	{
		this.level = new HeroLevel();
		this.stats = new HeroStats(type);
	}

	public void setName(String name) {
		this.name = name;
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
