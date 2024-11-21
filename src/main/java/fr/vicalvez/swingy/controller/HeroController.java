package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroType;

public class HeroController {

	private Hero hero;

	public HeroController()
	{

	}

	public void loadHero(String heroName)
	{

	}

	public void createHero(String heroName, HeroType type)
	{
		this.hero = new Hero(type, heroName);
	}

	public Hero getHero() {
		return hero;
	}
}
