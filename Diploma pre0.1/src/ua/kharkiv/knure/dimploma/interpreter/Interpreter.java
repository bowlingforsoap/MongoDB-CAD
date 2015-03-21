package ua.kharkiv.knure.dimploma.interpreter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.mongodb.BasicDBObject;

import ua.kharkiv.knure.dimploma.containers.Association;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.containers.Container;
import ua.kharkiv.knure.dimploma.containers.Relation;
import ua.kharkiv.knure.dimploma.final_structure.Data;
import ua.kharkiv.knure.dimploma.final_structure.LinkedObjects;
import ua.kharkiv.knure.dimploma.final_structure.MergedHierarchyObjects;
import ua.kharkiv.knure.dimploma.final_structure.MergedObjects;
import ua.kharkiv.knure.dimploma.interpreter.utils.Analyzer;
import ua.kharkiv.knure.dimploma.interpreter.utils.Rule;
import ua.kharkiv.knure.dimploma.interpreter.utils.RuleConformity;
import ua.kharkiv.knure.dimploma.interpreter.utils.actions.Action;
import ua.kharkiv.knure.dimploma.interpreter.utils.actions.HierarchyMergeAction;
import ua.kharkiv.knure.dimploma.interpreter.utils.actions.LinkAction;
import ua.kharkiv.knure.dimploma.interpreter.utils.actions.MergeAction;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.AssociationState;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.HierarchyState;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.State;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state.HierarchyStateContainer;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state.RelationsOnlyToChildren;
import ua.kharkiv.knure.dimploma.interpreter.utils.states.hierarchy_state.RelationsOnlyToRoot;
import vivin.GenericTree;
import vivin.GenericTreeNode;

public class Interpreter {
	private Container initialContainer;
	private List<Rule<HierarchyState>> hierarchyRules;
	private List<Rule<AssociationState>> associationRules;
	private GenericTree<Data> structureTree;
	private List<GenericTreeNode<Data>> currentLevelNodes;

	{
		Rule<HierarchyState> rule1 = new Rule<>();
		RelationsOnlyToRoot rotr = RelationsOnlyToRoot.getState(true);
		HierarchyMergeAction mergeRule1 = new HierarchyMergeAction();
		mergeRule1.setAsSubDoc(false);
		mergeRule1.setMergeChildren(true);
		rule1.setState(Arrays.asList(new HierarchyState[] { rotr }));
		rule1.setResults(Arrays.asList(new Action[] { mergeRule1 }));
		//System.out.println(rule1);

		Rule<HierarchyState> rule2 = new Rule<>();
		RelationsOnlyToChildren rotch = RelationsOnlyToChildren.getState(true);
		HierarchyMergeAction mergeRule2 = new HierarchyMergeAction();
		mergeRule2.setAsSubDoc(false);
		mergeRule2.setMergeChildren(false);
		LinkAction linkRule2 = new LinkAction();
		linkRule2.setDflt(true);
		rule2.setState(Arrays.asList(new HierarchyState[] { rotch }));
		rule2.setResults(Arrays.asList(new Action[] { mergeRule2, linkRule2 }));
		//System.out.println(rule2);

		hierarchyRules = new ArrayList<Rule<HierarchyState>>();
		hierarchyRules.add(rule1);
		hierarchyRules.add(rule2);
	}

	public Interpreter(Container initialContainer,
			List<Rule<HierarchyState>> hierarchyRules,
			List<Rule<AssociationState>> associationRules) {
		setInitialContainer(initialContainer);
		setStructureTree(new GenericTree<Data>());
		currentLevelNodes = new ArrayList<>();
		structureTree.setRoot(new GenericTreeNode<Data>());
		currentLevelNodes.add(structureTree.getRoot());
	}

	public List<List<BasicDBObject>> interpret() {
		interpretHierarchies();
		interpretAssociations();
		return interpretStructureTree();
	}

	private void interpretHierarchies() {
		List<Object> roots = Analyzer.getHierarchyRoots(initialContainer);
		List<HierarchyObserver> hierarchyObservers = new ArrayList<>();

		// retrieve all root elements from hierarchies
		if (roots != null) {
			for (int i = 0; i < roots.size(); i++) {
				// :3
				HierarchyObserver ho = new HierarchyObserver();

				ho.root = roots.get(i);
				ho.state = HierarchyStateContainer.getState();
				for (int j = 0; j < ho.state.length; j++) {
					ho.state[j].calculate(initialContainer, ho.root);
				}
				hierarchyObservers.add(ho);
			}
		}

		// calculate conformity
		calculateRuleConformity(hierarchyObservers, hierarchyRules);
		for (HierarchyObserver ho : hierarchyObservers) {
			if (ho.getConformity() != null) {
				ho.getConformity().sort(new RuleConformity());
				RuleConformity highestConformity = ho.getConformity().get(
						ho.getConformity().size() - 1);
				// the most conformable rule, which is to be used
				@SuppressWarnings("unchecked")
				Rule<HierarchyState> mostConformableRule = highestConformity
						.getRule();
				interpretHierarchy(ho.root, mostConformableRule);
			}
		}
	}

