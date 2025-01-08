package fr.vicalvez.swingy.view;

import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.console.hero.create.HeroCreateViewConsole;
import fr.vicalvez.swingy.view.gui.hero.HeroCreateViewGUI;
import fr.vicalvez.swingy.view.console.hero.info.HeroInfoViewConsole;
import fr.vicalvez.swingy.view.console.level.LevelViewConsole;
import fr.vicalvez.swingy.view.console.level.villain.MeetVillainViewConsole;
import fr.vicalvez.swingy.view.console.start.StartViewConsole;

public enum ViewType {

	START(StartViewConsole.class, "startView"),
	HERO_CREATE(HeroCreateViewConsole.class, "heroCreateType"),
	HERO_DETAILS(HeroInfoViewConsole.class, ""),
	GAME_LEVEL(LevelViewConsole.class, ""),
	MEET_VILLAIN(MeetVillainViewConsole.class, ""),

	// GUI ONLY
	HERO_NAME(null, "heroCreateName")
	;

	private final Class<? extends ConsoleView> consoleClass;
	private final String guiPanelName;

	ViewType(Class<? extends ConsoleView> consoleClass, String guiPanelName)
	{
		this.consoleClass = consoleClass;
		this.guiPanelName = guiPanelName;
	}

	public Class<? extends ConsoleView> getConsoleClass() {
		return consoleClass;
	}

	public String getGuiPanelName() {
		return guiPanelName;
	}
}
