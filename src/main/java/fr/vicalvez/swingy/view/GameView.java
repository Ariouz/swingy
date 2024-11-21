package fr.vicalvez.swingy.view;

import fr.vicalvez.swingy.controller.GameController;

public abstract class GameView {

	protected GameView() { }

	public abstract void open(GameController gameController);

}
