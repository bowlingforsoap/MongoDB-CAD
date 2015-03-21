package ua.kharkiv.knure.dimploma.interpreter.utils;

import java.util.Comparator;

public class RuleConformity implements Comparable<RuleConformity>, Comparator<RuleConformity> {
	@SuppressWarnings("rawtypes")
	private Rule rule;
	private int conformity;

	@SuppressWarnings("rawtypes")
	public Rule getRule() {
		return rule;
	}

	@SuppressWarnings("rawtypes")
	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public int getConformity() {
		return conformity;
	}

	public void setConformity(int conformity) {
		this.conformity = conformity;
	}

	@Override
	public String toString() {
		return "RuleConformity [rule=" + rule + ", conformity=" + conformity
				+ "]";
	}

	@Override
	public int compareTo(RuleConformity o) {
		if (conformity > o.conformity) {
			return 1;
		} else if (conformity < o.conformity) {
			return -1;
		}
		
		if (rule.getState().size() > o.getRule().getState().size()) {
			return 1;
		} else if (rule.getState().size() < o.getRule().getState().size()) {
			return -1;
		}
		return 0;
	}

	public int compare(RuleConformity o1, RuleConformity o2) {
		return o1.compareTo(o2);
	}
}
