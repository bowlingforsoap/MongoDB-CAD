package ua.kharkiv.knure.dimploma.containers;

public class OwnedEnd extends Property {
	protected boolean navigable;

	public OwnedEnd() {
	}
	
	public OwnedEnd(Property property, boolean navigable) {
		setXmiID(property.getXmiID());
		setXmiType(property.getXmiType());
		setIsID(property.getIsID());
		setName(property.getName());
		setType(property.getType());
		setMultiplicity(property.getMultiplicity());
		setAggregation(property.getAggregation());
		setAssociation(property.getAssociation());
		this.navigable = navigable;
	}

	public void resetOwnedEnd(OwnedEnd oe) {
		setXmiID(oe.getXmiID());
		setXmiType(oe.getXmiType());
		setIsID(oe.getIsID());
		setName(oe.getName());
		setType(oe.getType());
		setMultiplicity(oe.getMultiplicity());
		setAggregation(oe.getAggregation());
		setAssociation(oe.getAssociation());
		setNavigable(oe.getNavigable());
	}

	public boolean getNavigable() {
		return navigable;
	}

	public void setNavigable(boolean navigable) {
		this.navigable = navigable;
	}

	@Override
	public String toString() {
		return "OwnedEnd [navigable=" + navigable + ", isID=" + isID
				+ ", name=" + name + ", type=" + type + ", association="
				+ association + ", aggregation=" + aggregation
				+ ", multiplicity=" + multiplicity + ", xmiID=" + xmiID
				+ ", xmiType=" + xmiType + "]";
	}
}
