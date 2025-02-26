package fr.vicalvez.swingy.view.gui.hero;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.sql.SQLHeroEntry;
import fr.vicalvez.swingy.view.ViewType;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class HeroSelectGUI {

	private JPanel panel;
	private JPanel listPanel;

	public void updateList(GameController gameController)
	{
		this.panel.remove(listPanel);
		this.listPanel = createList(gameController);
		this.panel.add(listPanel);
	}

	public JPanel createHeroesPanel(GameController gameController)
	{
		this.panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 10, 10));
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel topSection = new JPanel(new BorderLayout());
		String title = ("<html><h2>Select a hero</h2></html>");
		JLabel titleLabel = new JLabel(title, JLabel.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		JButton backButton = new JButton("Back");
		backButton.setPreferredSize(new Dimension(70, 20));
		GUIStyle.styleButton(backButton, ButtonColor.GRAY, 14);
		backButton.addActionListener(event -> gameController.getCardLayoutManager().showView(ViewType.START.getGuiPanelName()));
		buttonPanel.add(backButton);

		topSection.add(titleLabel, BorderLayout.CENTER);
		topSection.add(buttonPanel, BorderLayout.EAST);
		panel.add(topSection);

		this.listPanel = new JPanel();
		panel.add(listPanel);
		return panel;
	}

	public JPanel createList(GameController gameController)
	{
		JPanel heroesPanel = new JPanel();
		JTable heroTable;
		DefaultTableModel tableModel;

		List<SQLHeroEntry> heroes = gameController.getSqlManager().getSqlHero().getHeroes();
		Collections.reverse(heroes);

		heroesPanel.setLayout(new BorderLayout());

		String[] columnNames = {"ID", "Name", "Type", "Attack", "Damage", "Helm", "Level", "XP"};
		tableModel = new DefaultTableModel(columnNames, 0);

		for (SQLHeroEntry hero : heroes) {
			Object[] rowData = {
					hero.getId(),
					hero.getName(),
					hero.getType(),
					hero.getAttack(),
					hero.getDefense(),
					hero.getHelm(),
					hero.getLevel(),
					hero.getExperience()
			};
			tableModel.addRow(rowData);
		}

		heroTable = new JTable(tableModel);
		heroTable.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(heroTable);
		heroesPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

		for (SQLHeroEntry hero : heroes) {
			JButton selectButton = new JButton("Select " + hero.getName() + " (" + hero.getId() + ")");
			selectButton.addActionListener(e -> {
				System.out.println("Selected hero: " + hero.getId());
				gameController.getHeroController().loadHero(hero.getId());
				gameController.openView(ViewType.GAME_LEVEL);
			});
			GUIStyle.styleButton(selectButton,  ButtonColor.GREEN, 12);
			selectButton.setPreferredSize(new Dimension(150, 12));
			buttonsPanel.add(selectButton);
		}

		JScrollPane buttonScrollPane = new JScrollPane(buttonsPanel);
		buttonScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		heroesPanel.add(buttonScrollPane, BorderLayout.EAST);
		return heroesPanel;
	}

}
