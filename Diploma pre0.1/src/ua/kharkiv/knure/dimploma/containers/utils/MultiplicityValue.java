package ua.kharkiv.knure.dimploma.containers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiplicityValue {
	public static final String FEW = "few";
	public static final String N = "n";
	public static final String STAR = "*";
	public static final String LOWEST = "0";
	public static final String SECOND_LOWEST = "1";
	private final String REGEXP = "((^[0-9]{1,}$)|(^" + MultiplicityValue.FEW
			+ "$)|(^" + MultiplicityValue.N + "$)|(^\\"
			+ MultiplicityValue.STAR + "$))";
	protected String value;
	protected Boolean isANumber;

	public MultiplicityValue(String value) throws NotValidMultiplicityException {
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) throws NotValidMultiplicityException {
		Pattern p = Pattern.compile(REGEXP);
		Matcher m = p.matcher(value.trim());
		if (m.matches()) {
			this.value = value;
			try {
				Long.valueOf(value);
				isANumber = true;
			} catch (NumberFormatException e) {
				isANumber = false;
			}
		} else {
			throw new NotValidMultiplicityException();
		}
	}

	public Boolean getIsANumber() {
		return isANumber;
	}

	public class NotValidMultiplicityException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	@Override
	public String toString() {
		return "MultiplicityValue [ value=" + value + ", isANumber="
				+ isANumber + "]";
	}
}
