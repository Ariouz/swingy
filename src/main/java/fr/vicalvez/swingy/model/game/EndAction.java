package fr.vicalvez.swingy.model.game;

import java.util.Arrays;

public enum EndAction {

	PLAY,
	QUIT;

	public static EndAction getByName(String name)
	{
		return Arrays.stream(values())
				.filter(type -> type.name().equals(name))
				.findFirst()
				.orElse(null);
	}

}
