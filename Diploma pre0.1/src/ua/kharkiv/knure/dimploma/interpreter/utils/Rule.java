package ua.kharkiv.knure.dimploma.interpreter.utils;

import java.util.List;

import ua.kharkiv.knure.dimploma.interpreter.utils.actions.Action;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.State;

public class Rule<T extends State> {
	protected List<T> state;
	protected List<Action> results;

	public List<T> getState() {
		return state;
	}

	public void setState(List<T> states) {
		this.state = states;
	}

	public List<Action> getResults() {
		return results;
	}

	public void setResults(List<Action> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (State s : state) {
			result += s.getName() + ":" + s.getValue() + " ";
		}
		return result;
	}
}
