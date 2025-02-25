package fr.vicalvez.swingy.model.hero;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.validators.EnumConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.File;

public class Hero {

	private int id = -1;

	@NotNull(message = "Invalid hero type")
	private HeroType type;

	@Size(min=2, max=20, message="Hero's name length must be between 2 and 20 chars")
	private String name;

	private HeroLevel level;
	private HeroStats stats;
	private Location location;
	private Direction lastMove = Direction.NONE;

	public Hero(HeroType type)
	{
		this.type = type;
	}

	public Hero(int id, String name)
	{
		this.id = id;
		this.name = name;
		this.level = new HeroLevel(name); // todo load
		this.stats = new HeroStats(name); // todo load
		this.location = new Location(0, 0);
	}

	public void loadDefaults()
	{
		this.level = new HeroLevel();
		this.stats = new HeroStats(type);
		this.location = new Location(0, 0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(HeroType type) {
		this.type = type;
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

	public Location getLocation() {
		return location;
	}

	public Direction getLastMove() {
		return lastMove;
	}

	public int getId() {
		return id;
	}
	public void setLastMove(Direction lastMove) {
		this.lastMove = lastMove;
	}
}
