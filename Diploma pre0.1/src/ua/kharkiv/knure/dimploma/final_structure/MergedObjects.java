package ua.kharkiv.knure.dimploma.final_structure;

import ua.kharkiv.knure.dimploma.containers.Object;

public class MergedObjects extends BoundObjects {
	protected Object owner;
	protected Object owned;
	protected boolean asSubDoc;

	public Object getOwner() {
		return owner;
	}

	public void setOwner(Object owner) {
		this.owner = owner;
	}

	public Object getOwned() {
		return owned;
	}

	public void setOwned(Object owned) {
		this.owned = owned;
	}

	public boolean isAsSubDoc() {
		return asSubDoc;
	}

	public void setAsSubDoc(boolean asSubDoc) {
		this.asSubDoc = asSubDoc;
	}
}
