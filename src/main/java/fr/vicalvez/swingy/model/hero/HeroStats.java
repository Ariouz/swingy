package fr.vicalvez.swingy.model.hero;

import java.util.Arrays;
import java.util.HashMap;

public class HeroStats {

	private final HashMap<HeroAttribute, Integer> attributes = new HashMap<>();
	private HeroType type;

	public HeroStats(HeroType type)
	{
		this.type = type;
		this.loadDefaultAttributes();
	}

	public HeroStats(String heroName)
	{
		this.loadHeroAttributes(heroName);
	}

	public void loadHeroAttributes(String heroName)
	{
		// todo load attributes from file
		loadDefaultAttributes(); // todo temp
	}

	public void loadDefaultAttributes()
	{
		Arrays.stream(HeroAttribute.values()).forEach(attribute -> {
			attributes.put(attribute, type.getDefaultAttribute(attribute));
		});
	}

	public int getAttribute(HeroAttribute attribute)
	{
		return this.attributes.get(attribute);
	}

	public void upgradeAttribute(HeroAttribute attribute, int amount)
	{
		this.attributes.replace(attribute, this.attributes.get(attribute) + amount);
	}

}
