package fr.vicalvez.swingy.validators.wrapper;

import fr.vicalvez.swingy.model.game.EndAction;
import fr.vicalvez.swingy.validators.EnumConstraint;

public class EndActionWrapper {

	@EnumConstraint(enumClass = EndAction.class, message = "Invalid action")
	private final String action;

	public EndActionWrapper(String action)
	{
		this.action = action;
	}

	public String getAction() {
		return action;
	}

}