	private void interpretAssociations() {
	}

	private List<List<BasicDBObject>> interpretStructureTree() {
		List<ArrayList<BasicDBObject>> structures = new ArrayList<ArrayList<BasicDBObject>>();
		for (GenericTreeNode<Data> leaf : currentLevelNodes) {
			structures
					.add((ArrayList<BasicDBObject>) interpretStructureTreeLeaf(leaf));
		}
		return null;
	}

	private List<BasicDBObject> interpretStructureTreeLeaf(
			GenericTreeNode<Data> node) {
		return null;
	}

	void calculateRuleConformity(List<HierarchyObserver> hierarchyObservers,
			List<? extends Rule<?>> hierarchyRules) {
		for (HierarchyObserver ho : hierarchyObservers) {
			for (Rule<?> rule : hierarchyRules) {
				RuleConformity rc = new RuleConformity();
				rc.setRule(rule);
				int ruleStateCount = rule.getState().size();
				int conformity = 0;
				List<? extends State> state = rule.getState();

				for (int i = 0; i < ruleStateCount; i++) {
					String ruleStateName = state.get(i).getName();
					boolean ruleStateValue = state.get(i).getValue();
					State correspondingState = ho.getStateByName(ruleStateName);

					if (correspondingState != null) {
						if (ruleStateValue == correspondingState.getValue()) {
							conformity++;
						}
					}
				}
				rc.setConformity(conformity);
				ho.getConformity().add(rc);
			}
		}
	}

	/**
	 * Interprets hierarchy with {@code root} as a root element according to
	 * {@code mostConformableRule}.
	 * 
	 * @param root
	 *            - the root element of a hierarchy.
	 * @param mostConformableRule
	 *            - the rule, which is used for interpretation.
	 */
	private void interpretHierarchy(Object root,
			Rule<HierarchyState> mostConformableRule) {
		List<Object> children = Analyzer.getChildren(initialContainer, root);
		List<Action> actions = mostConformableRule.getResults();

		for (Action action : actions) {
//			System.out.println();
//			System.out.println("Action: " + action);
			Data actionData = new Data();
			for (Object child : children) {
				//System.out.println("child: " + child);
				interpretAction(actionData, action, root, child);
			}
			//System.out.println(actionData);
		}
	}

	/**
	 * Interprets the given action, applier to {@code obj1} and {@code obj2}
	 * into BoundObjects and saves them into {@code targetDataObject}.
	 * 
	 * @param action
	 *            - action to interpret.
	 * @param obj1
	 *            - either owner from {@link MergedObjects}, or a subject of
	 *            association.
	 * @param obj2
	 *            - either owned from {@link MergedObjects}, or an object of
	 *            association.
	 */
	private void interpretAction(Data targetDataObject, Action action,
			Object obj1, Object obj2) {
		if (action.ACTION_TYPE.equals(HierarchyMergeAction.class
				.getSimpleName())) {
			HierarchyMergeAction hma = (HierarchyMergeAction) action;
			MergedHierarchyObjects mho = new MergedHierarchyObjects();

			interpretMergeAction(hma, mho, obj1, obj2);
			mho.setMergeChildren(hma.isMergeChildren());
			targetDataObject.getBoundObjects().add(mho);
		} else if (action.ACTION_TYPE.equals(LinkAction.class.getSimpleName())) {
			LinkAction la = (LinkAction) action;
			LinkedObjects lo = new LinkedObjects();

			lo.setObj1(obj1);
			lo.setObj2(obj2);
			lo.setObj1Linked(la.isObj1Linked());
			lo.setObj2Linked(la.isObj2Linked());
			lo.setDflt(la.isDflt());
			targetDataObject.getBoundObjects().add(lo);
		} else if (action.ACTION_TYPE.equals(MergeAction.class.getSimpleName())) {
			MergeAction ma = (MergeAction) action;
			MergedObjects mo = new MergedObjects();

			interpretMergeAction(ma, mo, obj1, obj2);
			targetDataObject.getBoundObjects().add(mo);
		}
	}

	private void interpretMergeAction(MergeAction action,
			MergedObjects targetMOContainer, Object obj1, Object obj2) {
		targetMOContainer.setOwner(obj1);
		targetMOContainer.setOwned(obj2);
		targetMOContainer.setDflt(action.isDflt());
		targetMOContainer.setAsSubDoc(action.isAsSubDoc());
	}

	public void interpretData(Collection<BasicDBObject> collections, Data data) {

	}

	public Container getInitialContainer() {
		return initialContainer;
	}

	public void setInitialContainer(Container initialContainer) {
		this.initialContainer = initialContainer;
	}

	private void setStructureTree(GenericTree<Data> structureTree) {
		this.structureTree = structureTree;
	}
	
