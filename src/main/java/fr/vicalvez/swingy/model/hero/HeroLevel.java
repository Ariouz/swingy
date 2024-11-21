package fr.vicalvez.swingy.model.hero;

public class HeroLevel {

	private int level;
	private double experience;

	public HeroLevel()
	{
		this.level = 1;
		this.experience = 0;
	}

	public HeroLevel(String name)
	{
		this.level = 0;
		this.experience = 0;
		// todo load from file
	}

	public int getLevel() {
		return level;
	}

	public double getExperience() {
		return experience;
	}

	public double getLevelExperience() {
		return level * 1000 + Math.pow(level - 1, 2) * 450;
	}

	public boolean addExperience(double amount)
	{
		this.experience += amount;
		return this.levelUp();
	}

	private boolean levelUp()
	{
		if (this.experience >= getLevelExperience())
		{
			this.experience -= getLevelExperience();
			this.level++;
			return true;
		}
		return false;
	}

}
