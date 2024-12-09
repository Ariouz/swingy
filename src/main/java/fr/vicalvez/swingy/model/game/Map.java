package fr.vicalvez.swingy.model.game;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.model.villains.VillainBuilder;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.DirectionWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Map {

	private Hero hero;
	private final HashMap<Location, Villain> villains = new HashMap<>();

	public Map() { }

	public void printMap()
	{
		int mapSize = getMapSize();
		for (int y = 0; y < mapSize; y++)
		{
			for (int x = 0; x < mapSize; x++)
			{
				if (hero.getLocation().equals(x, y))
					System.out.print("H");
				else
					System.out.print(isVillainAt(new Location(x, y)) ? "X" : ".");
			}
			System.out.println();
		}
	}

	public void spreadVillains()
	{
		int amount = (hero.getLevel().getLevel() + 1) * 8;
		int mapSize = getMapSize();
		for (int i = 0; i < amount; i++)
		{
			int x = new Random().nextInt(0, mapSize);
			int y = new Random().nextInt(0, mapSize);

			Location location = new Location(x, y);
			while (location.equals(getMapCenterLocation()) || isVillainAt(location))
			{
				x = new Random().nextInt(0, mapSize);
				y = new Random().nextInt(0, mapSize);
				location.set(x, y);
			}
			villains.put(location, VillainBuilder.generate(hero.getStats(), location));
		}
	}

	public boolean isVillainAt(Location location)
	{
		return villains.keySet().stream().anyMatch(loc -> loc.equals(location));
	}

	public int getMapSize()
	{
		int level = hero.getLevel().getLevel();
		return (level - 1) * 5 + 10 - (level % 2);
	}

	public void setHero(Hero hero) {
		this.hero = hero;
		this.spreadVillains();
	}

	public Location getMapCenterLocation()
	{
		int mapSize = getMapSize();
		return new Location(mapSize / 2, mapSize / 2);
	}

	public void setLocationToCenter(Location location)
	{
		Location center = getMapCenterLocation();
		location.set(center.getX(), center.getY());
	}

	public boolean goTo(String directionStr)
	{
		DirectionWrapper directionWrapper = new DirectionWrapper(directionStr);

		if (ValidationUtil.isInvalid(directionWrapper))
		{
			ValidationUtil.printValidationError(directionWrapper);
			return false;
		}

		Direction direction = Direction.valueOf(directionStr);
		go(direction, false);
		return true;
	}

	public void go(Direction direction, boolean opposite)
	{
		Location location = hero.getLocation();

		if (opposite)
		{
			location.removeX(direction.getXOffset());
			location.removeY(direction.getYOffset());
		}
		else {
			location.addX(direction.getXOffset());
			location.addY(direction.getYOffset());
		}
	}

	public boolean isOnBorder()
	{
		Location location = hero.getLocation();
		int mapSize = getMapSize();

		return (location.getX() == 0 || location.getX() == mapSize - 1) ||
				(location.getY() == 0 || location.getY() == mapSize - 1);
	}


}
