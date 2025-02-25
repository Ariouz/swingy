package fr.vicalvez.swingy.model.artifact;

import java.util.Random;

public class ArtifactFactory {

	private static ArtifactFactory instance;

	private ArtifactFactory() {}

	public static ArtifactFactory getInstance() {
		if (instance == null) { instance = new ArtifactFactory(); }
		return instance;
	}

	public Artifact createArtifact(int heroLevel) {
		ArtifactType type = ArtifactType.values()[new Random().nextInt(ArtifactType.values().length)];
		try {
			return (Artifact) type.getClazz().getConstructor(int.class).newInstance(heroLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
