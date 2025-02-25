package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.model.game.Map;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.DirectionWrapper;
import fr.vicalvez.swingy.view.ViewType;

public class MapController {

	private final GameController gameController;
	private final Map map;

	public MapController(GameController gameController)
	{
		this.gameController = gameController;
		this.map = new Map();
	}

	public void generate(Hero hero)
	{
		map.setHero(hero);
		map.spreadVillains();
	}

	public boolean goTo(String directionStr)
	{
		DirectionWrapper directionWrapper = new DirectionWrapper(directionStr);

		if (ValidationUtil.isInvalid(directionWrapper))
		{
			if (directionStr.equals("SWITCH"))
				gameController.setMode(gameController.getMode() == RunMode.GUI ? RunMode.CONSOLE : RunMode.GUI);
			else {
				ValidationUtil.printValidationError(directionWrapper, null, gameController.getMode());
			}
			return false;
		}

		Direction direction = Direction.valueOf(directionStr);
		go(direction, false);
		return true;
	}

	public void go(Direction direction, boolean opposite)
	{
		Location location = map.getHero().getLocation();
		gameController.getHeroController().getHero().setLastMove(direction);

		if (opposite)
		{
			location.removeX(direction.getXOffset());
			location.removeY(direction.getYOffset());
		}
		else {
			location.addX(direction.getXOffset());
			location.addY(direction.getYOffset());
		}

		System.out.println(location.getX() + " " + location.getY());

		if (map.isVillainAt(location)){
			gameController.openView(ViewType.MEET_VILLAIN);
			return;
		}

		gameController.openView(ViewType.GAME_LEVEL);
	}

	public boolean isOnBorder(Location location)
	{
		int mapSize = map.getMapSize();

		return (location.getX() == 0 || location.getX() == mapSize - 1) ||
				(location.getY() == 0 || location.getY() == mapSize - 1);
	}

	public Map getMap() {
		return map;
	}
}
