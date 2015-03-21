package ua.kharkiv.knure.dimploma.containers;

import java.util.List;

public class Class extends Object {
	protected Boolean isAbstract;
	protected List<Property> properties;

	public String getIsAbstract() {
		return String.valueOf(isAbstract);
	}

	public void setIsAbstract(String isAbstract) {
		this.isAbstract = Boolean.valueOf(isAbstract);
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Class [isAbstract=" + isAbstract + ", name=" + name
				+ ", properties=" + properties + ", xmiID=" + xmiID
				+ ", xmiType=" + xmiType + "]";
	}
}
