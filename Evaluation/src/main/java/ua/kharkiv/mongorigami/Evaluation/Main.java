package ua.kharkiv.mongorigami.Evaluation;

import java.util.Arrays;

import ua.kharkiv.knure.dimploma.containers.Class;
import ua.kharkiv.knure.dimploma.containers.Property;
import ua.kharkiv.knure.dimploma.final_structure.LinkedObjects;

public class Main {
	public static void main(String[] args) {
		Class person = new Class();
		person.setName("Person");
		Property name = new Property();
		name.setName("Name");
		name.setType("String");
		person.setProperties(Arrays.asList(name));
		Class job = new Class();
		job.setName("Job");
		Property job_name = new Property();
		job_name.setName("Job_name");
		job_name.setType("String");
		job.setProperties(Arrays.asList(job_name));
		LinkedObjects lo = new LinkedObjects();
		lo.setObj1(person);
		lo.setObj2(job);
		lo.setObj2Linked(true);
	}
}
