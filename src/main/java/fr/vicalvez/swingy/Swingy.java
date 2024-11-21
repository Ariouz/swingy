package fr.vicalvez.swingy;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.view.ViewType;

public class Swingy {
	public static void main(String[] args) {

		GameController gameController = new GameController(RunMode.CONSOLE);

		gameController.openView(ViewType.START);
	}
}