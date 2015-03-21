package ua.kharkiv.knure.dimploma.final_structure;

/**
 * The superclass for all interpreted bound objects, such as: {@link MergedObjects}, {@link LinkedObjects}.
 * @author Vadym
 *
 */
public abstract class BoundObjects extends Interpreted {
	/**
	 * Use default way to set linking for a parent:child pair (true/false).
	 */
	protected boolean dflt;

	public boolean isDflt() {
		return dflt;
	}

	public void setDflt(boolean dflt) {
		this.dflt = dflt;
	}
}
