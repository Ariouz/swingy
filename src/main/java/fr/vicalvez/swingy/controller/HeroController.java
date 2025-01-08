package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.validators.ValidationUtil;

import javax.swing.*;

public class HeroController {

	private Hero hero;

	private final GameController gameController;

	public HeroController(GameController gameController) {
		this.gameController = gameController;
	}

	public void loadHero(String heroName) {
		this.gameController.getLevelController().getMapController().getMap().setHero(this.hero);
	}

	public boolean createHero(String typeStr)
	{
		HeroType heroType = HeroType.getByName(typeStr);
		Hero hero = new Hero(heroType);

		if (ValidationUtil.isInvalid(hero))
		{
			ValidationUtil.printValidationError(hero, null, gameController.getMode());
			return false;
		}

		this.hero = hero;
		this.hero.loadDefaults();
		this.gameController.getLevelController().getMapController().generate(hero);
		return true;
	}

	public boolean setHeroName(String name, JLabel errorLabel)
	{
		String tmp = this.hero.getName();
		this.hero.setName(name);

		if (ValidationUtil.isInvalid(hero))
		{
			ValidationUtil.printValidationError(hero, errorLabel, gameController.getMode());
			this.getHero().setName(tmp);
			return false;
		}
		return true;
	}

	public Hero getHero() {
		return hero;
	}
}
