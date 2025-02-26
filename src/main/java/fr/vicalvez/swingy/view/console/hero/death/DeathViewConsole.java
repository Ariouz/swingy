package fr.vicalvez.swingy.view.console.hero.death;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Scanner;

public class DeathViewConsole extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		Hero hero = gameController.getHeroController().getHero();
		System.out.println("You Died");

		System.out.println("What do you  want to do?");
		System.out.println("- PLAY to go back to main menu");
		System.out.println("- QUIT to rage quit");
		System.out.println("- SWITCH to switch to GUI view");

		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();

		if (!gameController.getStartController().selectEndAction(action, gameController))
			gameController.openView(ViewType.WIN);

	}

}
