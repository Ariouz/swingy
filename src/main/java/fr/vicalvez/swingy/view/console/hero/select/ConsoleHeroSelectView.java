package fr.vicalvez.swingy.view.console.hero.select;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.sql.SQLHeroEntry;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.console.ConsoleView;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleHeroSelectView extends ConsoleView {

	@Override
	public void open(GameController gameController) {
		List<SQLHeroEntry> heroes = gameController.getSqlManager().getSqlHero().getHeroes();
		Collections.reverse(heroes);

		System.out.println("ID - " + "Name - " + "Type - " + "Attack - " + "Damage - " + "Helm - " + "Level - " + "XP");
		for (SQLHeroEntry hero : heroes) {
			System.out.println( hero.getId() +" " +
					hero.getName() +" " +
					hero.getType() +" " +
					hero.getAttack() +"atk " +
					hero.getDefense() +"def " +
					hero.getHelm() +"hp " +
					hero.getLevel() +"lvl " +
					hero.getExperience() + "xp");
		}

		System.out.println("\nEnter hero ID to select");
		Scanner sc = new Scanner(System.in);
		String action = sc.nextLine();
		try {
			int id = Integer.parseInt(action);
			if (gameController.getSqlManager().getSqlHero().getHero(id) == null) {
				System.out.println("Invalid ID");
				gameController.openView(ViewType.HERO_SELECT);
				return ;
			}
			gameController.getHeroController().loadHero(id);
			gameController.openView(ViewType.GAME_LEVEL);
		} catch (NumberFormatException e) {
			System.out.println("Invalid ID");
			gameController.openView(ViewType.HERO_SELECT);
		}

	}

}
