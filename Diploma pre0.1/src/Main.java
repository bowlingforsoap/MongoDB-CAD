import java.io.File;
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
			DOMParser parser = new DOMParser(args[0], "xmi");
			Container container = parser.parse();
			
			List<Rule<HierarchyState>> hierarchyRules = RuleInterpreter.interpretHierarchyRules(args[1]);
			List<Rule<AssociationState>> associationRules = RuleInterpreter.interpretAssociationRules(args[1]);
			
			Interpreter i = new Interpreter(container, hierarchyRules, associationRules);
			List<List<BasicDBObject>> iterpretedStructureTree = i.interpret();
			Interpreter.print(iterpretedStructureTree);
		} catch (Exception e) {
			
		}
	}
}
