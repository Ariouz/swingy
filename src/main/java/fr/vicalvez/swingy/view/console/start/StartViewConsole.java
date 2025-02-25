package fr.vicalvez.swingy.view.console.start;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Scanner;

public class StartViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController)
	{
		System.out.println("Welcome to Swingy RPG");
		System.out.println("Type:");
		System.out.println("- CREATE To create a new Hero");
		System.out.println("- SELECT To select a previous Hero");
		System.out.println("- SWITCH To open the game in GUI mode");

		Scanner scanner = new Scanner(System.in);
		String action = scanner.nextLine();

		if (!gameController.getStartController().selectStartAction(action, gameController))
			gameController.openView(ViewType.START);
	}

}
