package ua.kharkiv.knure.dimploma.interpreter.utils.actions;

public class HierarchyMergeAction extends MergeAction {
	protected boolean mergeChildren;

	public boolean isMergeChildren() {
		return mergeChildren;
	}

	public void setMergeChildren(boolean mergeChildren) {
		this.mergeChildren = mergeChildren;
	}
}