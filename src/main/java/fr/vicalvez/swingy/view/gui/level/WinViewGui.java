package fr.vicalvez.swingy.view.gui.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.RunMode;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WinViewGui {

	public JPanel createWinPanel(GameController gameController)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		String title = ("<html><h2>YOU WIN</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);
		titleLabel.setForeground(Color.GREEN);
		panel.add(titleLabel);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(1, 2, 10, 10));
		JButton mainMenu = new JButton("Back to main menu");
		JButton rageQuit = new JButton("Quit game");

		GUIStyle.styleButton(mainMenu, ButtonColor.BLUE, 15);
		GUIStyle.styleButton(rageQuit, ButtonColor.RED, 15);

		buttonsPanel.add(mainMenu);
		buttonsPanel.add(rageQuit);

		setButtonClickActionValue(mainMenu, ViewType.START, gameController);
		setButtonClickActionValue(rageQuit, ViewType.EXIT_GAME, gameController);

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
