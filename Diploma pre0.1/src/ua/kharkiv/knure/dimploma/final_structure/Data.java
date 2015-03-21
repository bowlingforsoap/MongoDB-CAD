package ua.kharkiv.knure.dimploma.final_structure;

import java.util.ArrayList;
import java.util.List;

import vivin.GenericTree;

/**
 * This class is used as a container for data, when utilizing
 * {@link GenericTree}.
 * 
 * @author Vadym
 * 
 */
public class Data {
	protected List<BoundObjects> boundObjects;
	
	public Data() {
		boundObjects = new ArrayList<>();
	}

	public List<BoundObjects> getBoundObjects() {
		return boundObjects;
	}

	public void setBoundObjects(List<BoundObjects> boundObjects) {
		this.boundObjects = boundObjects;
	}

	@Override
	public String toString() {
		return "Data [boundObjects=" + boundObjects + "]";
	}
}
