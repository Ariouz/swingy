package fr.vicalvez.swingy.view.console.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class LevelViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		gameController.getLevelController().printLevel();

		if (!askDirection(gameController)) return;

		if (gameController.getLevelController().checkLevelWin())
			// TODO PLAY AGAIN OR EXIT VIEW
			return ;

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
