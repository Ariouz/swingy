package fr.vicalvez.swingy.view.console.level.villain;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Arrays;
import java.util.Scanner;

public class MeetVillainViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Hero hero = gameController.getHeroController().getHero();
		Villain villain = gameController.getLevelController().getMapController().getMap().getVillainAt(hero.getLocation());

		System.out.println("Villain:");
		System.out.printf("Attack: %s; Defense: %s; HP: %s\n", villain.getAttack(), villain.getDefense(), villain.getHitPoints());

		askAction(gameController);
	}

	public void askAction(GameController gameController)
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("You have met a villain, what will you do ?");
		Arrays.stream(CombatAction.values()).forEach(action -> System.out.println("- " + action));

		String action = scanner.nextLine();
		if (!gameController.getCombatController().isValidFightAction(action)){
			gameController.openView(ViewType.MEET_VILLAIN);
			return ;
		}

		CombatAction combatAction = CombatAction.valueOf(action);
		gameController.getCombatController().processAction(combatAction);
	}

}
