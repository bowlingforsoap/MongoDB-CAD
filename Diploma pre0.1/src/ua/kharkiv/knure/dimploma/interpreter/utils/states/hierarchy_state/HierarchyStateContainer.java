package ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state;

import ua.kharkiv.knure.dimploma.interpreter.utils.states.State;

public class HierarchyStateContainer {
	static {
		
	}
	
	public static State[] getState() {
		return new State[] {new RelationsOnlyToChildren(), new RelationsOnlyToRoot()};
	}
}
