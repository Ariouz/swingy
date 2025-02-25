package fr.vicalvez.swingy.model.villains;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.model.hero.HeroStats;

import java.util.Random;

public class VillainBuilder {

	public static Villain generate(HeroStats heroStats, Location location)
	{
		double multiplier = new Random().nextDouble(0.8, 1.2);
		int attack = (int) (heroStats.getAttribute(HeroAttribute.ATTACK) * multiplier + new Random().nextInt(-5, 5));
		int defense = (int) (heroStats.getAttribute(HeroAttribute.DEFENSE) * multiplier + new Random().nextInt(-5, 5));
		int hp = (int) (heroStats.getAttribute(HeroAttribute.HIT_POINTS) * multiplier + new Random().nextInt(-5, 5));

		return new Villain()
				.setAttack(Math.max(attack, 1))
				.setDefense(Math.max(defense, 1))
				.setHP(Math.max(hp, 1))
				.setLocation(location);
	}

}
