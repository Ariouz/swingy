package fr.vicalvez.swingy;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.RunModeWrapper;
import fr.vicalvez.swingy.view.ViewType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Swingy {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("org.hibernate.validator");
		logger.setLevel(Level.OFF);

		if (args.length == 0) {
			System.out.println("Arguments missing");
			return ;
		}

		String action = args[0].toUpperCase();
		RunModeWrapper runModeWrapper = new RunModeWrapper(action);
		if (ValidationUtil.isInvalid(runModeWrapper))
		{
			ValidationUtil.printValidationError(runModeWrapper, null, RunMode.CONSOLE);
			return;
		}

		GameController gameController = new GameController(RunMode.getByName(action));
		gameController.openView(ViewType.START);
	}
}