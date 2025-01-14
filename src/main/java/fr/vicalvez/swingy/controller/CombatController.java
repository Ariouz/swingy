package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
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

	// true if hero wins, otherwise false
	public boolean simulateCombat() {
		Hero hero = gameController.getHeroController().getHero();
		Location heroLoc = hero.getLocation();
		Villain villain = gameController.getLevelController().getMapController().getMap().getVillainAt(heroLoc);

		System.out.println("Combat is processing");

		int heroHP = hero.getStats().getAttribute(HeroAttribute.HIT_POINTS);
		int villainHP = villain.getHitPoints();

		Random random = new Random();
		// 10% chance to dealt more damages
		double chanceForHeroToHit = 0.1;

		int heroAttack = hero.getStats().getAttribute(HeroAttribute.ATTACK);
		int heroDefense = hero.getStats().getAttribute(HeroAttribute.DEFENSE);

		int villainAttack = villain.getAttack();
		int villainDefense = villain.getDefense();

		while (heroHP > 0 && villainHP > 0) {
			int heroDamage = heroAttack - (int) (0.6 * villainDefense);

			// Hero attack bonus chance
			if (random.nextDouble() < chanceForHeroToHit) {
				heroDamage += random.nextInt(5);
			}

			villainHP -= heroDamage;
			System.out.println("Le héros attaque, infligeant " + heroDamage + " dégâts. " + villainHP + " HP restants pour le vilain.");

			if (villainHP <= 0) {
				System.out.println("Le vilain est vaincu !");
				return true;
			}

			int villainDamage = villainAttack - (int) (0.6 * heroDefense);

			heroHP -= villainDamage;
			System.out.println("Le vilain attaque, infligeant " + villainDamage + " dégâts. " + heroHP + " HP restants pour le héros.");

			if (heroHP <= 0) {
				System.out.println("Le héros est vaincu !");
				return false;
			}
		}
		return false;
	}

	public double calculateVillainXP(Villain villain) {
		int villainAttack = villain.getAttack();
		int villainDefense = villain.getDefense();
		int villainHP = villain.getHitPoints();

		return (villainAttack * 1.2) * (villainDefense * 0.8) + (villainHP * 5);
	}

	public boolean isValidFightAction(String action)
	{
		CombatActionWrapper wrapper = new CombatActionWrapper(action);
		if (ValidationUtil.isInvalid(wrapper))
		{
			ValidationUtil.printValidationError(wrapper, null, gameController.getMode());
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

		int rand = new Random().nextInt(2);
		if (rand == 0){
			System.out.println("You fell on a rock while running, you got to fight!");
			this.simulateCombat();
			return;
		}

		Hero hero = gameController.getHeroController().getHero();
		gameController.getLevelController().getMapController().go(hero.getLastMove(), true);
		gameController.openView(ViewType.GAME_LEVEL);
	}

}
