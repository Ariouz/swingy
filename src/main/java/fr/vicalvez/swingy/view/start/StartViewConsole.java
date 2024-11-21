package fr.vicalvez.swingy.view.start;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.view.ConsoleView;
import fr.vicalvez.swingy.view.GameView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Scanner;

public class StartViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController)
	{
		System.out.println("Welcome to Swingy RPG");
		System.out.println("Type:");
		System.out.println("- 1 To create a new Hero");
		System.out.println("- 2 To select a previous Hero");
		Scanner scanner = new Scanner(System.in);
		String action = scanner.nextLine();
		System.out.println("Action: " + action);
		switch (action)
		{
			case "1":
				gameController.openView(ViewType.HERO_CREATE);
				break;
			case "2":
				break;
			default:
				System.out.println("Invalid choice, try again");
				gameController.openView(ViewType.START);
				break;
		}
	}

}
