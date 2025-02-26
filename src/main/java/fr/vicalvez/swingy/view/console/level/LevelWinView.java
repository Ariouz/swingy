package fr.vicalvez.swingy.view.console.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.sql.SQLHero;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.console.ConsoleView;

import java.util.Scanner;

public class LevelWinView extends ConsoleView {

    @Override
    public void open(GameController gameController) {
        Hero hero = gameController.getHeroController().getHero();
        System.out.println("Congrats! You win!");

        System.out.println("What do you  want to do?");
        System.out.println("- PLAY to go back to main menu");
        System.out.println("- QUIT to exit the game");
        System.out.println("- SWITCH to switch to GUI view");

        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();

        if (!gameController.getStartController().selectEndAction(action, gameController))
            gameController.openView(ViewType.WIN);

    }
}
