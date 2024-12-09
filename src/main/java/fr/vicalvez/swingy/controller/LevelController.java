package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.game.Map;
import fr.vicalvez.swingy.model.hero.Hero;

public class LevelController {

	private final GameController gameController;
	private final Map map;

	public LevelController(GameController gameController)
	{
		this.gameController = gameController;
		this.map = new Map();
	}

	public void nextLevel(Hero hero)
	{
		this.map.setLocationToCenter(hero.getLocation());
	}

	public void printLevel()
	{
		this.map.printMap();
	}

	public boolean checkLevelWin()
	{
		if (!map.isOnBorder())
			return false;
		System.out.println("Congrats, you win!");
		return true;
	}

	public Map getMap() {
		return map;
	}

}
