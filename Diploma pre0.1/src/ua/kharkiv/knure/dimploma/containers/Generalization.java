package ua.kharkiv.knure.dimploma.containers;

import ua.kharkiv.knure.dimploma.parser.NamesContainer;

public class Generalization extends Abstraction {
	protected String general;
	protected String owner;

	{
		xmiType = NamesContainer.GENERALIZATION_TAG_GENERALIZATION;
	}
	
	public String getGeneral() {
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Generalization [general=" + general + ", owner=" + owner
				+ ", xmiID=" + xmiID + ", xmiType=" + xmiType + "]";
	}
}
