package fr.vicalvez.swingy.model.villains;

import fr.vicalvez.swingy.model.Location;

public class Villain {

	private int attack;
	private int defense;
	private int hitPoints;

	private Location location;

	public Villain()
	{
		this.attack = 0;
		this.defense = 0;
		this.hitPoints = 0;
		this.location = null;
	}

	public Villain setAttack(int attack)
	{
		this.attack = attack;
		return this;
	}

	public Villain setDefense(int defense)
	{
		this.defense = defense;
		return this;
	}

	public Villain setHP(int hp)
	{
		this.hitPoints = hp;
		return this;
	}

	public Villain setLocation(Location location) {
		this.location = location;
		return this;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public Location getLocation() {
		return location;
	}
}
