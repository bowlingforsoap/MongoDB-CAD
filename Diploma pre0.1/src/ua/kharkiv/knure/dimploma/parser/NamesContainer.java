package ua.kharkiv.knure.dimploma.parser;

import java.util.HashMap;
import java.util.Map;

import ua.kharkiv.knure.dimploma.containers.Association;
import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Interface;
import ua.kharkiv.knure.dimploma.containers.Realization;

public class NamesContainer {
	public static final String ATTR_XMI_ID = "id";
	public static final String ATTR_XMI_TYPE = "type";

	public static final String TAG_PACKAGED_ELEMENT = "packagedElement";
	public static final String TAG_OWNED_ATTRIBUTE = "ownedAttribute";
	public static final String TAG_OWNED_END = "ownedEnd";
	public static final String TAG_LOWER_VALUE = "lowerValue";
	public static final String TAG_UPPER_VALUE = "upperValue";
	public static final String TAG_GENERALIZATION = "generalization";
	public static final String TAG_INTERFACE_REALIZATION = "interfaceRealization";

	public static final String PACKAGE_TAG_PACKAGED_ELEMENT = "Package";
	/**
	 * Objects
	 */
	public static final String CLASS_TAG_PACKAGED_ELEMENT = "Class";
	public static final String INTERFACE_TAG_PACKAGED_ELEMENT = "Interface";
	public static final String[] OBJECTS = { CLASS_TAG_PACKAGED_ELEMENT,
			INTERFACE_TAG_PACKAGED_ELEMENT };
	/**
	 * Relations
	 */
	public static final String ASSOCIATION_TAG_PACKAGED_ELEMENT = "Association";
	public static final String[] RELATIONS = { ASSOCIATION_TAG_PACKAGED_ELEMENT };

	/**
	 * Abstractions
	 */
	public static final String INTERFACE_REALIZTION_TAG_PACKAGED_ELEMENT = "InterfaceRealization";
	public static final String REALIZTION_TAG_PACKAGED_ELEMENT = "Realization";
	public static final String GENERALIZATION_TAG_GENERALIZATION = "Generalization";
	public static final String[] ABSTRACTIONS = {
			INTERFACE_REALIZTION_TAG_PACKAGED_ELEMENT,
			REALIZTION_TAG_PACKAGED_ELEMENT, GENERALIZATION_TAG_GENERALIZATION };
	
	public static final String PROPERTY_TAG_OWNED_ATTRIBUTE_END = "Property";

	@SuppressWarnings("rawtypes")
	public static final Map<String, java.lang.Class> NAME_TYPE_MAP = new HashMap<>();
	static {
		NAME_TYPE_MAP.put(NamesContainer.CLASS_TAG_PACKAGED_ELEMENT, Class.class);
		NAME_TYPE_MAP.put(NamesContainer.INTERFACE_TAG_PACKAGED_ELEMENT, Interface.class);
		NAME_TYPE_MAP.put(NamesContainer.INTERFACE_REALIZTION_TAG_PACKAGED_ELEMENT,	Realization.class);
		NAME_TYPE_MAP.put(NamesContainer.REALIZTION_TAG_PACKAGED_ELEMENT, Realization.class);
		NAME_TYPE_MAP.put(NamesContainer.ASSOCIATION_TAG_PACKAGED_ELEMENT, Association.class);
	}
	
	//ATTRIBUTES
	public static final String[] ATTRS_CLASS_TAG_PACKAGED_ELEMENT = { "isAbstract", "name" };
	public static final String[] ATTRS_INTERFACE_TAG_PACKAGED_ELEMENT = { "name" };
	public static final String[] ATTRS_ASSOCIATION_TAG_PACKAGED_ELEMENT = {};
	public static final String[] ATTRS_REALIZTION_TAG_PACKAGED_ELEMENT = { "client", "supplier" };
	public static final String[] ATTRS_TAG_OWNED_END_ATTRIBUTE = { "isID", "name", "type", "association", "aggregation" };
	public static final String[] ATTRS_TAG_LOWER_UPPER_VALUE = { "value" };
	public static final String[] ATTRS_TAG_GENERALIZATION = { "general" };
	public static final String[] ATTRS_TAG_INTERFACE_REALIZATION = { "supplier" };

	public static final Map<String, String[]> TAG_PACKAGED_ELEMENT_ATTRS_MAP = new HashMap<>();
	static {
		TAG_PACKAGED_ELEMENT_ATTRS_MAP.put(ASSOCIATION_TAG_PACKAGED_ELEMENT, ATTRS_ASSOCIATION_TAG_PACKAGED_ELEMENT);
		TAG_PACKAGED_ELEMENT_ATTRS_MAP.put(CLASS_TAG_PACKAGED_ELEMENT, ATTRS_CLASS_TAG_PACKAGED_ELEMENT);
		TAG_PACKAGED_ELEMENT_ATTRS_MAP.put(INTERFACE_TAG_PACKAGED_ELEMENT,
				ATTRS_INTERFACE_TAG_PACKAGED_ELEMENT);
		TAG_PACKAGED_ELEMENT_ATTRS_MAP.put(REALIZTION_TAG_PACKAGED_ELEMENT,
				ATTRS_REALIZTION_TAG_PACKAGED_ELEMENT);
	}
}
