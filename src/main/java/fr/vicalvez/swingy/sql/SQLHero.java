package fr.vicalvez.swingy.sql;

import fr.vicalvez.swingy.model.hero.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLHero {

    private final String HERO_TABLE = "swingy_hero";
    private final String STATS_TABLE = "swingy_hero_stats";
    private final String LEVEL_TABLE = "swingy_hero_level";

    private final SQLManager sqlManager;

    public SQLHero(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    public void createTables()
    {
        String query = "CREATE TABLE IF NOT EXISTS " + HERO_TABLE + "(hero_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), type VARCHAR(255))";
        String statsQuery = "CREATE TABLE IF NOT EXISTS " + STATS_TABLE + "(hero_id INTEGER PRIMARY KEY, attack INTEGER, defense INTEGER, helm INTEGER)";
        String levelQuery = "CREATE TABLE IF NOT EXISTS " + LEVEL_TABLE + "(hero_id INTEGER PRIMARY KEY, level INTEGER, experience INTEGER)";

        sqlManager.update(query);
        sqlManager.update(statsQuery);
        sqlManager.update(levelQuery);
    }

    public boolean exists(Hero hero) {
        String query = "SELECT * FROM " + HERO_TABLE + " WHERE hero_id = %s";
        return (boolean) sqlManager.query(String.format(query, hero.getId()), resultSet -> {
            try {
                if (resultSet.next())
                    return true;
                return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void insert(Hero hero) {
        String query = "INSERT INTO " + HERO_TABLE + " (name, type) VALUES ('%s', '%s')";
        ResultSet rs = sqlManager.updateAndGet(String.format(query, hero.getName(), hero.getType()));
        try {
            if (rs.next())
            {

                int heroId = rs.getInt(1);
                System.out.println("Hero id " + heroId);

                HeroStats heroStats = hero.getStats();
                query = "INSERT INTO " + STATS_TABLE + " (hero_id, attack, defense, helm) VALUES (%s, %s, %s, %s)";
                sqlManager.update(String.format(query, heroId,
                        heroStats.getAttribute(HeroAttribute.ATTACK),
                        heroStats.getAttribute(HeroAttribute.DEFENSE),
                        heroStats.getAttribute(HeroAttribute.HIT_POINTS)));

                HeroLevel heroLevel = hero.getLevel();
                query = "INSERT INTO " + LEVEL_TABLE + " (hero_id, level, experience) VALUES (%s, %s, %s)";
                sqlManager.update(String.format(query, heroId, heroLevel.getLevel(), heroLevel.getExperience()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateHero(Hero hero)
    {
        HeroStats heroStats = hero.getStats();
        HeroLevel heroLevel = hero.getLevel();

        String query = "UPDATE " + STATS_TABLE + " SET attack = '%s', defense = '%s', helm = '%s' WHERE hero_id = '%s'";
        sqlManager.update(String.format(query,
                heroStats.getAttribute(HeroAttribute.ATTACK),
                heroStats.getAttribute(HeroAttribute.DEFENSE),
                heroStats.getAttribute(HeroAttribute.HIT_POINTS),
                hero.getId()));

        query = "UPDATE " + LEVEL_TABLE + " SET level = '%s', experience = '%s' WHERE hero_id = '%s'";
        sqlManager.update(String.format(query, heroLevel.getLevel(), heroLevel.getExperience(), hero.getId()));
    }

    public List<SQLHeroEntry> getHeroes()
    {
        List<SQLHeroEntry> entries = new ArrayList<>();
        String query = "SELECT * FROM %s AS hero INNER JOIN %s AS stats ON hero.hero_id=stats.hero_id INNER JOIN %s AS level ON hero.hero_id=level.hero_id";
        sqlManager.query(String.format(query, HERO_TABLE, STATS_TABLE, LEVEL_TABLE),  resultSet -> {
            try {
                while (resultSet.next())
                {
                    SQLHeroEntry entry = new SQLHeroEntry(
                            resultSet.getInt("hero_id"),
                            resultSet.getString("name"),
                            HeroType.getByName(resultSet.getString("type")),
                            resultSet.getInt("attack"),
                            resultSet.getInt("defense"),
                            resultSet.getInt("helm"),
                            resultSet.getInt("level"),
                            resultSet.getInt("experience")
                    );
                    entries.add(entry);
                }

            } catch (SQLException e) { throw new RuntimeException(e); }
        });
        return entries;
    }

    public SQLHeroEntry getHero(int heroId)
    {
        String query = "SELECT * FROM %s AS hero INNER JOIN %s AS stats ON hero.hero_id=stats.hero_id INNER JOIN %s AS level ON hero.hero_id=level.hero_id WHERE hero.hero_id=%s";
        return (SQLHeroEntry) sqlManager.query(String.format(query, HERO_TABLE, STATS_TABLE, LEVEL_TABLE, heroId),  resultSet -> {
            try {
                if (resultSet.next())
                {
                    return new SQLHeroEntry(
                            resultSet.getInt("hero_id"),
                            resultSet.getString("name"),
                            HeroType.getByName(resultSet.getString("type")),
                            resultSet.getInt("attack"),
                            resultSet.getInt("defense"),
                            resultSet.getInt("helm"),
                            resultSet.getInt("level"),
                            resultSet.getInt("experience"));
                }
                return null;
            } catch (SQLException e) { throw new RuntimeException(e); }
        });
    }

}
