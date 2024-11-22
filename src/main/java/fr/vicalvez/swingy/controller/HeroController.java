package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.validators.ValidationUtil;

public class HeroController {

	private Hero hero;

	public HeroController() { }

	public void loadHero(String heroName) { }

	public boolean createHero(String typeStr)
	{
		HeroType heroType = HeroType.getByName(typeStr);
		Hero hero = new Hero(heroType);

		if (ValidationUtil.isInvalid(hero))
		{
			ValidationUtil.printValidationError(hero);
			return false;
		}

		this.hero = hero;
		this.hero.loadDefaults();
		return true;
	}

	public boolean setHeroName(String name)
	{
		String tmp = this.hero.getName();
		this.hero.setName(name);

		if (ValidationUtil.isInvalid(hero))
		{
			this.getHero().setName(tmp);
			return false;
		}
		return true;
	}

	public Hero getHero() {
		return hero;
	}
}
