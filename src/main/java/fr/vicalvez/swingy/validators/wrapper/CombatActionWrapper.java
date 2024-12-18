package fr.vicalvez.swingy.validators.wrapper;

import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.validators.EnumConstraint;

public class CombatActionWrapper {

	@EnumConstraint(enumClass = CombatAction.class, message = "Invalid action")
	private final String action;

	public CombatActionWrapper(String action)
	{
		this.action = action;
	}

	public String getAction() {
		return action;
	}

}
