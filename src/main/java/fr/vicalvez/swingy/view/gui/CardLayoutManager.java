package fr.vicalvez.swingy.view.gui;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.hero.HeroCreateViewGUI;
import fr.vicalvez.swingy.view.gui.level.LevelViewGUI;
import fr.vicalvez.swingy.view.gui.start.StartViewGUI;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class CardLayoutManager extends JFrame  {

	private final CardLayout cardLayout;
	private final Container contentPane;
	private final GameController gameController;

	private LevelViewGUI levelViewGUI;

	private final HashMap<ViewType, JPanel>  panels = new HashMap<>();

	public CardLayoutManager(GameController gameController)
	{
		this.setTitle("Swingy");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.gameController = gameController;
		this.cardLayout = new CardLayout();
		this.contentPane = getContentPane();

		this.loadPanels(gameController);
	}

	public void showView(String viewName)
	{
		if (viewName.equals(ViewType.GAME_LEVEL.getGuiPanelName())) updateLevelView();
		this.cardLayout.show(contentPane, viewName);
	}

	public void loadPanels(GameController gameController)
	{
		StartViewGUI startViewGUI = new StartViewGUI();
		HeroCreateViewGUI heroCreateViewGUI = new HeroCreateViewGUI();
		levelViewGUI = new LevelViewGUI();

		contentPane.setLayout(this.cardLayout);

		contentPane.add(ViewType.START.getGuiPanelName(), startViewGUI.createStartPanel(gameController));
		contentPane.add(ViewType.HERO_CREATE.getGuiPanelName(), heroCreateViewGUI.createHeroTypeView(gameController));
		contentPane.add(ViewType.HERO_NAME.getGuiPanelName(), heroCreateViewGUI.createHeroNameView(gameController));

		JPanel levelPanel = levelViewGUI.createLevelView(gameController);
		panels.put(ViewType.GAME_LEVEL, levelPanel);
		contentPane.add(ViewType.GAME_LEVEL.getGuiPanelName(), levelPanel);
	}

	public void updateLevelView()
	{
		levelViewGUI.updateHeroInfoPanel(gameController.getHeroController().getHero());
		levelViewGUI.updateMapToTextPane(gameController.getLevelController().getMapController().getMap(), gameController.getHeroController().getHero());
	}

}
