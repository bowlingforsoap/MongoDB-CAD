package ua.kharkiv.knure.dimploma.interpreter.utils.actions;

public class LinkAction extends Action {
	protected boolean obj1Linked;
	protected boolean obj2Linked;

	public boolean isObj1Linked() {
		return obj1Linked;
	}

	public void setObj1Linked(boolean obj1Linked) {
		this.obj1Linked = obj1Linked;
	}

	public boolean isObj2Linked() {
		return obj2Linked;
	}

	public void setObj2Linked(boolean obj2Linked) {
		this.obj2Linked = obj2Linked;
	}
}
