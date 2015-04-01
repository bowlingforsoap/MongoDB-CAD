package ua.kharkiv.knure.dimploma.containers;

public class Property extends Element {
	protected Boolean isID;
	protected String name;
	protected String type;
	/**
	 * Indicates that this {@code Property} is a result of an association.
	 */
	protected String association;
	/**
	 * Indicates that this {@code Property} is a result of an aggregation of a
	 * certain type.
	 */
	protected Integer aggregation;
	protected Multiplicity multiplicity;

	public static final int SHARED_AGGREGATION = 1;
	public static final String SHARED_AGGREGATION_STRING = "shared";
	public static final int COMPOSITE_AGGREGATION = 2;
	public static final String COMPOSITE_AGGREGATION_STRING = "composite";

	public String getIsID() {
		return String.valueOf(isID);
	}

	public void setIsID(String isID) {
		this.isID = Boolean.valueOf(isID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		if (type == null || type.length() == 0) {
			type = String.class.getSimpleName();
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

	public String getAggregation() {
		if (aggregation != null) {
			if (aggregation.equals(Property.COMPOSITE_AGGREGATION)) {
				return COMPOSITE_AGGREGATION_STRING;
			} else if (aggregation.equals(Property.SHARED_AGGREGATION)) {
				return SHARED_AGGREGATION_STRING;
			}
		}
		return String.valueOf(0);
	}

	public void setAggregation(String aggregation) {
		if (aggregation.equals(SHARED_AGGREGATION_STRING)) {
			this.aggregation = Property.SHARED_AGGREGATION;
		} else if (aggregation.equals(COMPOSITE_AGGREGATION_STRING)) {
			this.aggregation = Property.COMPOSITE_AGGREGATION;
		} else {
			this.aggregation = 0;
		}
	}

	public String getAssociation() {
		return association;
	}

	public void setAssociation(String association) {
		this.association = association;
	}

	@Override
	public String toString() {
		return "Property [isID=" + isID + ", name=" + name + ", type=" + type
				+ ", association=" + association + ", aggregation="
				+ aggregation + ", multiplicity=" + multiplicity + ", xmiID="
				+ xmiID + ", xmiType=" + xmiType + "]";
	}
}
