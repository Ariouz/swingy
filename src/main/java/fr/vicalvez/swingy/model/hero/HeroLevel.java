package fr.vicalvez.swingy.model.hero;

public class HeroLevel {

	private int level;
	private double experience;

	public HeroLevel()
	{
		this.level = 1;
		this.experience = 0;
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

	public void setExperience(double experience) {
		this.experience = experience;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
