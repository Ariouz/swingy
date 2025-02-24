package fr.vicalvez.swingy.view.console.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class LevelViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Hero hero = gameController.getHeroController().getHero();

		System.out.println("------------ HERO STATS -------------");
		Arrays.stream(HeroAttribute.values()).forEach(attr -> {
			System.out.println(attr+ ": " + hero.getStats().getAttribute(attr));
		});
		System.out.println("Level: " + hero.getLevel().getLevel() + " " + hero.getLevel().getExperience() + "/" + hero.getLevel().getLevelExperience());
		System.out.println("-------------------------------------");

		gameController.getLevelController().printLevel();

		if (!askDirection(gameController)) return;
	}

	public boolean askDirection(GameController gameController)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Choose a direction");
		Arrays.stream(Direction.values())
				.filter(type -> type != Direction.NONE)
				.forEach(type -> System.out.println("- " + type)) ;

		String directionStr = scanner.nextLine();
		if (!gameController.getLevelController().getMapController().goTo(directionStr))
		{
			gameController.openView(ViewType.GAME_LEVEL);
			return false;
		}
		return true;
	}

}
