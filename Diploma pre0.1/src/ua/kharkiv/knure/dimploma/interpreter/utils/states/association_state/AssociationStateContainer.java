package ua.kharkiv.knure.dimploma.interpreter.utils.states.association_state;

import ua.kharkiv.knure.dimploma.interpreter.utils.states.State;

public class AssociationStateContainer {
	static {
		
	}
	
	public static State[] getState() {
		return new State[] { new CompositeAssociation(),
				new FewToOneAssociationMultiplicity(),
				new ManyToOneAssociationMultiplicity(),
				new ObjectToOneAssociation(),
				new OneToFewAssociationMultiplicity(),
				new OneToManyAssociationMultiplicity(),
				new OneToOneAssociationMultiplicity() };
	}
}
