package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.view.GameView;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.CardLayoutManager;

import javax.swing.*;

public class GameController {

	private final HeroController heroController;
	private final StartController startController;
	private final LevelController levelController;
	private final CombatController combatController;
	private final CardLayoutManager cardLayoutManager;

	private RunMode mode;

	public GameController(RunMode mode) {
		this.heroController = new HeroController(this);
		this.startController = new StartController();
		this.levelController = new LevelController(this);
		this.combatController = new CombatController(this);

		this.cardLayoutManager = new CardLayoutManager(this);
		setMode(mode);
	}

	public void openView(ViewType viewType) {
		if (this.mode == RunMode.GUI) {
			this.cardLayoutManager.showView(viewType.getGuiPanelName());
			return ;
		}

		try {
			Class<? extends GameView> viewClass = viewType.getConsoleClass();
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
		this.cardLayoutManager.setVisible(mode == RunMode.GUI);
	}

	public HeroController getHeroController() {
		return heroController;
	}

	public StartController getStartController() {
		return startController;
	}

	public LevelController getLevelController() {
		return levelController;
	}

	public CombatController getCombatController() {
		return combatController;
	}

	public CardLayoutManager getCardLayoutManager() {
		return cardLayoutManager;
	}
}
