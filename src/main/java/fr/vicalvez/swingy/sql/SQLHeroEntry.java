package fr.vicalvez.swingy.sql;

import fr.vicalvez.swingy.model.hero.HeroType;

public class SQLHeroEntry {

    private final int id;
    private final String name;
    private final HeroType type;
    private final int attack;
    private final int defense;
    private final int helm;
    private final int level;
    private final int experience;

    public SQLHeroEntry(int id, String name, HeroType type, int attack, int defense, int helm, int level, int experience) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.helm = helm;
        this.level = level;
        this.experience = experience;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroType getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHelm() {
        return helm;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }
}
