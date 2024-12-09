package fr.vicalvez.swingy.validators.wrapper;

import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.validators.EnumConstraint;

public class DirectionWrapper {

	@EnumConstraint(enumClass = Direction.class, message = "Invalid direction")
	private final String direction;

	public DirectionWrapper(String direction)
	{
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}

