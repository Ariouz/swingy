package fr.vicalvez.swingy.view.gui.start;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartViewGUI {

	public JPanel createStartPanel(GameController gameController)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));


		String title = ("<html><h2>Welcome to Swingy RPG</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);
		panel.add(titleLabel);


		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2, 10, 10));
		JButton createButton = new JButton("Create a hero");
		JButton selectButton = new JButton("Select a hero");

		GUIStyle.styleButton(createButton, ButtonColor.BLUE, 15);
		GUIStyle.styleButton(selectButton, ButtonColor.BLUE, 15);

		buttonsPanel.add(createButton);
		buttonsPanel.add(selectButton);

		setButtonClickActionValue(createButton, ViewType.HERO_CREATE, gameController);
		setButtonClickActionValue(selectButton, ViewType.HERO_SELECT, gameController);

		panel.add(buttonsPanel);

		return panel;
	}

	public void setButtonClickActionValue(JButton button, ViewType viewType, GameController gameController)
	{
		button.addActionListener(e -> {
			gameController.openView(viewType);
		});
	}

}
