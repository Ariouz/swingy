package fr.vicalvez.swingy.view;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class HeroView extends GameView{

	@Override
	public void open(GameController gameController) {
		Hero hero = gameController.getHeroController().getHero();

		System.out.println("Hero name: " + hero.getName());
		System.out.println("Level: " + hero.getLevel().getLevel() + " | " + hero.getLevel().getExperience() + " / " + hero.getLevel().getLevelExperience());
		System.out.println("Attributes:");
		System.out.println("Attack: " + hero.getStats().getAttribute(HeroAttribute.ATTACK));
		System.out.println("Defense: " + hero.getStats().getAttribute(HeroAttribute.DEFENSE));
		System.out.println("HP: " + hero.getStats().getAttribute(HeroAttribute.HIT_POINTS));
	}

}
