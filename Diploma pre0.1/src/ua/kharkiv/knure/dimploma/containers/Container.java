package ua.kharkiv.knure.dimploma.containers;

import java.util.Map;
import java.util.HashMap;

public class Container {
	protected Map<String, Object> objects;
	protected Map<String, Relation> relations;
	protected Map<String, Abstraction> abstractions;

	public Container() {
		objects = new HashMap<>();
		relations = new HashMap<>();
		abstractions = new HashMap<>();
	}

	public Map<String, Object> getObjects() {
		return objects;
	}

	public void setObjects(Map<String, Object> objects) {
		this.objects = objects;
	}

	public Map<String, Relation> getRelations() {
		return relations;
	}

	public void setRelations(Map<String, Relation> relations) {
		this.relations = relations;
	}

	public Map<String, Abstraction> getAbstractions() {
		return abstractions;
	}

	public void setAbstractions(Map<String, Abstraction> abstractions) {
		this.abstractions = abstractions;
	}

	//REDO!
	//redundancy
	public Element getElementByXmiID(String xmiID) {
		if (xmiID != null) {
			for (Object object : objects.values()) {
				if (object.getXmiID().equals(xmiID)) {
					return object;
				}
			}
			for (Abstraction abstraction : abstractions.values()) {
				if (abstraction.getXmiID().equals(xmiID)) {
					return abstraction;
				}
			}
			for (Relation relation : relations.values()) {
				if (relation.getXmiID().equals(xmiID)) {
					return relation;
				}
			}
		}
		return null;
	}
}
