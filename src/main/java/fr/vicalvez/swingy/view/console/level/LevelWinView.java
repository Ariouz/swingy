package fr.vicalvez.swingy.view.console.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.sql.SQLHero;
import fr.vicalvez.swingy.view.console.ConsoleView;

public class LevelWinView extends ConsoleView {

    @Override
    public void open(GameController gameController) {
        Hero hero = gameController.getHeroController().getHero();
        System.out.println("Congrats! You win!");

        SQLHero sqlHero = gameController.getSqlManager().getSqlHero();;
        if (sqlHero.exists(hero)) {
            sqlHero.updateHero(hero);
            System.out.println(hero.getName() + " stats have been updated!");
        } else {
            sqlHero.insert(hero);
            System.out.println(hero.getName() + " stats have been saved!");
        }

        System.out.println("What do you  want to do?");
        System.out.println("- MENU to go back to main menu");
        System.out.println("- EXIT to exit the game");

        // TODO action read & validation
    }
}
