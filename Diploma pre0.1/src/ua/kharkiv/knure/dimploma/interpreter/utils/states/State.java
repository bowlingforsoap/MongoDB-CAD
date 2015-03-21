package ua.kharkiv.knure.dimploma.interpreter.utils.states;

import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Object;

public abstract class State {
	{
		name = getClass().getSimpleName();
	}
	
	protected String name;
	protected boolean value;
	protected List<State> substitutedBy;
	protected boolean calculated;
	
	public boolean getCalculated() {
		return calculated;
	}

	public String getName() {
		return name;
	}
	public boolean getValue() {
		return value;
	}

	public List<State> getSubstitutedBy() {
		return substitutedBy;
	}
	
	public abstract boolean calculate(Container container, Object object);

	@Override
	public boolean equals(java.lang.Object obj) {
		return name.equals(((State) obj).name);
	}
	
	@Override
	public String toString() {
		return "State [name=" + name + ", value=" + value + ", substitutedBy="
				+ substitutedBy + ", calculated=" + calculated + "]";
	} 
}
