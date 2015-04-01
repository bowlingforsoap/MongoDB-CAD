package ua.kharkiv.mongorigami.Evaluation.classGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;

import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Object;
import ua.kharkiv.knure.dimploma.containers.Property;
import ua.kharkiv.knure.dimploma.final_structure.BoundObjects;
import ua.kharkiv.knure.dimploma.final_structure.LinkedObjects;
import ua.kharkiv.knure.dimploma.final_structure.MergedObjects;

import com.sun.codemodel.*;

/**
 * This class is responsible for the generation of classes from <b>{@link BoundObjects}</b>.
 * @author Vadym
 *
 */
public class ClassGenerator {
	/**
	 * Container for the generated classes.
	 */
	protected List<JDefinedClass> classes = new LinkedList<>();
	
	/**
	 * Generates classes from {@link LinkedObjects}. 
	 * @throws JClassAlreadyExistsException 
	 */
	public static void generateClassesFromLinkedObjects(LinkedObjects lo, String packageName) throws JClassAlreadyExistsException {
		JCodeModel codeModel = new JCodeModel();
		Class class1 = (Class) lo.getObj1();
		LinkedList<Property> class1Properties = (LinkedList<Property>) class1.getProperties();
		Class class2 = (Class) lo.getObj2();
		LinkedList<Property> class2Properties = (LinkedList<Property>) class2.getProperties();
		JDefinedClass definedClass1 = codeModel._class(packageName + class1.getName());
		JDefinedClass definedClass2 = codeModel._class(packageName + class2.getName());
		
	}
	
	/**
	 * Generates classes from {@link MergedObjects}. 
	 */
	public static void generateClassesFromMergedObjects(MergedObjects mo, String packageName) {
		
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface Link {
		String object();
		String field();
	}
}
