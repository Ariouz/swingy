package fr.vicalvez.swingy.view.gui.hero;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.model.hero.HeroType;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class HeroCreateViewGUI {

	public JPanel createHeroTypeView(GameController gameController)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));


		JPanel topSection = new JPanel(new BorderLayout());
		String title = ("<html><h2>Select a hero type</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		JButton backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(70, 20));
		GUIStyle.styleButton(backButton, ButtonColor.GRAY, 14);
		backButton.addActionListener(event -> gameController.getCardLayoutManager().showView(ViewType.START.getGuiPanelName()));
		buttonPanel.add(backButton);

		JButton switchButton = new JButton("Switch");
		switchButton.setPreferredSize(new Dimension(70, 20));
		GUIStyle.styleButton(switchButton, ButtonColor.GRAY, 14);
		switchButton.addActionListener(event -> {gameController.setMode(RunMode.CONSOLE); gameController.openView(ViewType.HERO_CREATE);});
		buttonPanel.add(switchButton);

		topSection.add(titleLabel, BorderLayout.CENTER);
		topSection.add(buttonPanel, BorderLayout.EAST);
		panel.add(topSection);

		JPanel typesPanel = new JPanel();
		typesPanel.setLayout(new GridLayout(2, HeroType.values().length, 10, 10));

		Arrays.stream(HeroType.values()).forEach(type -> {
			JPanel heroStatsPanel = new JPanel();
			heroStatsPanel.setLayout(new GridLayout(4, 1, 10, 10));
			heroStatsPanel.setBorder(new EmptyBorder(0, 20, 0, 20));

			JLabel typeName = new JLabel(type.name());
			JLabel attack = new JLabel("Attack: " + type.getDefaultAttack());
			JLabel defense = new JLabel("Defense: " + type.getDefaultDefense());
			JLabel hp = new JLabel("HP: " + type.getDefaultHP());

			heroStatsPanel.add(typeName);
			heroStatsPanel.add(attack);
			heroStatsPanel.add(defense);
			heroStatsPanel.add(hp);

			typesPanel.add(heroStatsPanel);
		});

		Arrays.stream(HeroType.values()).forEach(type -> {
			JButton chooseHeroButton = new JButton("Select " + type.name());
			GUIStyle.styleButton(chooseHeroButton, ButtonColor.GREEN, 14);
			setHeroTypeEvent(chooseHeroButton, type, gameController);
			typesPanel.add(chooseHeroButton);
		});


		panel.add(typesPanel);

		return panel;
	}

	public void setHeroTypeEvent(JButton button, HeroType type, GameController gameController)
	{
		button.addActionListener(event -> {
			gameController.getHeroController().createHero(type.name());
			gameController.openView(ViewType.HERO_NAME);
		});
	}


	public JPanel createHeroNameView(GameController gameController)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel topSection = new JPanel(new BorderLayout());
		String title = ("<html><h2>Enter hero name</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		JButton backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(70, 20));
		GUIStyle.styleButton(backButton, ButtonColor.GRAY, 14);
		backButton.addActionListener(event -> gameController.getCardLayoutManager().showView(ViewType.HERO_CREATE.getGuiPanelName()));
		buttonPanel.add(backButton);

		JButton switchButton = new JButton("Switch");
		switchButton.setPreferredSize(new Dimension(70, 20));
		GUIStyle.styleButton(switchButton, ButtonColor.GRAY, 14);
		switchButton.addActionListener(event -> {gameController.setMode(RunMode.CONSOLE); gameController.openView(ViewType.HERO_NAME);});
		buttonPanel.add(switchButton);

		topSection.add(titleLabel, BorderLayout.CENTER);
		topSection.add(buttonPanel, BorderLayout.EAST);
		panel.add(topSection);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2, 1, 10, 10));

		JTextField nameInput = new JTextField("My Hero");
		nameInput.setPreferredSize(new Dimension(200, 25));

		JPanel inputPanel = new JPanel();
		JLabel errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		errorLabel.setForeground(Color.RED);

		JButton validateName = new JButton("Save");
		GUIStyle.styleButton(validateName, ButtonColor.GREEN, 12);
		setHeroNameEvent(validateName, gameController, nameInput, errorLabel);

		inputPanel.add(nameInput);
		inputPanel.add(validateName);

		namePanel.add(inputPanel);
		namePanel.add(errorLabel);

		panel.add(namePanel);
		return panel;
	}

	public void setHeroNameEvent(JButton button, GameController gameController, JTextField input, JLabel errorLabel)
	{
		button.addActionListener(event -> {
			if (!gameController.getHeroController().setHeroName(input.getText(), errorLabel)) return ;
			gameController.openView(ViewType.GAME_LEVEL);
		});
	}

}
