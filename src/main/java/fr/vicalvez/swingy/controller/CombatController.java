package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.CombatActionWrapper;
import fr.vicalvez.swingy.view.ViewType;

import java.util.Random;

public class CombatController {

	private final GameController gameController;

	public CombatController(GameController gameController){
		this.gameController = gameController;
	}

	public void simulateCombat()
	{
		Hero hero = gameController.getHeroController().getHero();
		Location heroLoc = hero.getLocation();
		Villain villain = gameController.getLevelController().getMapController().getMap().getVillainAt(heroLoc);

		System.out.println("Combat is processing");
		return;
	}

	public boolean isValidFightAction(String action)
	{
		CombatActionWrapper wrapper = new CombatActionWrapper(action);
		if (ValidationUtil.isInvalid(wrapper))
		{
			ValidationUtil.printValidationError(wrapper);
			return false;
		}

		return true;
	}

	public void processAction(CombatAction combatAction)
	{
		if (combatAction == CombatAction.FIGHT){
			this.simulateCombat();
			return;
		}

		int rand = new Random().nextInt() % 2;
		if (rand == 0){
			System.out.println("You fell on a rock while running, you got to fight!");
			this.simulateCombat();
			return ;
		}

		Hero hero = gameController.getHeroController().getHero();
		gameController.getLevelController().getMapController().go(hero.getLastMove(), true);
		gameController.openView(ViewType.GAME_LEVEL);
	}

}
