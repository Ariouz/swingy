package fr.vicalvez.swingy.model.artifact;

import fr.vicalvez.swingy.model.hero.HeroAttribute;

public class WeaponArtifact extends Artifact{

	public WeaponArtifact(int artifactLevel) {
		super(HeroAttribute.ATTACK, 1, artifactLevel, ArtifactType.WEAPON);
	}

}
