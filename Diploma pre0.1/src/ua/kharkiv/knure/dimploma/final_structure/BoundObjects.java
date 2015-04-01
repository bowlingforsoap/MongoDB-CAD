package ua.kharkiv.knure.dimploma.final_structure;

import ua.kharkiv.knure.dimploma.containers.Object;

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
	protected Object obj1;
	protected Object obj2;
	//a filed must be added to represent a magnitude of a link

	public boolean isDflt() {
		return dflt;
	}

	public void setDflt(boolean dflt) {
		this.dflt = dflt;
	}
}
