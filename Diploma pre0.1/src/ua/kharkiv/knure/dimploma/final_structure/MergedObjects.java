package ua.kharkiv.knure.dimploma.final_structure;

import ua.kharkiv.knure.dimploma.containers.Object;

/**
 * This class indicates that obj1 is merged with obj2.<p/>
 * obj1 is a dominant object, obj2 is the one that is being attached.
 * 
 * @author Vadym
 *
 */
public class MergedObjects extends BoundObjects {
	/**
	 * Store object as subdocument as opposed to merging the fields of the both
	 * objects.
	 */
	protected boolean asSubDoc;

	public Object getOwner() {
		return obj1;
	}

	public void setOwner(Object owner) {
		this.obj1 = owner;
	}

	public Object getOwned() {
		return obj2;
	}

	public void setOwned(Object owned) {
		this.obj2 = owned;
	}

	public boolean isAsSubDoc() {
		return asSubDoc;
	}

	/**
	 * 
	 * @param asSubDoc
	 *            - store object as subdocument as opposed to merging the fileds
	 *            of the both objects.
	 */
	public void setAsSubDoc(boolean asSubDoc) {
		this.asSubDoc = asSubDoc;
	}
}
