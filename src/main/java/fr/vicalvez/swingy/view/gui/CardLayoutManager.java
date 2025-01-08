package fr.vicalvez.swingy.view.gui;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.hero.HeroCreateViewGUI;
import fr.vicalvez.swingy.view.gui.start.StartViewGUI;

import javax.swing.*;
import java.awt.*;

public class CardLayoutManager extends JFrame  {

	private final CardLayout cardLayout;
	private final Container contentPane;

	public CardLayoutManager(GameController gameController)
	{
		this.setTitle("Swingy");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(800, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(false);

		this.cardLayout = new CardLayout();
		this.contentPane = getContentPane();

		this.loadPanels(gameController);
	}

	public void showView(String viewName)
	{
		this.cardLayout.show(contentPane, viewName);
	}

	public void loadPanels(GameController gameController)
	{
		StartViewGUI startViewGUI = new StartViewGUI();
		HeroCreateViewGUI heroCreateViewGUI = new HeroCreateViewGUI();

		contentPane.setLayout(this.cardLayout);

		contentPane.add(ViewType.START.getGuiPanelName(), startViewGUI.createStartPanel(gameController));
		contentPane.add(ViewType.HERO_CREATE.getGuiPanelName(), heroCreateViewGUI.createHeroTypeView(gameController));
		contentPane.add(ViewType.HERO_NAME.getGuiPanelName(), heroCreateViewGUI.createHeroNameView(gameController));
	}

}
