package ua.kharkiv.knure.dimploma.interpreter.utils.states;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ua.kharkiv.knure.dimploma.interpreter.utils.states.association_state.AssociationStateContainer;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state.HierarchyStateContainer;

public class StateToXSD {
	public static final String DEFAULT_LOCATION = "C:\\Users\\Vadym\\workspaces\\HPKepler\\Diploma pre0.1\\xsd\\StateValues.xsd";
	
	public static void transform(String xsd) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			List<State[]> stateGroups = new ArrayList<>();
			List<String> stateNames = new ArrayList<>();
			stateGroups.add(AssociationStateContainer.getState());
			stateNames.add("associationStateEnum");
			stateGroups.add(HierarchyStateContainer.getState());
			stateNames.add("hierarchyStateEnum");

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("schema");
			doc.appendChild(rootElement);
			rootElement.setAttribute("xmlns",
					"http://www.w3.org/2001/XMLSchema");
			rootElement.setAttribute("targetNamespace",
					"http://www.example.org/StateValues");
			rootElement.setAttribute("xmlns:tns",
					"http://www.example.org/StateValues");

			for (int i = 0; i < stateGroups.size(); i++) {
				Element stateEnum = doc.createElement("simpleType");
				rootElement.appendChild(stateEnum);
				stateEnum.setAttribute("name", stateNames.get(i));
				Element restrictionStateEnum = doc.createElement("restriction");
				stateEnum.appendChild(restrictionStateEnum);
				restrictionStateEnum.setAttribute("base", "string");
				for (State state : stateGroups.get(i)) {
					Element stateElement = doc.createElement("enumeration");
					stateElement.setAttribute("value", state.name);
					restrictionStateEnum.appendChild(stateElement);
				}
			}
			try {
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(xsd));

				transformer.transform(source, result);
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		transform(StateToXSD.DEFAULT_LOCATION);
	}
}
