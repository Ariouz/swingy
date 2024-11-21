package fr.vicalvez.swingy.view.hero.create;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.view.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class HeroCreateViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Select an hero type:");
		Arrays.stream(HeroType.values()).forEach(type -> {
			System.out.println("- " + type
					+ "; Attack: " + type.getDefaultAttack()
					+ "; Defense: " + type.getDefaultDefense()
					+ "; HP: " + type.getDefaultHP());
		});

		String typeStr = scanner.nextLine();

		HeroType type;
		try {
			type = HeroType.valueOf(typeStr);
		}
		catch (Exception e) {
			System.out.println("Invalid type");
			gameController.openView(ViewType.HERO_CREATE);
			return ;
		}

		System.out.println("Enter hero name:");
		String heroName = scanner.nextLine();
		// todo check input
		gameController.getHeroController().createHero(heroName, type);
		gameController.openView(ViewType.HERO_DETAILS);
		gameController.openView(ViewType.GAME_LEVEL);
	}

}
