package ua.kharkiv.knure.dimploma.containers;

import java.util.Arrays;

public class Association extends Relation {
	protected OwnedEnd[] ownedEnds = {new OwnedEnd(), new OwnedEnd()};

	public void addOwnedEnd(OwnedEnd oe) {
		int i = 0;
		if (ownedEnds[1].getXmiID() == null) {
			i = 1;
		}
		ownedEnds[i].resetOwnedEnd(oe);
	}
	
	public OwnedEnd[] getOwnedEnds() {
		return ownedEnds;
	}

	public void setOwnedEnds(OwnedEnd[] ownedEnds) {
		this.ownedEnds = ownedEnds;
	}

	@Override
	public String toString() {
		return "Association [ownedEnds=" + Arrays.toString(ownedEnds)
				+ ", xmiID=" + xmiID + ", xmiType=" + xmiType + "]";
	}
}
