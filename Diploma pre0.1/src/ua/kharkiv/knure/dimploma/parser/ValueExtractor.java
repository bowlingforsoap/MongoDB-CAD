package ua.kharkiv.knure.dimploma.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueExtractor {
	/**
	 * Regexp for a "namespace:element_name" combination
 	 */
	public static final String NS_NAME_REGEXP = "^([A-Za-z0-9-_]{1,}:[A-Za-z0-9-_]{1,}$)";
	/**
	 * Regexp for just a "element_name"
	 */
	public static final String NAME_REGEXP = "^[A-Za-z0-9-_]{1,}$";
	/**
	 * Regexp for just a "namespace"
	 */
	public static final String NS_REGEXP = "^[A-Za-z0-9-_]{1,}:";
	
	/**
	 * Checks for the {@code ValueExtractor.NS_NAME_REGEXP} match in string and returns the "element_name" part, if
	 * there was a match.
	 * 
	 * @param targetString
	 *            - string to parse.
	 * @return Target name, or null, if there was no match for the regexp.
	 */
	public static String checkForNSName(String targetString) {
		if (targetString == null) {
			return null;
		}

		targetString = targetString.trim();
		Pattern p = Pattern.compile(ValueExtractor.NS_NAME_REGEXP);
		Matcher m = p.matcher(targetString);

		if (m.matches()) {
			Pattern nsPattern = Pattern.compile(ValueExtractor.NS_REGEXP);
			Matcher replacer = nsPattern.matcher(targetString);
			String result = replacer.replaceAll("");
			return result;
		}
		return null;
	}

	/**
	 * Checks for the {@code ValueExtractor.NAME_REGEXP} match in string and returns the "element_name" part, if
	 * there was a match.
	 * 
	 * @param targetString
	 *            - string to parse.
	 * @return Target name, or null, if there was no match for the regexp.
	 */
	public static String checkForName(String targetString) {
		if (targetString == null) {
			return null;
		}

		targetString = targetString.trim();
		Pattern p = Pattern.compile(ValueExtractor.NAME_REGEXP);
		Matcher m = p.matcher(targetString);

		if (m.matches()) {
			return targetString;
		}

		return null;
	}
	
	public static String checkForBoth(String targetString) {
		String result = checkForName(targetString);
		if (result == null) {
			return checkForNSName(targetString);			
		}
		
		return result;
	}
	
	//REDO!
	public static boolean checkForXmiNS(String attrName, String xmiNSCol) {
		attrName = attrName.trim();
		if (attrName.contains(xmiNSCol)) {
			return true;
		} else {
			return false;
		}
	}
}
