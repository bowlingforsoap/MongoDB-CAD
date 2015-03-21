package ua.kharkiv.knure.dimploma.containers;

public class Realization extends Abstraction {
	protected String supplier;
	protected String client;

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Realization [supplier=" + supplier + ", client=" + client
				+ ", xmiID=" + xmiID + ", xmiType=" + xmiType + "]";
	}
}
