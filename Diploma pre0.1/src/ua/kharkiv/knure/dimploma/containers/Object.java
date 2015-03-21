package ua.kharkiv.knure.dimploma.containers;

public abstract class Object extends Element {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
