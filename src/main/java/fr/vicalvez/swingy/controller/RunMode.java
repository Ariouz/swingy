package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.game.GameStartAction;

import java.util.Arrays;

public enum RunMode {

	CONSOLE,
	GUI;

	public static RunMode getByName(String name)
	{
		return Arrays.stream(values())
				.filter(type -> type.name().equals(name))
				.findFirst()
				.orElse(null);
	}

}
