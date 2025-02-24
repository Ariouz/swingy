package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.game.Map;
import fr.vicalvez.swingy.model.hero.Hero;

public class LevelController {

	private final GameController gameController;
	private final MapController mapController;

	public LevelController(GameController gameController)
	{
		this.gameController = gameController;
		this.mapController = new MapController(gameController);
	}

	public void nextLevel(Hero hero)
	{
		this.mapController.getMap().setLocationToCenter(hero.getLocation());
	}

	public void printLevel()
	{
		this.mapController.getMap().printMap();
	}

	public boolean checkLevelWin()
	{
		if (!mapController.isOnBorder(gameController.getHeroController().getHero().getLocation()))
			return false;
		System.out.println("Congrats, you win!");

		//todo win screen with play again (hero selection) or quit
		return true;
	}

	public MapController getMapController() {
		return mapController;
	}


}
