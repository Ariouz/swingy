package fr.vicalvez.swingy.model.artifact;

import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class Artifact {

	private final HeroAttribute targetAttribute;
	private final double attributeIncrease;
	private final int artifactLevel;

	protected Artifact(HeroAttribute attribute, double attributeIncrease, int artifactLevel)
	{
		this.targetAttribute = attribute;
		this.attributeIncrease = attributeIncrease;
		this.artifactLevel = artifactLevel;
	}

	public HeroAttribute getTargetAttribute() {
		return targetAttribute;
	}

	public double getAttributeIncrease() {
		return attributeIncrease;
	}

	public int getArtifactLevel() {
		return artifactLevel;
	}
}
