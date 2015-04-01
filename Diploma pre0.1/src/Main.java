import java.util.List;

import com.mongodb.BasicDBObject;

import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.interpreter.Interpreter;
import ua.kharkiv.knure.dimploma.interpreter.utils.Rule;
import ua.kharkiv.knure.dimploma.interpreter.utils.RuleInterpreter;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.AssociationState;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.HierarchyState;
import ua.kharkiv.knure.dimploma.parser.DOMParser;

public class Main {
	public static void main(String[] args) {
		try {
			DOMParser parser = new DOMParser("V:\\software\\GitRepository\\Diploma pre0.1\\xmi\\model.uml", "xmi");
			Container container = parser.parse();
			for (ua.kharkiv.knure.dimploma.containers.Object object : container.getObjects().values()) {
				System.out.println(ua.kharkiv.knure.dimploma.final_structure.utils.Util.objectToBasicDBObject(object));
			}
			
			List<Rule<HierarchyState>> hierarchyRules = RuleInterpreter.interpretHierarchyRules(null);
			List<Rule<AssociationState>> associationRules = RuleInterpreter.interpretAssociationRules(null);
			
			Interpreter i = new Interpreter(container, hierarchyRules, associationRules);
			List<List<BasicDBObject>> iterpretedStructureTree = i.interpret();
			Interpreter.print(iterpretedStructureTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
