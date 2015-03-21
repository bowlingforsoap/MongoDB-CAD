package ua.kharkiv.knure.dimploma.parser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ua.kharkiv.knure.dimploma.containers.Abstraction;
import ua.kharkiv.knure.dimploma.containers.Association;
import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Generalization;
import ua.kharkiv.knure.dimploma.containers.Multiplicity;
import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Interface;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.containers.OwnedEnd;
import ua.kharkiv.knure.dimploma.containers.Property;
import ua.kharkiv.knure.dimploma.containers.Realization;
import ua.kharkiv.knure.dimploma.containers.Relation;
import ua.kharkiv.knure.dimploma.containers.utils.MultiplicityValue.NotValidMultiplicityException;
import ua.kharkiv.knure.dimploma.interpreter.Interpreter;

public class DOMParser {
	private Container container;
	private File file;
	private String xmiNS;
	private Map<String, List<Node>> packagedElements = new HashMap<>();

	{
		container = new Container();
	}

	public DOMParser(String path, String xmiNS) {
		file = new File(path);
		this.xmiNS = xmiNS;
	}

	public DOMParser(File file) {
		this.file = file;
	}

	/**
	 * Main method of DOMParser.
	 * 
	 * @return
	 */
	public Container parse() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			Element root = document.getDocumentElement();
			Node rootNode = checkForPackages(root);
			parsePackagedElementXMIAttrs(rootNode);
			parseObjects();
			parseRelations();
			parseAbstractions();
			restructureObjectsNames();

