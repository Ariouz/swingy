package fr.vicalvez.swingy.validators.wrapper;

import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.model.game.EndAction;
import fr.vicalvez.swingy.validators.EnumConstraint;

public class RunModeWrapper {

	@EnumConstraint(enumClass = RunMode.class, message = "Invalid run mode")
	private final String mode;

	public RunModeWrapper(String mode)
	{
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

}
