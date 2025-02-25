package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.game.Map;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.view.ViewType;

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
        return mapController.isOnBorder(gameController.getHeroController().getHero().getLocation());
    }

	public void win()
	{
		gameController.openView(ViewType.WIN);
	}

	public MapController getMapController() {
		return mapController;
	}


}
