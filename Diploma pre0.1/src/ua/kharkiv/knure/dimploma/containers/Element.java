package ua.kharkiv.knure.dimploma.containers;

public abstract class Element {
	protected String xmiID;
	protected String xmiType;
	
	public String getXmiID() {
		return xmiID;
	}
	public void setXmiID(String xmiID) {
		this.xmiID = xmiID;
	}
	public String getXmiType() {
		return xmiType;
	}
	public void setXmiType(String xmiType) {
		this.xmiType = xmiType;
	}
}
