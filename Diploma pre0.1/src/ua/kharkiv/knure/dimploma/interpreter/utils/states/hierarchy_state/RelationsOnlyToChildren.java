package ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state;

import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.interpreter.utils.Analyzer;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.HierarchyState;

public class RelationsOnlyToChildren extends HierarchyState {	
	public RelationsOnlyToChildren() {
		
	}
	
	public RelationsOnlyToChildren(Container container, Object root) {
		calculate(container, root);
	}
	
	@Override
	public boolean calculate(Container container, Object object) {
		calculated = true;
		if (Analyzer.isRoot(container, object)) {
			boolean relationsToRoot = Analyzer.isObjectToAssociation(container,
					object);
			if (!relationsToRoot) {
				//counter for children
				int count = 0;
				List<Object> children = Analyzer.getChildren(container, object);
				if (children != null && children.size() != 0) {
					for (Object child : children) {
						if (Analyzer.isObjectToAssociation(container, child)) {
							// if they are, value is false
							count++;
						}
					}
					if (count > 1) {
						value = true;
						return value;
					}
				}
			}
		}
		
		value = false;
		return value;
	}
	
	public static RelationsOnlyToChildren getState(boolean value) {
		RelationsOnlyToChildren rotch = new RelationsOnlyToChildren();
		
		rotch.value = value;
		rotch.calculated = true;
		return rotch;
	}
}
