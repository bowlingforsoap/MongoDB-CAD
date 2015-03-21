package ua.kharkiv.knure.dimploma.interpreter.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Abstraction;
import ua.kharkiv.knure.dimploma.containers.Association;
import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Generalization;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.OwnedEnd;
import ua.kharkiv.knure.dimploma.containers.Realization;
import ua.kharkiv.knure.dimploma.containers.Relation;
import ua.kharkiv.knure.dimploma.parser.NamesContainer;

public class Analyzer {
	public static List<Object> getHierarchyRoots(Container container) {
		List<Object> hierarchyRoots = new ArrayList<Object>();
		Collection<Object> objects = container.getObjects().values();

		for (Object object : objects) {
			if (Analyzer.isRoot(container, object)) {
				hierarchyRoots.add(object);
			}
		}
		return hierarchyRoots;
	}

	public static boolean isRoot(Container container, Object object) {
		Collection<Abstraction> abstractions = container.getAbstractions()
				.values();
		boolean hasChilder = false;
		String objectXmiID = object.getXmiID();

		for (Abstraction abstraction : abstractions) {
			try {
				// checking if abstraction is Realization
				Realization r = (Realization) abstraction;
				if (r.getClient().equals(objectXmiID)) {
					return false;
				}
				if (r.getSupplier().equals(objectXmiID)) {
					hasChilder = true;
				}
			} catch (ClassCastException e) {
				// checking if abstraction is Generalization
				Generalization g = (Generalization) abstraction;
				if (g.getOwner().equals(objectXmiID)) {
					return false;
				}
				if (g.getGeneral().equals(objectXmiID)) {
					hasChilder = true;
				}
			}
		}
		return hasChilder;
	}

	public static boolean isObjectToAssociation(Container container,
			Object object) {
		Collection<Relation> relations = container.getRelations().values();
		String objectXmiID = object.getXmiID();
		// System.out.println(objectXmiID);

		for (Relation relation : relations) {
			Association association = (Association) relation;
			OwnedEnd[] ownedEnds = association.getOwnedEnds();

			for (int i = 0; i < ownedEnds.length; i++) {
				// System.out.println(ownedEnds[i].getType());
				if (ownedEnds[i].getType().equals(objectXmiID)
						&& ownedEnds[i].getNavigable() == true) {
					return true;
				}
			}
		}

		return false;
	}

	// REPEATING CODE!
	public static boolean isObjectToAssociations(Container container,
			Object object) {
		int count = 0;
		Collection<Relation> relations = container.getRelations().values();
		String objectXmiID = object.getXmiID();

		for (Relation relation : relations) {
			Association association = (Association) relation;
			OwnedEnd[] ownedEnds = association.getOwnedEnds();

			for (int i = 0; i < ownedEnds.length; i++) {
				if (ownedEnds[i].getType().equals(objectXmiID)
						&& ownedEnds[i].getNavigable() == true) {
					count++;
				}
			}
		}

		if (count > 1) {
			return true;
		}
		return false;
	}

	// REPEATING CODE!
	public static boolean isSubjectToAssociation(Container container,
			Object object) {
		Collection<Relation> relations = container.getRelations().values();
		String objectXmiID = object.getXmiID();
		// System.out.println(objectXmiID);

		for (Relation relation : relations) {
			Association association = (Association) relation;
			OwnedEnd[] ownedEnds = association.getOwnedEnds();

			for (int i = 0; i < ownedEnds.length; i++) {
				// System.out.println(ownedEnds[i].getType());
				if (ownedEnds[i].getType().equals(objectXmiID)
						&& ownedEnds[i].getNavigable() == false) {
					return true;
				}
			}
		}

		return false;
	}

	// REPEATING CODE!
	public static boolean isSubjectToAssociations(Container container,
			Object object) {
		int count = 0;
		Collection<Relation> relations = container.getRelations().values();
		String objectXmiID = object.getXmiID();

		for (Relation relation : relations) {
			Association association = (Association) relation;
			OwnedEnd[] ownedEnds = association.getOwnedEnds();

			for (int i = 0; i < ownedEnds.length; i++) {
				if (ownedEnds[i].getType().equals(objectXmiID)
						&& ownedEnds[i].getNavigable() == false) {
					count++;
				}
			}
		}

		if (count > 1) {
			return true;
		}
		return false;
	}

	public static boolean isClass(Object object) {
		if (object != null
				&& object.getXmiType().equals(
						NamesContainer.CLASS_TAG_PACKAGED_ELEMENT)) {
			return true;
		}
		return false;
	}

	public static boolean isAbstract(Object object) {
		if (Analyzer.isClass(object)) {
			return Boolean.valueOf(((Class) object).getIsAbstract());
		}
		return false;
	}

	public static boolean isInterface(Object object) {
		if (object != null
				&& object.getXmiType().equals(
						NamesContainer.INTERFACE_TAG_PACKAGED_ELEMENT)) {
			return true;
		}
		return false;
	}

	public static List<Object> getChildren(Container container, Object object) {
		if (Analyzer.isParent(container, object)) {
			Collection<Abstraction> abstractions = container.getAbstractions()
					.values();
			List<Object> children = new ArrayList<>();
			String objectXmiID = object.getXmiID();

			for (Abstraction abstraction : abstractions) {
				try {
					Realization r = (Realization) abstraction;
					if (r.getSupplier().equals(objectXmiID)) {
						children.add((Object) container
								.getElementByXmiID(r.getClient()));
					}
				} catch (ClassCastException e) {
					Generalization g = (Generalization) abstraction;
					if (g.getGeneral().equals(objectXmiID)) {
						children.add((Object) container
								.getElementByXmiID(g.getOwner()));
					}
				}
			}
			return children;
		}
		return null;
	}

	public static boolean isParent(Container container, Object object) {
		Collection<Abstraction> abstractions = container.getAbstractions()
				.values();
		String objectXmiID = object.getXmiID();

		for (Abstraction abstraction : abstractions) {
			try {
				Realization r = (Realization) abstraction;
				if (r.getSupplier().equals(objectXmiID)) {
					return true;
				}
			} catch (ClassCastException e) {
				Generalization g = (Generalization) abstraction;
				if (g.getGeneral().equals(objectXmiID)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param object
	 * @param subject
	 * @return
	 */
	public static Abstraction getAbstractionByObjAndSubj(Object object, Object subject) {
		return null;
	}
}
