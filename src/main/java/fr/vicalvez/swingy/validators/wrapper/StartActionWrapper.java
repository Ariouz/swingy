package fr.vicalvez.swingy.validators.wrapper;

import fr.vicalvez.swingy.model.game.GameStartAction;
import fr.vicalvez.swingy.validators.EnumConstraint;

public class StartActionWrapper {

	@EnumConstraint(enumClass = GameStartAction.class, message = "Invalid start action")
	private final String action;

	public StartActionWrapper(String action)
	{
		this.action = action;
	}

	public String getAction() {
		return action;
	}
}
