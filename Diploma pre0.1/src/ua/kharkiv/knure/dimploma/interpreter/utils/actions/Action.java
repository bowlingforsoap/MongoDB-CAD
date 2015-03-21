package ua.kharkiv.knure.dimploma.interpreter.utils.actions;

public abstract class Action {
	/**
	 * Use default way to set linking for a parent:child pair (true/false).
	 */
	protected boolean dflt;
	public final String ACTION_TYPE;
	
	{
		ACTION_TYPE = this.getClass().getSimpleName();
	}

	public boolean isDflt() {
		return dflt;
	}

	public void setDflt(boolean dflt) {
		this.dflt = dflt;
	}
}
