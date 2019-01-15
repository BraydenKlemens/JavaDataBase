package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * Catalog of all the courses in a University
 * */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CourseCatalog implements Serializable 
{

	private ArrayList<Course> catalog;
	private ArrayList<Course> preReqs;

	public CourseCatalog()
	{
		catalog = new ArrayList<>();
		preReqs = new ArrayList<>();
	}

	/**loads all courses into an array list from a file
	 * @param filename 
	 */
	public void initializeCatalog(String filename) 
	{
		String oneLine = "";
		String[] tokens;
		String[] pre;
		Course fillCourse = new Course();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			oneLine = reader.readLine();
			
			//keep getting the next line
			while (reader != null && oneLine != null) {
				Course newCourse = new Course();
				tokens = oneLine.split(",");
				
				//update information in the new course
				newCourse.setCourseCode(tokens[0]);
				newCourse.setCourseCredit(Double.parseDouble(tokens[1]));
				newCourse.setCourseTitle(tokens[2]);
				
				//if it has a prereq list
				if (tokens.length > 3) {
					pre = tokens[3].split(":");
					for (int i = 0; i < pre.length; i++) {
						preReqs.add(new Course(pre[i]));
					}
					newCourse.setPrerequisites(preReqs);
				}
				//add course, reset
				catalog.add(newCourse);
				oneLine = reader.readLine();
				preReqs.clear();
			}
			reader.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}

		// fill the preRequiste lists in the catalog with their appropriate information
		for (int i = 0; i < catalog.size(); i++) {
			for (int j = 0; j < catalog.get(i).getPrerequisites().size(); j++) {
				fillCourse = findCourse(catalog.get(i).getPrerequisites().get(j).getCourseCode());
				if (fillCourse != null) {
					preReqs.add(fillCourse);
				} 
				else {
					preReqs.add(new Course());
				}
			}
			catalog.get(i).setPrerequisites(preReqs);
			preReqs.clear();
		}
	}

	/**adds a course to the catalog
	 * @param toAdd
	 */
	public void addCourse(Course toAdd) 
	{
		if (toAdd != null) {
			catalog.add(toAdd);
		}
	}

	
	/**removes a course from the catalog if codes match
	 * @param toRemove
	 */
	public void removeCourse(Course toRemove) 
	{
		if (toRemove != null) {
			for (Course course : catalog) {
				if (course.getCourseCode().equals(toRemove.getCourseCode())) {
					catalog.remove(toRemove);
				}
			}
		}
	}

	/**
	 * saves the state of the catalog to a file
	 */
	public void saveCatalog() 
	{
		
		try {
			ObjectOutputStream s = new ObjectOutputStream(
					new FileOutputStream("catalog.sav"));
			s.writeObject(this);
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**finds a course in the catalog
	 * @param courseCode
	 * @return
	 */
	public Course findCourse(String courseCode) 
	{

		Course foundCourse = null;

		for (Course course : catalog) {
			if (course.getCourseCode().equals(courseCode)) {
				foundCourse = course;
			}
		}

		return foundCourse;
	}

	/**
	 * @return
	 */
	public ArrayList<Course> getCatalog() 
	{
		return catalog;
	}

	/**sets the catalog list to the passed
	 * @param courses
	 */
	public void setCatalog(ArrayList<Course> courses) 
	{
		this.catalog = courses;
	}
	
	/**check if catalog is empty
	 * @return
	 */
	public boolean isEmpty() 
	{
		if(catalog.size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String toString() 
	{
		String string = "";
		for (Course course : catalog) {
			string += course.toString();
		}
		return string;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (obj == this) {
			return true;
		}
		return false;
	}
}
