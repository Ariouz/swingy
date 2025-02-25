package fr.vicalvez.swingy.model.artifact;

import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class ArmorArtifact extends Artifact {

	public ArmorArtifact(int artifactLevel) {
		super(HeroAttribute.DEFENSE, 1, artifactLevel, ArtifactType.ARMOR);
	}

}
