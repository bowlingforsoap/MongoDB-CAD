package ua.kharkiv.knure.dimploma.parser;

public class NameAnalyzer {
	public static final String NAME_AS_IS_REGEXP = "^[A-Za-z]{1,}[A-Za-z0-9]{1,}$";
	public static final String NAME_TO_BE_REGEXP = "[A-Za-z]{1,}";
	public static final String CLEANING_REGEXP = "^[^A-Za-z]{1,}";

	/**
	 * Will analyze names and restructure them, so there is no overlapping, and
	 * all of them can be stored properly.
	 * 
	 * @param name
	 * @return
	 */
	public static String analyzeName(String name) {
		return name;
	}
}
