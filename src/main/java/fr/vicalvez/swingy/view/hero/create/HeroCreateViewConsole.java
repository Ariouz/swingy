package fr.vicalvez.swingy.view.hero.create;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.view.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class HeroCreateViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Scanner scanner = new Scanner(System.in);

		if (!selectHeroType(scanner, gameController)) return ;

		askHeroName(scanner, gameController);

		gameController.openView(ViewType.HERO_DETAILS);
		gameController.getLevelController().nextLevel(gameController.getHeroController().getHero());
		gameController.openView(ViewType.GAME_LEVEL);
	}

	private boolean selectHeroType(Scanner scanner, GameController gameController) {
		System.out.println("Select a hero type:");
		Arrays.stream(HeroType.values()).forEach(type -> {
			System.out.println("- " + type
					+ "; Attack: " + type.getDefaultAttack()
					+ "; Defense: " + type.getDefaultDefense()
					+ "; HP: " + type.getDefaultHP());
		});

		String typeStr = scanner.nextLine();
		if (!gameController.getHeroController().createHero(typeStr)) {
			gameController.openView(ViewType.HERO_CREATE);
			return false;
		}
		return true;
	}

	private void askHeroName(Scanner scanner, GameController gameController) {
		System.out.println("Enter hero name:");
		String heroName = scanner.nextLine();

		while (!gameController.getHeroController().setHeroName(heroName)) {
			System.out.println("Enter hero name:");
			heroName = scanner.nextLine();
		}
	}

}
