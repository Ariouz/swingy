package fr.vicalvez.swingy.view.console.hero.create;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class HeroCreateViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Scanner scanner = new Scanner(System.in);

		if (!selectHeroType(scanner, gameController)) return ;

		gameController.openView(ViewType.HERO_NAME);
	}

	private boolean selectHeroType(Scanner scanner, GameController gameController) {
		System.out.println("Select a hero type:");
		Arrays.stream(HeroType.values()).forEach(type -> {
			System.out.println("- " + type
					+ "; Attack: " + type.getDefaultAttack()
					+ "; Defense: " + type.getDefaultDefense()
					+ "; HP: " + type.getDefaultHP());
		});
		System.out.println("- SWITCH To open the game in GUI mode");

		String typeStr = scanner.nextLine();
		if (!gameController.getHeroController().createHero(typeStr)) {
			gameController.openView(ViewType.HERO_CREATE);
			return false;
		}
		return true;
	}

}
