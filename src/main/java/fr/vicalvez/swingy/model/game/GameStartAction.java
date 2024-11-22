package fr.vicalvez.swingy.model.game;

import fr.vicalvez.swingy.model.hero.HeroType;

import java.util.Arrays;

public enum GameStartAction {

	CREATE,
	SELECT;

	public static GameStartAction getByName(String name)
	{
		return Arrays.stream(values())
				.filter(type -> type.name().equals(name))
				.findFirst()
				.orElse(null);
	}

}
