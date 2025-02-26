package fr.vicalvez.swingy.controller;

import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.artifact.Artifact;
import fr.vicalvez.swingy.model.artifact.ArtifactFactory;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.validators.ValidationUtil;
import fr.vicalvez.swingy.validators.wrapper.CombatActionWrapper;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.level.VillainFightViewGui;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CombatController {

	private final GameController gameController;

	public CombatController(GameController gameController){
		this.gameController = gameController;
	}

	public boolean simulateCombat(JTextArea combatLogArea, RunMode runMode) throws InterruptedException {
		Hero hero = gameController.getHeroController().getHero();
		Location heroLoc = hero.getLocation();
		Villain villain = gameController.getLevelController().getMapController().getMap().getVillainAt(heroLoc);

		printCombatLog(combatLogArea, runMode, "Combat is processing");

		int heroHP = hero.getStats().getAttribute(HeroAttribute.HIT_POINTS);
		int villainHP = villain.getHitPoints();

		Random random = new Random();
		double chanceForHeroToHit = 0.1;

		int heroAttack = hero.getStats().getAttribute(HeroAttribute.ATTACK);
		int heroDefense = hero.getStats().getAttribute(HeroAttribute.DEFENSE);

		int villainAttack = villain.getAttack();
		int villainDefense = villain.getDefense();

		while (heroHP > 0 && villainHP > 0) {
			int heroDamage = Math.max(heroAttack - (int) (0.6 * villainDefense) - 1, 1);

			if (random.nextDouble() < chanceForHeroToHit) {
				heroDamage += random.nextInt(5);
			}

			villainHP -= heroDamage;
			printCombatLog(combatLogArea, runMode, "Le héros attaque, infligeant " + heroDamage + " dégâts. " + Math.max(0, villainHP) + " HP restants pour le vilain.");

			if (villainHP <= 0) {
				printCombatLog(combatLogArea, runMode, "Le vilain est vaincu !");
				double xp = calculateVillainXP(villain);
				printCombatLog(combatLogArea, runMode, "Le hero gagne " + xp + " xp");
				if (hero.getLevel().addExperience(xp)){
					printCombatLog(combatLogArea, runMode, "Le hero monte au niveau " + hero.getLevel().getLevel() + " !");
					gameController.getLevelController().nextLevel(gameController.getHeroController().getHero());
				}

				int rand = new Random().nextInt(2);
				if (rand == 0) {
					Artifact artifact = ArtifactFactory.getInstance().createArtifact((int) xp / 20);
					hero.getStats().upgradeAttribute(artifact.getTargetAttribute(), artifact.getAttributeIncrease());
					printCombatLog(combatLogArea, runMode, "Le villain a drop un artifact " + artifact.getType().getName() + ", le hero gagne " + artifact.getAttributeIncrease() + " " + artifact.getType().getName());
				}
				return true;
			}

			int villainDamage = Math.max(villainAttack - (int) (0.6 * heroDefense) - 1, 1);

			heroHP -= villainDamage;
			printCombatLog(combatLogArea, runMode, "Le vilain attaque, infligeant " + villainDamage + " dégâts. " + Math.max(0, heroHP) + " HP restants pour le héros.");

			if (heroHP <= 0) {
				printCombatLog(combatLogArea, runMode, "Le héros est vaincu !");
				return false;
			}
		}
		return false;
	}

	public double calculateVillainXP(Villain villain) {
		int villainAttack = villain.getAttack();
		int villainDefense = villain.getDefense();
		int villainHP = villain.getHitPoints();

		return (double) Math.round(((villainAttack * 1.2) * (villainDefense * 0.8) + (villainHP * 5)) * 100) / 100;
	}

	public void printCombatLog(JTextArea area, RunMode runMode, String text) throws InterruptedException {
		if (runMode == RunMode.CONSOLE) System.out.println(text);
		else {
			area.append("\n"+text);
			SwingUtilities.invokeLater(() -> {
				area.setCaretPosition(area.getDocument().getLength());
				area.repaint();
				area.revalidate();
			});
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}

	public boolean isValidFightAction(String action)
	{
		CombatActionWrapper wrapper = new CombatActionWrapper(action);
		if (ValidationUtil.isInvalid(wrapper))
		{
			if (action.equals("SWITCH"))
				gameController.setMode(gameController.getMode() == RunMode.GUI ? RunMode.CONSOLE : RunMode.GUI);
			else {
				ValidationUtil.printValidationError(wrapper, null, gameController.getMode());
			}
			return false;
		}
		return true;
	}

	public void processAction(CombatAction combatAction)
	{
		if (combatAction == CombatAction.FIGHT){
			this.startFight(false);
			return;
		}

		int rand = new Random().nextInt(2);
		if (rand == 0){
			this.startFight(true);
		} else {
			Hero hero = gameController.getHeroController().getHero();
			gameController.getLevelController().getMapController().go(hero.getLastMove(), true);
			gameController.openView(ViewType.GAME_LEVEL);
		}
	}

	public void startFight(boolean fellOnARock)
	{
		gameController.openView(ViewType.FIGHT_VILLAIN);
		VillainFightViewGui fightViewGui = gameController.getCardLayoutManager().getVillainFightViewGui();
		fightViewGui.updateView(gameController);
		JTextArea combatLogArea = fightViewGui.getCombatLog();

		try {
			if (fellOnARock)
				printCombatLog(combatLogArea, gameController.getMode(), "You fell on a rock while running, you got to fight!");
		} catch (InterruptedException e) { e.printStackTrace(); }

		Villain villain = gameController
				.getLevelController().
				getMapController().
				getMap()
				.getVillainAt(gameController.getHeroController().getHero().getLocation());


		startCombatSwingWorker(combatLogArea, villain);
	}

	private void startCombatSwingWorker(JTextArea combatLogArea, Villain villain) {
		SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				TimeUnit.MILLISECONDS.sleep(500);
				return simulateCombat(combatLogArea, gameController.getMode());
			}

			@Override
			protected void done()
			{
				try {
					boolean won = get();
					TimeUnit.SECONDS.sleep(3);
					if (won) {
						gameController.getLevelController().getMapController().getMap().getVillains().remove(villain.getLocation());
						gameController.openView(ViewType.GAME_LEVEL);
					}
					else {
						gameController.getLevelController().getMapController().getMap().getVillains().clear();
						if (gameController.getMode() == RunMode.CONSOLE) gameController.openView(ViewType.START);
						else gameController.openView(ViewType.DEATH);
					}
				} catch (Exception e) { e.printStackTrace(); }
			}
		};

		worker.execute();
	}
}
