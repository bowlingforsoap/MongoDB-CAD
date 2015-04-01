package ua.kharkiv.knure.dimploma.containers;

import ua.kharkiv.knure.dimploma.containers.utils.MultiplicityValue;
import ua.kharkiv.knure.dimploma.containers.utils.MultiplicityValue.NotValidMultiplicityException;

public class Multiplicity {
	protected MultiplicityValue lower;
	protected MultiplicityValue upper;
	public static final int DEFAULT_LOWER_MULTIPLICITY = 0;
	public static final int DEFAULT_UPPER_MULTIPLICITY = 1;
	public Multiplicity() {
		
	}

	public Multiplicity(boolean composite) {
		if (composite) {
			try {
				setLower("1");
				setUpper("1");
			} catch (NotValidMultiplicityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Multiplicity(String lower, String upper) {
		try {
			this.lower = new MultiplicityValue(lower);
			this.upper = new MultiplicityValue(upper);
		} catch (NotValidMultiplicityException e) {
			e.printStackTrace();
		}
	}

	public MultiplicityValue getLower() {
		return lower;
	}

	public void setLower(String lower) throws NotValidMultiplicityException {
		this.lower = new MultiplicityValue(lower);
	}

	public MultiplicityValue getUpper() {
		return upper;
	}

	public void setUpper(String upper) throws NotValidMultiplicityException {
		this.upper = new MultiplicityValue(upper);
	}
	
	@Override
	public String toString() {
		return "Multiplicity [lower=" + lower + ", upper=" + upper + "]";
	}
	
	public static void main(String[] args) {
		Multiplicity m = new Multiplicity(true);
		System.out.println(m);
	}
}
