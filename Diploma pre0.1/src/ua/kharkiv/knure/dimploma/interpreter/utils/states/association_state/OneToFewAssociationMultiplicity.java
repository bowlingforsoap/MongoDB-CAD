package ua.kharkiv.knure.dimploma.interpreter.utils.states.association_state;

import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.State;

public class OneToFewAssociationMultiplicity extends State {

	@Override
	public boolean calculate(Container container, Object object) {
		return false;
	}

}
