package ua.kharkiv.knure.dimploma.final_structure.utils;

import java.util.Arrays;
import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Multiplicity;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.containers.Property;
import ua.kharkiv.knure.dimploma.containers.utils.MultiplicityValue;

import com.mongodb.BasicDBObject;

public class Util {
	public static String COLLECTION_NAME = "name";
	public static String COLLECTION_ID = "id";
	public static String PROPERTY_NAME = "name";
	public static String PROPERTY_ID = "id";

	public static BasicDBObject objectToBasicDBObject(Object object) {
		BasicDBObject collection = new BasicDBObject();
		collection.append(Util.COLLECTION_NAME, object.getName());
		collection.append(Util.COLLECTION_ID, object.getXmiID());
		try {
			Class classObject = (Class) object;
			List<Property> properties = classObject.getProperties();
			if (properties != null) {
				for (Property property : properties) {
					propertyToBasicDBObject(property, collection);
				}
			}
		} catch (ClassCastException e) {
		}
		return collection;
	}

	public static void propertyToBasicDBObject(Property property, BasicDBObject targetCollection) {
		if (property != null) {
			String upperMultiValue;
			if (property.getMultiplicity().getUpper() != null) {
				upperMultiValue = property.getMultiplicity().getUpper()
						.getValue();
			} else {
				upperMultiValue = String
						.valueOf(Multiplicity.DEFAULT_UPPER_MULTIPLICITY);
			}
			if (upperMultiValue.equals(MultiplicityValue.LOWEST)
					|| upperMultiValue.equals(MultiplicityValue.SECOND_LOWEST)) {
				targetCollection.append(property.getName(), property.getType());
			} else {
				targetCollection.append(property.getName(), Arrays.asList(new String[] {property.getType()}));
			}
		}
	}
}
