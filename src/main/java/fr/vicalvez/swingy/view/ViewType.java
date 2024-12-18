package fr.vicalvez.swingy.view;

import fr.vicalvez.swingy.view.hero.create.HeroCreateViewConsole;
import fr.vicalvez.swingy.view.hero.info.HeroInfoViewConsole;
import fr.vicalvez.swingy.view.level.LevelViewConsole;
import fr.vicalvez.swingy.view.level.villain.MeetVillainViewConsole;
import fr.vicalvez.swingy.view.start.StartViewConsole;

public enum ViewType {

	START(StartViewConsole.class, null),
	HERO_CREATE(HeroCreateViewConsole.class, null),
	HERO_DETAILS(HeroInfoViewConsole.class, null),
	GAME_LEVEL(LevelViewConsole.class, null),
	MEET_VILLAIN(MeetVillainViewConsole.class, null),
	;

	private final Class<? extends ConsoleView> consoleClass;
	private final Class<? extends GUIView> guiClass;

	ViewType(Class<? extends ConsoleView> consoleClass, Class<? extends GUIView> guiClass)
	{
		this.consoleClass = consoleClass;
		this.guiClass = guiClass;
	}

	public Class<? extends ConsoleView> getConsoleClass() {
		return consoleClass;
	}

	public Class<? extends GUIView> getGuiClass() {
		return guiClass;
	}
}
