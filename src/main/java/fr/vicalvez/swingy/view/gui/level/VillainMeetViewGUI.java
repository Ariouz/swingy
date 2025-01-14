package fr.vicalvez.swingy.view.gui.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.MapController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import java.awt.*;

public class VillainMeetViewGUI {

	private JPanel panel;
	private JPanel infoPanel;

	public JPanel createVillainMeetView(GameController gameController)
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 1, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		String title = ("<html><h2>You met a villain</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);
		panel.add(titleLabel);

		infoPanel = new JPanel();
		panel.add(infoPanel);

		return panel;
	}

	public void updateView(GameController gameController)
	{
		panel.remove(infoPanel);

		Hero hero = gameController.getHeroController().getHero();
		Villain villain = gameController.getLevelController().getMapController().getMap().getVillainAt(hero.getLocation());

		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(1, 3, 20, 5));
		infoPanel.setPreferredSize(new Dimension(600, 300));

		JPanel heroPanel = createHeroInfoPanel(hero);
		heroPanel.setPreferredSize(new Dimension(120, 300));

		JPanel villainPanel = createVillainPanel(villain);

		JPanel buttonsPanel = new JPanel();
		JButton fightButton = new JButton("FIGHT");
		JButton runButton = new JButton("RUN");

		GUIStyle.styleButton(fightButton, ButtonColor.GREEN, 14);
		GUIStyle.styleButton(runButton, ButtonColor.GRAY, 14);

		buttonsPanel.add(fightButton);
		buttonsPanel.add(runButton);

		setVillainMeetButtonAction(fightButton, CombatAction.FIGHT, gameController);
		setVillainMeetButtonAction(runButton, CombatAction.RUN, gameController);

		infoPanel.add(heroPanel);
		infoPanel.add(buttonsPanel);
		infoPanel.add(villainPanel);

		this.panel.add(infoPanel);
	}

	public void setVillainMeetButtonAction(JButton button, CombatAction action, GameController gameController)
	{
		button.addActionListener(e -> {
			gameController.getCombatController().processAction(action);
		});
	}

	public JPanel createHeroInfoPanel(Hero hero)
	{
		JPanel parent = new JPanel(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 5, 5));
		panel.setPreferredSize(new Dimension(150, 150));

		if (hero == null || hero.getType() == null) return panel;

		JLabel type = new JLabel(hero.getName() +" :");
		JLabel heroType = new JLabel(hero.getType().toString());

		JLabel attack = new JLabel("Attack:");
		JLabel heroAttack = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.ATTACK)));

		JLabel defense = new JLabel("Defense:");
		JLabel heroDefense = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.DEFENSE)));

		JLabel hp = new JLabel("Hp:");
		JLabel heroHp = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.HIT_POINTS)));

		panel.add(type);
		panel.add(heroType);
		panel.add(attack);
		panel.add(heroAttack);
		panel.add(defense);
		panel.add(heroDefense);
		panel.add(hp);
		panel.add(heroHp);

		parent.add(panel);
		return parent;
	}

	private JPanel createVillainPanel(Villain villain)
	{
		JPanel parent = new JPanel(new FlowLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 10, 5));
		panel.setPreferredSize(new Dimension(150, 100));

		if (villain == null) return panel;

		JLabel type = new JLabel("Type:");
		JLabel typeVillain = new JLabel("Villain");

		JLabel attack = new JLabel("Attack:");
		JLabel heroAttack = new JLabel(String.valueOf(villain.getAttack()));

		JLabel defense = new JLabel("Defense:");
		JLabel heroDefense = new JLabel(String.valueOf(villain.getDefense()));

		JLabel hp = new JLabel("Hp:");
		JLabel heroHp = new JLabel(String.valueOf(villain.getHitPoints()));


		panel.add(type);
		panel.add(typeVillain);
		panel.add(attack);
		panel.add(heroAttack);
		panel.add(defense);
		panel.add(heroDefense);
		panel.add(hp);
		panel.add(heroHp);

		parent.add(panel);
		return parent;
	}

}
