package fr.vicalvez.swingy.view;

import fr.vicalvez.swingy.view.console.ConsoleView;
import fr.vicalvez.swingy.view.console.hero.create.HeroCreateViewConsole;
import fr.vicalvez.swingy.view.console.hero.create.HeroNameViewConsole;
import fr.vicalvez.swingy.view.console.hero.death.DeathViewConsole;
import fr.vicalvez.swingy.view.console.hero.info.HeroInfoViewConsole;
import fr.vicalvez.swingy.view.console.hero.select.ConsoleHeroSelectView;
import fr.vicalvez.swingy.view.console.level.LevelViewConsole;
import fr.vicalvez.swingy.view.console.level.LevelWinView;
import fr.vicalvez.swingy.view.console.level.villain.MeetVillainViewConsole;
import fr.vicalvez.swingy.view.console.start.StartViewConsole;

public enum ViewType {

	START(StartViewConsole.class, "startView"),
	HERO_CREATE(HeroCreateViewConsole.class, "heroCreateType"),
	HERO_DETAILS(HeroInfoViewConsole.class, ""),
	GAME_LEVEL(LevelViewConsole.class, "levelView"),
	MEET_VILLAIN(MeetVillainViewConsole.class, "meetVillain"),
	WIN(LevelWinView.class, "win"),
	HERO_SELECT(ConsoleHeroSelectView.class, "heroSelect"),
	HERO_NAME(HeroNameViewConsole.class, "heroCreateName"),
	DEATH(DeathViewConsole.class, "death"),

	// GUI ONLY,
	FIGHT_VILLAIN(null, "fightVillain"),
	EXIT_GAME(null, "")
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
