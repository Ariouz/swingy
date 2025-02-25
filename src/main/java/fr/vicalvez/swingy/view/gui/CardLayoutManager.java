package fr.vicalvez.swingy.view.gui;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.hero.DeathViewGui;
import fr.vicalvez.swingy.view.gui.hero.HeroCreateViewGUI;
import fr.vicalvez.swingy.view.gui.level.LevelViewGUI;
import fr.vicalvez.swingy.view.gui.level.VillainFightViewGui;
import fr.vicalvez.swingy.view.gui.level.VillainMeetViewGUI;
import fr.vicalvez.swingy.view.gui.start.StartViewGUI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CardLayoutManager extends JFrame  {

	private final CardLayout cardLayout;
	private final Container contentPane;
	private final GameController gameController;

	private HeroCreateViewGUI heroCreateViewGUI;
	private LevelViewGUI levelViewGUI;
	private VillainMeetViewGUI villainMeetViewGUI;
	private VillainFightViewGui villainFightViewGui;

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
		if (viewName.equals(ViewType.MEET_VILLAIN.getGuiPanelName())) updateVillainMeetView();
		this.cardLayout.show(contentPane, viewName);
	}

	public void loadPanels(GameController gameController)
	{
		StartViewGUI startViewGUI = new StartViewGUI();
		heroCreateViewGUI = new HeroCreateViewGUI();
		villainMeetViewGUI = new VillainMeetViewGUI();
		levelViewGUI = new LevelViewGUI();
		villainFightViewGui = new VillainFightViewGui();
		DeathViewGui deathViewGui = new DeathViewGui();

		contentPane.setLayout(this.cardLayout);

		contentPane.add(ViewType.START.getGuiPanelName(), startViewGUI.createStartPanel(gameController));
		contentPane.add(ViewType.HERO_CREATE.getGuiPanelName(), heroCreateViewGUI.createHeroTypeView(gameController));
		contentPane.add(ViewType.HERO_NAME.getGuiPanelName(), heroCreateViewGUI.createHeroNameView(gameController));
		contentPane.add(ViewType.MEET_VILLAIN.getGuiPanelName(), villainMeetViewGUI.createVillainMeetView(gameController));
		contentPane.add(ViewType.FIGHT_VILLAIN.getGuiPanelName(), villainFightViewGui.createVillainFightView(gameController));
		contentPane.add(ViewType.DEATH.getGuiPanelName(), deathViewGui.createDeathPanel(gameController));

		JPanel levelPanel = levelViewGUI.createLevelView(gameController);
		panels.put(ViewType.GAME_LEVEL, levelPanel);
		contentPane.add(ViewType.GAME_LEVEL.getGuiPanelName(), levelPanel);
	}

	public void updateLevelView()
	{
		Hero hero = gameController.getHeroController().getHero();

		levelViewGUI.updateHeroInfoPanel(hero);
		levelViewGUI.updateMapToTextPane(gameController.getLevelController().getMapController().getMap(), gameController.getHeroController().getHero());
	}

	public void updateVillainMeetView()
	{
		villainMeetViewGUI.updateView(gameController);
	}

	public LevelViewGUI getLevelViewGUI() {
		return levelViewGUI;
	}

	public VillainFightViewGui getVillainFightViewGui() {
		return villainFightViewGui;
	}
}
