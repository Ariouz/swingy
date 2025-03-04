package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.sql.SQLHero;
import fr.vicalvez.swingy.sql.SQLHeroEntry;
import fr.vicalvez.swingy.validators.ValidationUtil;

import javax.swing.*;

public class HeroController {

	private Hero hero;

	private final GameController gameController;

	public HeroController(GameController gameController) {
		this.gameController = gameController;
	}

	public void loadHero(int heroId) {
		SQLHeroEntry sqlHero = gameController.getSqlManager().getSqlHero().getHero(heroId);
		if (sqlHero == null) {return ;}

		this.hero = new Hero(sqlHero.getId(), sqlHero.getName());
		this.hero.setType(sqlHero.getType());
		this.hero.loadStats();
		this.hero.getStats().setAttribute(HeroAttribute.ATTACK, sqlHero.getAttack());
		this.hero.getStats().setAttribute(HeroAttribute.DEFENSE, sqlHero.getDefense());
		this.hero.getStats().setAttribute(HeroAttribute.HIT_POINTS, sqlHero.getHelm());
		this.hero.getLevel().setExperience(sqlHero.getExperience());
		this.hero.getLevel().setLevel(sqlHero.getLevel());

		this.gameController.getLevelController().getMapController().generate(this.hero);
		this.gameController.getLevelController().getMapController().getMap().setLocationToCenter(this.hero.getLocation());
	}

	public boolean createHero(String typeStr) {
		HeroType heroType = HeroType.getByName(typeStr);
		Hero hero = new Hero(heroType);

		if (ValidationUtil.isInvalid(hero)) {
			if (typeStr.equals("SWITCH"))
				gameController.setMode(gameController.getMode() == RunMode.GUI ? RunMode.CONSOLE : RunMode.GUI);
			else {
				ValidationUtil.printValidationError(hero, null, gameController.getMode());
			}
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
		gameController.getLevelController().nextLevel(gameController.getHeroController().getHero());
		return true;
	}

	public void saveHero()
	{
		SQLHero sqlHero = gameController.getSqlManager().getSqlHero();;
		if (sqlHero.exists(hero)) {
			sqlHero.updateHero(hero);
			System.out.println(hero.getName() + " stats have been updated!");
		} else {
			sqlHero.insert(hero);
			System.out.println(hero.getName() + " stats have been saved!");
		}
	}

	public Hero getHero() {
		return hero;
	}
}
