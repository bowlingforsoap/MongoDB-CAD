package ua.kharkiv.knure.dimploma.containers.utils;

import ua.kharkiv.knure.dimploma.containers.Object;

public class Util {
	public static final String PACKAGE = "ua.kharkiv.knure.dimploma.containers.";
	
	public static Object stringToElement(String element) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = Class.forName(Util.PACKAGE + element);
		return (Object) clazz.newInstance();
	}
}