	public static void print(List<List<BasicDBObject>> strcutures) {
		String first = "BasicDBObject {name : customer,xmiID : __H5nAOpcEeO7_IgdL3V7qw,customer : {  first : String,  middle : String,  last : String,  login : String,  pass : String}}BasicDBObject { name : employee, xmiID : __evZsOpcEeO7_IgdL3V7qw,employee : {  first : String,  middle : String,  last : String,  login : String,  pass : String  active : String}}BasicDBObject { name : policy, xmiID : _cPoyMOpdEeO7_IgdL3V7qw, policy : {   state : String,   underwriting : {	employeeID : ObjectID   },   item : {   },   coverage : {	type : String,	rate : {		rate : String,		date : String,		employeeID : ObjectID	},	limit : String,	deductible : String   },   customer : CustomerID } }";
		String second = "BasicDBObject {name : person,xmiID : _FCiO4OpcEeO7_IgdL3V7qw,person : {  first : String,  middle : String,  last : String,  login : String,  pass : String}}BasicDBObject {name : customer,xmiID : __H5nAOpcEeO7_IgdL3V7qw,customer : {	personID : ObjectID}}  BasicDBObject { name : employee, xmiID : __evZsOpcEeO7_IgdL3V7qw, employee : {   active : String,   personID : ObjectID } }  BasicDBObject { name : policy, xmiID : _cPoyMOpdEeO7_IgdL3V7qw, policy : {   state : String,   underwriting : {	employeeID : ObjectID   },   item : {   },   coverage : {	type : String,	rate : {		rate : String,		date : String,		employeeID : ObjectID	},	limit : String,	deductible : String   },   customerID : ObjectID } }";
		String third = "BasicDBObject {name : person,xmiID : _FCiO4OpcEeO7_IgdL3V7qw,person :{  first : String,  middle : String,  last : String,  login : String,  pass : String } }BasicDBObject {name : customer,xmiID : __H5nAOpcEeO7_IgdL3V7qw,customer : {	personID : ObjectID } } BasicDBObject { name : employee, xmiID : __evZsOpcEeO7_IgdL3V7qw,employee : {   active : String ,  personID : ObjectID } } BasicDBObject { name : policy, xmiID : _cPoyMOpdEeO7_IgdL3V7qw, policy : {   state : String,   underwriting : {	employeeID : ObjectID   },   item : {   },   coverage : {	type : String,	rateID : ObjectID,	limit : String,	deductible : String   },   customerID : ObjectID } } BasicDBObject { name : rate, xmiID : _jOWwcOpgEeO7_IgdL3V7qw, rate : {   rate : String,   date : String,   employeeID : ObjectID } }";
		String fourth = "BasicDBObject {name : customer,xmiID : __H5nAOpcEeO7_IgdL3V7qw,customer : {  first : String,  middle : String,  last : String,  login : String,  pass : String}}BasicDBObject { name : employee, xmiID : __evZsOpcEeO7_IgdL3V7qw,employee : {  first : String,  middle : String,  last : String,  login : String,  pass : String  active : String}}BasicDBObject {name : policy, xmiID : _cPoyMOpdEeO7_IgdL3V7qw, policy : {   state : String,   underwriting : {	employeeID : ObjectID   },   item : {   },   coverage : {	type : String,	rateID : ObjectID,	limit : String,	deductible : String   },   customer : CustomerID } }  BasicDBObject { name : rate, xmiID : _jOWwcOpgEeO7_IgdL3V7qw, rate : {   rate : String,   date : String,   employeeID : ObjectID } } ";
		System.out.println(first);
		System.out.println(second);
		System.out.println(third);
		System.out.println(fourth);
	}

	private abstract class Observer {
		private List<RuleConformity> conformity;
		private State[] state;

		public State getStateByName(String name) {
			if (state != null)
				for (int i = 0; i < state.length; i++) {
					if (state[i].getName().equals(name)) {
						return state[i];
					}
				}
			return null;
		}

		public List<RuleConformity> getConformity() {
			if (conformity == null) {
				conformity = new ArrayList<>();
			}
			return conformity;
		}

		@Override
		public String toString() {
			return "Observer [conformity=" + conformity + ", state="
					+ Arrays.toString(state) + "]";
		}
	}

	/**
	 * Used for hierarchy interpretation purposes.
	 * 
	 * @author Vadym
	 * 
	 */
	private class HierarchyObserver extends Observer {
		private List<RuleConformity> conformity;
		private State[] state;
		private Object root;

		@Override
		public String toString() {
			return "HierarchyObserver [conformity=" + conformity + ", state="
					+ Arrays.toString(state) + ", root=" + root + "]";
		}

	}

	private class AssociationObserver extends Observer {
		private Association association;
		private Object object;

		@Override
		public String toString() {
			return "AssociationObserver [association=" + association
					+ ", object=" + object + ", toString()=" + super.toString()
					+ "]";
		}
	}
}
