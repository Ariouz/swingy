package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.game.GameStartAction;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.StartActionWrapper;
import fr.vicalvez.swingy.view.ViewType;

public class StartController {

	public boolean selectStartAction(String action, GameController gameController)
	{
		StartActionWrapper wrapper = new StartActionWrapper(action);
		if (ValidationUtil.isInvalid(wrapper)){
			if (action.equals("SWITCH"))
				gameController.setMode(gameController.getMode() == RunMode.GUI ? RunMode.CONSOLE : RunMode.GUI);
			else {
				ValidationUtil.printValidationError(wrapper, null, gameController.getMode());
			}
			return false;
		}

		GameStartAction gameStartAction = GameStartAction.getByName(action);
		switch (gameStartAction) {
			case CREATE:
				gameController.openView(ViewType.HERO_CREATE);
			case SELECT:
				break;
		}
		return true;
	}

}
