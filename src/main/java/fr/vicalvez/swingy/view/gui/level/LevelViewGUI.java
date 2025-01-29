package fr.vicalvez.swingy.view.gui.level;

import fr.vicalvez.swingy.controller.GameController;
import fr.vicalvez.swingy.controller.MapController;
import fr.vicalvez.swingy.model.Location;
import fr.vicalvez.swingy.model.game.Direction;
import fr.vicalvez.swingy.model.game.Map;
import fr.vicalvez.swingy.model.hero.Hero;
import fr.vicalvez.swingy.model.hero.HeroAttribute;
import fr.vicalvez.swingy.view.gui.style.ButtonColor;
import fr.vicalvez.swingy.view.gui.style.GUIStyle;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class LevelViewGUI {

	private JPanel levelPanel;
	private JPanel heroInfoPanel;
	private StyledDocument mapStyledDoc;

	public JPanel createLevelView(GameController gameController)
	{
		Hero hero = gameController.getHeroController().getHero();
		MapController mapController = gameController.getLevelController().getMapController();

		JPanel panel = new JPanel(new BorderLayout());

		JPanel heroInfo = createHeroInfoPanel(hero);
		heroInfo.setPreferredSize(new Dimension(150, panel.getHeight()));

		heroInfoPanel = heroInfo;
		panel.add(heroInfo, BorderLayout.WEST);

		JPanel mapDisplay = createMapDisplayPanel(mapController, hero);
		mapDisplay.setPreferredSize(new Dimension(450, panel.getHeight()));
		panel.add(mapDisplay, BorderLayout.EAST);



		levelPanel = panel;
		return panel;
	}

	public JPanel createHeroInfoPanel(Hero hero)
	{
		JPanel parent = new JPanel(new FlowLayout());
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));
		parent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 5, 5));
		panel.setPreferredSize(new Dimension(200, 150));

		if (hero == null || hero.getType() == null) return panel;

		JLabel type = new JLabel("Type:");
		JLabel heroType = new JLabel(hero.getType().toString());

		JLabel name = new JLabel("Name:");
		JLabel heroName = new JLabel(hero.getName());

		JLabel attack = new JLabel("Attack:");
		JLabel heroAttack = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.ATTACK)));

		JLabel defense = new JLabel("Defense:");
		JLabel heroDefense = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.DEFENSE)));

		JLabel hp = new JLabel("Hp:");
		JLabel heroHp = new JLabel(String.valueOf(hero.getStats().getAttribute(HeroAttribute.HIT_POINTS)));

		JLabel level = new JLabel("Level:");
		JLabel heroLevel = new JLabel(hero.getLevel().getLevel() + " (" + hero.getLevel().getExperience() + "/" + hero.getLevel().getLevelExperience() + ")");

		panel.add(type);
		panel.add(heroType);
		panel.add(name);
		panel.add(heroName);
		panel.add(attack);
		panel.add(heroAttack);
		panel.add(defense);
		panel.add(heroDefense);
		panel.add(hp);
		panel.add(heroHp);
		panel.add(level);
		panel.add(heroLevel);

		parent.add(panel);

		JButton exit = new JButton("Save and exit");
		exit.setPreferredSize(new Dimension(150, 30));
		GUIStyle.styleButton(exit, ButtonColor.GRAY, 14);
		parent.add(exit);
		return parent;
	}

	public void updateHeroInfoPanel(Hero hero)
	{
		levelPanel.remove(heroInfoPanel);
		JPanel heroInfo = createHeroInfoPanel(hero);
		levelPanel.add(heroInfo);
		heroInfoPanel = heroInfo;
	}

	public JPanel createMapDisplayPanel(MapController mapController, Hero hero)
	{
		JPanel panel = new JPanel();

		JTextPane mapText = new JTextPane();
		mapText.setEditable(false);
		mapText.setHighlighter(null);
		mapText.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mapText.getCaret().setVisible(false);

		StyledDocument styledDocument = mapText.getStyledDocument();
		mapStyledDoc = styledDocument;
		updateMapToTextPane(mapController.getMap(), hero);

		JScrollPane scrollPane = new JScrollPane(mapText);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		panel.add(scrollPane);

		addMapMovementButtons(panel, mapController);

		return panel;
	}

	private void addMapMovementButtons(JPanel container, MapController mapController)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));

		JButton upButton = new JButton("Up");
		JButton leftButton = new JButton("Left");
		JButton rightButton = new JButton("Right");
		JButton downButton = new JButton("Down");

		GUIStyle.styleButton(upButton, ButtonColor.BLUE, 14);
		GUIStyle.styleButton(leftButton, ButtonColor.BLUE, 14);
		GUIStyle.styleButton(rightButton, ButtonColor.BLUE, 14);
		GUIStyle.styleButton(downButton, ButtonColor.BLUE, 14);

		setMovementButtonAction(mapController, upButton, Direction.UP);
		setMovementButtonAction(mapController, leftButton, Direction.LEFT);
		setMovementButtonAction(mapController, rightButton, Direction.RIGHT);
		setMovementButtonAction(mapController, downButton, Direction.DOWN);
		panel.add(new JLabel());
		panel.add(upButton);
		panel.add(new JLabel());

		panel.add(leftButton);
		panel.add(new JLabel());
		panel.add(rightButton);

		panel.add(new JLabel());
		panel.add(downButton);
		panel.add(new JLabel());

		container.add(panel);
	}

	public void setMovementButtonAction(MapController map, JButton button, Direction direction)
	{
		button.addActionListener(event -> {
			map.goTo(direction.toString());
		});
	}

	public void updateMapToTextPane(Map map, Hero hero) {
		if (hero == null || hero.getType() == null) return ;

		System.out.println(hero.getLocation().getX() + " " + hero.getLocation().getY());
		try {
			mapStyledDoc.remove(0, mapStyledDoc.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		int mapSize = map.getMapSize();
		for (int y = 0; y < mapSize; y++)
		{
			for (int x = 0; x < mapSize; x++)
			{
				if (hero.getLocation().equals(x, y))
					addStyledText(mapStyledDoc, "H", new Color(20, 168, 11), false, 14);
				else
				{
					if (map.isVillainAt(new Location(x, y)))
						addStyledText(mapStyledDoc, "X", Color.RED, false, 14);
					else
						addStyledText(mapStyledDoc, "_", Color.BLACK, false, 14);
				}
			}
			addStyledText(mapStyledDoc, "\n", Color.BLACK, false, 14);
		}
	}

	public static void addStyledText(StyledDocument doc, String text, Color color, boolean bold, int fontSize) {
		Style style = doc.addStyle("Style", null);
		StyleConstants.setForeground(style, color);
		StyleConstants.setBold(style, bold);
		StyleConstants.setFontSize(style, fontSize);

		try {
			doc.insertString(doc.getLength(), text, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
