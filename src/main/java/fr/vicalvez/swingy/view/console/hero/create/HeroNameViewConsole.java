package fr.vicalvez.swingy.view.console.hero.create;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.console.ConsoleView;

import java.util.Scanner;

public class HeroNameViewConsole extends ConsoleView {
	@Override
	public void open(GameController gameController) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter hero name:");
		String heroName = scanner.nextLine();

		while (!gameController.getHeroController().setHeroName(heroName, null)) {
			System.out.println("Enter hero name:");
			heroName = scanner.nextLine();
		}

		gameController.openView(ViewType.HERO_DETAILS);
		gameController.openView(ViewType.GAME_LEVEL);
	}
}
