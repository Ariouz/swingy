package fr.vicalvez.swingy.view.gui.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.villains.CombatAction;
import fr.vicalvez.swingy.model.villains.Villain;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

public class VillainFightViewGui {

	private JPanel panel;
	private JPanel infoPanel;
	private JTextArea combatLog;

	public JPanel createVillainFightView(GameController gameController)
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1, 1, 1));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		String title = ("<html><h2>Fight !</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);
		panel.add(titleLabel);

		infoPanel = new JPanel();
		panel.add(infoPanel);

		return panel;
	}

	public void updateView(GameController gameController)
	{
		panel.remove(infoPanel);

		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setPreferredSize(new Dimension(600, 500));

		JTextArea combatLog = new JTextArea();
		combatLog.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		combatLog.setEditable(false);
		combatLog.setAutoscrolls(true);
		combatLog.setLineWrap(true);
		combatLog.setWrapStyleWord(true);

		JScrollPane jScrollPane = new JScrollPane(combatLog);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setPreferredSize(new Dimension(400, 300));
		jScrollPane.setBorder(BorderFactory.createTitledBorder("Combat Log"));
		jScrollPane.setViewportView(combatLog);

		DefaultCaret defaultCaret = (DefaultCaret) combatLog.getCaret();
		defaultCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		this.combatLog = combatLog;

		this.infoPanel.add(jScrollPane);
		this.panel.add(infoPanel);

		combatLog.revalidate();
		combatLog.repaint();
		jScrollPane.revalidate();
		jScrollPane.repaint();
	}

	public JTextArea getCombatLog() {
		return combatLog;
	}
}
