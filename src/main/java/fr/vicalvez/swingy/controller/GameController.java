package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.view.GameView;
import fr.vicalvez.swingy.view.ViewType;

public class GameController {

	private final HeroController heroController;
	private final StartController startController;

	private RunMode mode;

	public GameController(RunMode mode) {
		this.mode = mode;
		this.heroController = new HeroController();
		this.startController = new StartController();
	}

	public void openView(ViewType viewType) {
		try {
			Class<? extends GameView> viewClass = this.getMode() == RunMode.CONSOLE
					? viewType.getConsoleClass()
					: viewType.getGuiClass();

			if (viewClass == null) return;

			GameView view = viewClass.getConstructor().newInstance();
			view.open(this);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public RunMode getMode() {
		return mode;
	}

	public void setMode(RunMode mode) {
		this.mode = mode;
	}

	public HeroController getHeroController() {
		return heroController;
	}

	public StartController getStartController() {
		return startController;
	}
}
