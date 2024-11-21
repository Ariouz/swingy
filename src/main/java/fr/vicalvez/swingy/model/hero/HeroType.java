package fr.vicalvez.swingy.model.hero;

import java.util.HashMap;

public enum HeroType {

	LION(11, 5, 7),
	TURTLE(5, 13, 9),
	PANDA(4, 5, 25)

	;

	private final int defaultAttack;
	private final int defaultDefense;
	private final int defaultHP;

	private final HashMap<HeroAttribute, Integer> defaultAttribute = new HashMap<>();

	HeroType(int defaultAttack, int defaultDefense, int defaultHP)
	{
		this.defaultAttack = defaultAttack;
		this.defaultDefense = defaultDefense;
		this.defaultHP = defaultHP;

		defaultAttribute.put(HeroAttribute.ATTACK, getDefaultAttack());
		defaultAttribute.put(HeroAttribute.DEFENSE, getDefaultDefense());
		defaultAttribute.put(HeroAttribute.HIT_POINTS, getDefaultHP());
	}

	public int getDefaultAttack() {
		return defaultAttack;
	}

	public int getDefaultDefense() {
		return defaultDefense;
	}

	public int getDefaultHP() {
		return defaultHP;
	}

	public int getDefaultAttribute(HeroAttribute attribute) {
		return defaultAttribute.get(attribute);
	}
}