			for (Object object : container.getObjects().values()) {
				/*System.out
						.println(ua.kharkiv.knure.dimploma.final_structure.utils.Util
								.objectToBasicDBObject(object));*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return container;
	}

	/**
	 * Restructures names, so that they are unique and proper.
	 */
	private void restructureObjectsNames() {
		Collection<Object> objects = container.getObjects().values();

		for (Object object : objects) {
			NameAnalyzer.analyzeName(object.getName());
		}
	}

	public String getXMINS() {
		return xmiNS;
	}

	/**
	 * Returns {@code xmiNS} followed by ":".
	 * 
	 * @return Concatenation: ({@code xmiNS} + ":")
	 */
	public String getXMINSCol() {
		return xmiNS + ":";
	}

	public static void main(String[] args) {


		/*
		 * Collection<Abstraction> a = container.getAbstractions().values(); for
		 * (Abstraction abst : a) { System.out.println(abst); }
		 * Collection<Object> o = container .getObjects().values(); for (Object
		 * obj : o) { System.out.println(obj); } Collection<Relation> r =
		 * container.getRelations().values(); for (Relation rel : r) {
		 * System.out.println(rel); }
		 * 
		 * System.out.println(); List<Object> roots =
		 * Analyzer.getHierarchyRoots(container); for (Object root : roots) {
		 * RelationsOnlyToRoot rotr = new RelationsOnlyToRoot(container, root);
		 * RelationsOnlyToChildren rotch = new
		 * RelationsOnlyToChildren(container, root); System.out.println(root);
		 * System.out.println(rotr.getName() + ":" + rotr.isValue());
		 * System.out.println(rotch.getName() + ":" + rotch.isValue());
		 * System.out.println(); }
		 */

		/*
		 * System.out.println(); Collection<Object> objects =
		 * container.getObjects().values(); for (Object object : objects) { if
		 * (Analyzer.isSubjectToAssociation(container, object)) {
		 * System.out.println(object); } }
		 */
	}

	/**
	 * Checks for Package elements in Model and returns needed node for further
	 * parsing.
	 * 
	 * @param root
	 *            - root element of a document.
	 * @return The node for further parsing.
	 */
	private Node checkForPackages(Element root) {
		// HARDCODE ALERT!
		NodeList packagedElements = root
				.getElementsByTagName(NamesContainer.TAG_PACKAGED_ELEMENT);
		if (packagedElements == null) {
			return null;
		}

		theLoop: for (int i = 0; i < packagedElements.getLength(); i++) {
			NamedNodeMap attrs = packagedElements.item(i).getAttributes();
			if (attrs == null) {
				continue theLoop;
			}
			// HARDCODE ALERT!
			if (attrs
					.getNamedItem(getXMINSCol() + NamesContainer.ATTR_XMI_TYPE)
					.getNodeValue()
					.endsWith(NamesContainer.PACKAGE_TAG_PACKAGED_ELEMENT)) {
				return packagedElements.item(i);
			}
		}
		return root;
	}

	@SuppressWarnings("rawtypes")
	private void parsePackagedElementXMIAttrs(Node rootNode) {
		NodeList childNodes = rootNode.getChildNodes();

		if (childNodes.getLength() != 0) {
			for (int i = 0; i < childNodes.getLength(); i++) {
				NamedNodeMap attrs = childNodes.item(i).getAttributes();

				if (attrs != null) {
					Node xmiIDNode = attrs.getNamedItem(getXMINSCol()
							+ NamesContainer.ATTR_XMI_ID);
					Node xmiTypeNode = attrs.getNamedItem(getXMINSCol()
							+ NamesContainer.ATTR_XMI_TYPE);

					if (xmiIDNode != null && xmiTypeNode != null) {
						String xmiID = ValueExtractor.checkForBoth(xmiIDNode
								.getNodeValue());
						String xmiType = ValueExtractor
								.checkForBoth(xmiTypeNode.getNodeValue());
						if (xmiID != null && xmiType != null) {
							// if there are no list of nodes, create one
							if (packagedElements.get(xmiType) == null) {
								List<Node> nodeList = new ArrayList<Node>();
								packagedElements.put(xmiType, nodeList);
							}
							// get appropriate list
							List<Node> nodeList = packagedElements.get(xmiType);
							// construct an Element
							ua.kharkiv.knure.dimploma.containers.Element element = null;
							try {
								element = (ua.kharkiv.knure.dimploma.containers.Element) ((java.lang.Class) NamesContainer.NAME_TYPE_MAP
										.get(xmiType)).newInstance();
							} catch (InstantiationException
									| IllegalAccessException e) {
								e.printStackTrace();
							}
							// set XMI attributes
							element.setXmiID(xmiID);
							element.setXmiType(xmiType);
							// save node to node list
							nodeList.add(childNodes.item(i));
							// save element to an appropriate container
							if (Util.contains(NamesContainer.ABSTRACTIONS,
									xmiType)) {
								container.getAbstractions().put(xmiID,
										(Abstraction) element);
							} else if (Util.contains(NamesContainer.OBJECTS,
									xmiType)) {
								container
										.getObjects()
										.put(xmiID,
												(ua.kharkiv.knure.dimploma.containers.Object) element);
							} else if (Util.contains(NamesContainer.RELATIONS,
									xmiType)) {
								container.getRelations().put(xmiID,
										(Relation) element);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * 
	 * @param rootNode
	 */
	private void parseObjects() {
		List<Node> classNodes = packagedElements
				.get(NamesContainer.CLASS_TAG_PACKAGED_ELEMENT);
		if (classNodes != null)
			for (Node node : classNodes) {
				parseClass(node);
			}

		List<Node> interfaceNodes = packagedElements
				.get(NamesContainer.INTERFACE_TAG_PACKAGED_ELEMENT);
		if (interfaceNodes != null)
			for (Node node : interfaceNodes) {
				parseInterface(node);
			}
	}

	// ◊“Œ ƒ≈À¿“‹,  Œ√ƒ¿ œŒœ¿ƒ¿≈“—ﬂ PROPERTY — ¿——Œ÷»¿÷»≈…?
	private void parseClass(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		String xmiID = attrs.getNamedItem(
				getXMINSCol() + NamesContainer.ATTR_XMI_ID).getNodeValue();
		Class targetClass = (Class) container.getObjects().get(xmiID);
		String[] attrsToParse = NamesContainer.TAG_PACKAGED_ELEMENT_ATTRS_MAP
				.get(NamesContainer.CLASS_TAG_PACKAGED_ELEMENT);

		parseListedAttrs(attrsToParse, attrs, targetClass);

		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			String nodeName = ValueExtractor.checkForBoth(childNodes.item(i)
					.getNodeName());
			if (nodeName != null) {
				if (nodeName.equals(NamesContainer.TAG_OWNED_ATTRIBUTE)) {
					parseProperty(childNodes.item(i), targetClass, false);
				}
				if (nodeName.equals(NamesContainer.TAG_GENERALIZATION)) {
					parseGeneralization(childNodes.item(i), targetClass);
				}
				if (nodeName.equals(NamesContainer.TAG_INTERFACE_REALIZATION)) {
					parseIntefaceRealization(childNodes.item(i), targetClass);
				}
			}
		}
	}

	private void parseIntefaceRealization(Node intefaceRealizationNode,
			Class client) {
		Realization r = new Realization();
		parseNodeXMIAttrs(intefaceRealizationNode, r);
		if (r.getXmiType() == null) {
			r.setXmiType(NamesContainer.INTERFACE_REALIZTION_TAG_PACKAGED_ELEMENT);
		}
		NamedNodeMap attrs = intefaceRealizationNode.getAttributes();
		String[] interfaceRealizationAttrs = NamesContainer.ATTRS_TAG_INTERFACE_REALIZATION;

		r.setClient(client.getXmiID());
		parseListedAttrs(interfaceRealizationAttrs, attrs, r);
		container.getAbstractions().put(r.getXmiID(), r);
	}

	private void parseInterface(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		String xmiID = attrs.getNamedItem(
				getXMINSCol() + NamesContainer.ATTR_XMI_ID).getNodeValue();
		Interface targetInterface = (Interface) container.getObjects().get(
				xmiID);
		String[] attrsToParse = NamesContainer.TAG_PACKAGED_ELEMENT_ATTRS_MAP
				.get(NamesContainer.INTERFACE_TAG_PACKAGED_ELEMENT);
		NodeList childNodes = node.getChildNodes();

		parseListedAttrs(attrsToParse, attrs, targetInterface);
		// checking all children
		for (int i = 0; i < childNodes.getLength(); i++) {
			String nodeName = ValueExtractor.checkForBoth(childNodes.item(i)
					.getNodeName());
			if (nodeName != null
					&& nodeName.equals(NamesContainer.TAG_GENERALIZATION)) {
				parseGeneralization(childNodes.item(i), targetInterface);
			}
		}
	}

	private void parseRelations() {
		List<Node> associationNodes = packagedElements
				.get(NamesContainer.ASSOCIATION_TAG_PACKAGED_ELEMENT);
		if (associationNodes != null)
			for (Node node : associationNodes) {
				parseAssociation(node);
			}
	}

	private void parseAssociation(Node node) {
		// get association's attrs
		NamedNodeMap attrs = node.getAttributes();
		if (attrs != null) {
			// get xmiID attr
			Node xmiIDNode = attrs.getNamedItem(getXMINSCol()
					+ NamesContainer.ATTR_XMI_ID);
			if (xmiIDNode != null) {
				// get association's child nodes
				NodeList childNodes = node.getChildNodes();

				// iterate over child nodes
				for (int i = 0; i < childNodes.getLength(); i++) {
					// get node's name
					String nodeName = ValueExtractor.checkForBoth(childNodes
							.item(i).getNodeName());
					// if tag's name is "ownedEnd", then get it's attributes
					if (nodeName != null
							&& nodeName.equals(NamesContainer.TAG_OWNED_END)) {
						NamedNodeMap childAttrs = childNodes.item(i)
								.getAttributes();
						// if there are some attrs, get xmi:type of the tag
						if (attrs != null) {
							Node xmiTypeNode = childAttrs
									.getNamedItem(getXMINSCol()
											+ NamesContainer.ATTR_XMI_TYPE);
							// if xmi:type is absent or it equals to "Property",
							// then parse as a Property
							if (xmiTypeNode == null
									|| ValueExtractor
											.checkForBoth(
													xmiTypeNode.getNodeValue())
											.equals(NamesContainer.PROPERTY_TAG_OWNED_ATTRIBUTE_END)) {
								parseProperty(childNodes.item(i), null, true);
							}
						}
					}
				}
			}
		}
	}

	private void parseAbstractions() {
		List<Node> realizationNodes = packagedElements
				.get(NamesContainer.REALIZTION_TAG_PACKAGED_ELEMENT);
		if (realizationNodes != null)
			for (Node node : realizationNodes) {
				parseRealization(node);
			}

		List<Node> interfaceRealizationNodes = packagedElements
				.get(NamesContainer.INTERFACE_REALIZTION_TAG_PACKAGED_ELEMENT);
		if (interfaceRealizationNodes != null)
			for (Node node : interfaceRealizationNodes) {
				parseRealization(node);
			}
	}

	private void parseRealization(Node realizationNode) {
		String xmiID = extractXmiID(realizationNode);
		Realization r = (Realization) container.getAbstractions().get(xmiID);
		NamedNodeMap allAttrs = realizationNode.getAttributes();
		String[] listedAttrs = NamesContainer.ATTRS_REALIZTION_TAG_PACKAGED_ELEMENT;

		parseListedAttrs(listedAttrs, allAttrs, r);
		container.getAbstractions().put(r.getXmiID(), r);
	}

	private String extractXmiID(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String attrName = attrs.item(i).getNodeName();
				if (attrName != null
						&& attrName.equals(getXMINSCol()
								+ NamesContainer.ATTR_XMI_ID)) {
					return attrs.item(i).getNodeValue();
				}
			}
		}
		return null;
	}

	private void parseGeneralization(Node generalizationNode,
			ua.kharkiv.knure.dimploma.containers.Element owner) {
		Generalization g = new Generalization();
		parseNodeXMIAttrs(generalizationNode, g);
		if (g.getXmiType() == null) {
			g.setXmiType(NamesContainer.GENERALIZATION_TAG_GENERALIZATION);
		}
		NamedNodeMap attrs = generalizationNode.getAttributes();
		String[] generalizationAttrs = NamesContainer.ATTRS_TAG_GENERALIZATION;

		g.setOwner(owner.getXmiID());
		parseListedAttrs(generalizationAttrs, attrs, g);
		container.getAbstractions().put(g.getXmiID(), g);
	}

	// PARSES PROPERTY TYPE WRONG!
	/**
	 * Parses attributes listed in {@code listedAttrs} from {@code allAttrs} and
	 * saves them to {@code targetElement}.
	 * 
	 * @param listedAttrs
	 * @param allAttrs
	 * @param targetElement
	 */
	private void parseListedAttrs(String[] listedAttrs, NamedNodeMap allAttrs,
			ua.kharkiv.knure.dimploma.containers.Element targetElement) {
		for (int i = 0; i < listedAttrs.length; i++) {
			// Retrieving an attribute to parse
			String attrToParse = listedAttrs[i];
			// Iterating over attributes in the given node
			theLoop: for (int j = 0; j < allAttrs.getLength(); j++) {
				// retrieving an attribute name from a node
				if (allAttrs.item(j).getNodeName() == null
						|| ValueExtractor.checkForXmiNS(allAttrs.item(j)
								.getNodeName(), getXMINSCol())) {
					//System.out.println(allAttrs.item(j).getNodeName());
					continue theLoop;
				}
				//System.out.println("after check: "
						//+ allAttrs.item(j).getNodeName());
				String attrName = ValueExtractor.checkForBoth(allAttrs.item(j)
						.getNodeName());
				String attrValue = ValueExtractor.checkForBoth(allAttrs.item(j)
						.getNodeValue());
				// if it isn't null and equals to the attribute, which is now
				// parsed, then save it into a certain Class
				if (attrName != null && attrName.equals(attrToParse)) {
					PropertyUtilsBean pub = new PropertyUtilsBean();
					try {
						pub.setProperty(targetElement, attrName, attrValue);
					} catch (IllegalAccessException | InvocationTargetException
							| NoSuchMethodException e) {
						e.printStackTrace();
					}
					break theLoop;
				}
			}
		}
	}

	/**
	 * Parses a {@link Property} from {@propertyNode} and saves it into
	 * {@targetClass}.
	 * 
	 * @param propertyNode
	 * @param targetClass
	 */
	private void parseProperty(Node propertyNode, Class targetClass,
			boolean ownedEnd) {
		NamedNodeMap allAttrs = propertyNode.getAttributes();
		String[] listedAttrs = NamesContainer.ATTRS_TAG_OWNED_END_ATTRIBUTE;
		Property property = new Property();

		parseNodeXMIAttrs(propertyNode, property);
		if (property != null) {
			parseListedAttrs(listedAttrs, allAttrs, property);
			if (property.getAggregation().equals(
					Property.COMPOSITE_AGGREGATION_STRING)) {
				property.setMultiplicity(new Multiplicity(true));
			} else {
				parsePropertyMultiplicity(propertyNode, property);
			}
			if (property.getAssociation() != null) {
				Association association = (Association) container
						.getRelations().get(property.getAssociation());
				OwnedEnd oe;
				if (ownedEnd) {
					oe = new OwnedEnd(property, !ownedEnd);
				} else {
					oe = new OwnedEnd(property, !ownedEnd);
				}
				association.addOwnedEnd(oe);
				return;
			}
			if (targetClass != null) {
				List<Property> properties = targetClass.getProperties();
				if (properties == null) {
					properties = new ArrayList<>();
				}
				properties.add(property);
				targetClass.setProperties(properties);
			}
		}
	}

	/**
	 * Parses a {@link Multiplicity} of {@code PropertyNode}.
	 * 
	 * @param propertyNode
	 * @param property
	 */
	private void parsePropertyMultiplicity(Node propertyNode, Property property) {
		NodeList childNodes = propertyNode.getChildNodes();
		Multiplicity multi = new Multiplicity();
		boolean lower = false;
		boolean upper = false;

		for (int i = 0; i < childNodes.getLength(); i++) {
			String nodeName = ValueExtractor.checkForBoth(childNodes.item(i)
					.getNodeName());
			if (nodeName != null) {
				NamedNodeMap attrs = childNodes.item(i).getAttributes();
				if (attrs != null) {
					for (int j = 0; j < attrs.getLength(); j++) {
						String attrName = ValueExtractor.checkForBoth(attrs
								.item(j).getNodeName());
						if (attrName
								.equals(NamesContainer.ATTRS_TAG_LOWER_UPPER_VALUE[0])) {
							if (nodeName.equals(NamesContainer.TAG_LOWER_VALUE)) {
								// if a lower multiplicity
								lower = true;
								try {
									multi.setLower(attrs.item(j).getNodeValue());
								} catch (DOMException
										| NotValidMultiplicityException e) {
									e.printStackTrace();
								}
							} else if (nodeName
									.equals(NamesContainer.TAG_UPPER_VALUE)) {
								// otherwise
								upper = true;
								try {
									multi.setUpper(attrs.item(j).getNodeValue());
								} catch (DOMException
										| NotValidMultiplicityException e) {
									e.printStackTrace();
								}
							}

						}
					}
				} else {
					if (lower) {
						try {
							multi.setLower(String
									.valueOf(Multiplicity.DEFAULT_LOWER_MULTIPLICITY));
						} catch (NotValidMultiplicityException e) {
							e.printStackTrace();
						}
					} else if (upper) {
						try {
							multi.setUpper(String
									.valueOf(Multiplicity.DEFAULT_UPPER_MULTIPLICITY));
						} catch (NotValidMultiplicityException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (multi.getLower() == null) {
			try {
				multi.setLower(String
						.valueOf(Multiplicity.DEFAULT_LOWER_MULTIPLICITY));
			} catch (NotValidMultiplicityException e) {
				e.printStackTrace();
			}
		}
		if (multi.getUpper() == null) {
			try {
				multi.setUpper(String
						.valueOf(Multiplicity.DEFAULT_UPPER_MULTIPLICITY));
			} catch (NotValidMultiplicityException e) {
				e.printStackTrace();
			}
		}
		property.setMultiplicity(multi);
	}

	private void parseNodeXMIAttrs(Node node,
			ua.kharkiv.knure.dimploma.containers.Element targetElement) {
		String nodeName = ValueExtractor.checkForBoth(node.getNodeName());
		if (nodeName != null) {
			if (nodeName.equals(NamesContainer.TAG_OWNED_ATTRIBUTE)
					|| nodeName.equals(NamesContainer.TAG_OWNED_END)) {
				parsePropertyXMIAttrs(node, targetElement);
			} else {
				parseXMIAttrs(node, targetElement);
			}
		}
	}

	private void parsePropertyXMIAttrs(Node node,
			ua.kharkiv.knure.dimploma.containers.Element targetElement) {
		Property p = (Property) targetElement;

		parseXMIAttrs(node, p);
		if (p.getXmiType() == null) {
			p.setXmiType(NamesContainer.PROPERTY_TAG_OWNED_ATTRIBUTE_END);
		} else if (!p.getXmiType().equals(
				NamesContainer.PROPERTY_TAG_OWNED_ATTRIBUTE_END)) {
			p = null;
		}
		targetElement = p;
	}

	private void parseXMIAttrs(Node node,
			ua.kharkiv.knure.dimploma.containers.Element targetElement) {
		NamedNodeMap attrs = node.getAttributes();

		if (attrs != null) {
			Node xmiIDNode = attrs.getNamedItem(getXMINSCol()
					+ NamesContainer.ATTR_XMI_ID);
			Node xmiTypeNode = attrs.getNamedItem(getXMINSCol()
					+ NamesContainer.ATTR_XMI_TYPE);

			if (xmiIDNode != null) {
				String xmiID = ValueExtractor.checkForBoth(xmiIDNode
						.getNodeValue());
				targetElement.setXmiID(xmiID);
			}
			if (xmiTypeNode != null) {
				String xmiType = ValueExtractor.checkForBoth(xmiTypeNode
						.getNodeValue());
				targetElement.setXmiType(xmiType);
			}
		}
	}
}