package ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state;

import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.interpreter.utils.Analyzer;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.HierarchyState;

public class RelationsOnlyToRoot extends HierarchyState {
	public RelationsOnlyToRoot() {
	}
	
	public RelationsOnlyToRoot(Container container, Object root) {
		calculate(container, root);
	}

	@Override
	public boolean calculate(Container container, Object object) {
		calculated = true;
		if (Analyzer.isRoot(container, object)) {
			boolean relationsToRoot = Analyzer.isObjectToAssociation(container,
					object);
			// if root is an object to Association, check children for being
			// objects
			if (relationsToRoot) {
				List<Object> children = Analyzer.getChildren(container, object);
				if (children != null && children.size() != 0) {
					for (Object child : children) {
						if (Analyzer.isObjectToAssociation(container, child)) {
							// if they are, value is false
							value = false;
							return value;
						}
					}
				}
				// if they are not, value is true
				value = true;
			}
		}
		return value;
	}
	
	public static RelationsOnlyToRoot getState(boolean value) {
		RelationsOnlyToRoot rotr = new RelationsOnlyToRoot();
		
		rotr.value = value;
		rotr.calculated = true;
		return rotr;
	}
}
