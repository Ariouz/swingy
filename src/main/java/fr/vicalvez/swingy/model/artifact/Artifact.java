package fr.vicalvez.swingy.model.artifact;

import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class Artifact {

	private final HeroAttribute targetAttribute;
	private final int attributeIncrease;
	private final int artifactLevel;
	private final ArtifactType type;

	protected Artifact(HeroAttribute attribute, int attributeIncrease, int artifactLevel, ArtifactType type)
	{
		this.targetAttribute = attribute;
		this.attributeIncrease = attributeIncrease;
		this.artifactLevel = artifactLevel;
		this.type = type;
	}

	public HeroAttribute getTargetAttribute() {
		return targetAttribute;
	}

	public int getAttributeIncrease() {
		return attributeIncrease * artifactLevel;
	}

	public int getArtifactLevel() {
		return artifactLevel;
	}

	public ArtifactType getType() {
		return type;
	}
}
