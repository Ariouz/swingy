package fr.vicalvez.swingy;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.view.ViewType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Swingy {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("org.hibernate.validator");
		logger.setLevel(Level.OFF);

		// todo parse mode
		GameController gameController = new GameController(RunMode.CONSOLE);

		gameController.openView(ViewType.START);
	}
}