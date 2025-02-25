package fr.vicalvez.swingy.model.artifact;

public enum ArtifactType {

	ARMOR(ArmorArtifact.class, "Armor"),
	HELM(HelmArtifact.class, "HP"),
	WEAPON(WeaponArtifact.class, "Attack");

	private final Class clazz;
	private final String name;

	ArtifactType(Class clazz, String name) {
		this.clazz = clazz;
		this.name = name;
	}

	public Class getClazz() {
		return clazz;
	}

	public String getName() {
		return name;
	}
}
