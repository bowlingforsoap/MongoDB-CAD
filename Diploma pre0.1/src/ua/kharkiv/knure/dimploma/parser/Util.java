package ua.kharkiv.knure.dimploma.parser;

public class Util {
	public static boolean contains(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return true;
			}
		}
		return false;
	}
}
