package fr.vicalvez.swingy.view.hero.info;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.model.hero.HeroLevel;
import fr.vicalvez.swingy.view.ConsoleView;

public class HeroInfoViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Hero hero = gameController.getHeroController().getHero();
		HeroLevel heroLevel = hero.getLevel();

		System.out.println("Hero information:");
		System.out.println("Name: " + hero.getName());
		System.out.println("Type: " + hero.getType().toString().toLowerCase());
		System.out.println("Level: " + heroLevel.getLevel() + " | " + heroLevel.getExperience() + " / " + heroLevel.getLevelExperience());
		System.out.println("Attack: " + hero.getStats().getAttribute(HeroAttribute.ATTACK));
		System.out.println("Defense: " + hero.getStats().getAttribute(HeroAttribute.DEFENSE));
		System.out.println("HP: " + hero.getStats().getAttribute(HeroAttribute.HIT_POINTS));
	}

}
