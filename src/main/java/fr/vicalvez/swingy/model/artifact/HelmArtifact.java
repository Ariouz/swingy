package fr.vicalvez.swingy.model.artifact;

import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class HelmArtifact extends Artifact {

	public HelmArtifact(int artifactLevel) {
		super(HeroAttribute.HIT_POINTS, 2, artifactLevel);
	}

}
