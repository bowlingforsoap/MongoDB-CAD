package ua.kharkiv.knure.dimploma.final_structure;

/**
 * This class is used to specify the common features of the BoundObjects class.
 * Also, being an instance of descendants of this class, means that this part of
 * a Class Diagram is already interpret.
 * 
 * @author Vadym
 * 
 */
public abstract class Interpreted {
	public final String UUID;

	{
		UUID = java.util.UUID.randomUUID().toString();
	}
}
