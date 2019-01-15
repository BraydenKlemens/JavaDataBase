package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * Holds a list of courses the student has taken or plan to take to complete thier degree
 * */

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class PlanOfStudy implements Serializable 
{
	private Degree degree;
	private ArrayList<Course> plan;
	private CourseCatalog catalog;

	/**
	 * empty constructor
	 */
	public PlanOfStudy() 
	{
		plan = new ArrayList<>();
		catalog = new CourseCatalog();
		catalog.initializeCatalog("courselist.csv");
	}

	/**
	 * @param deg
	 */
	public void setDegreeProgram(Degree deg) 
	{
		this.degree = deg;
	}

	/**
	 * @return
	 */
	public Degree getDegreeProgram() 
	{
		return degree;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Course> getPlan() 
	{
		return plan;
	}

	/**
	 * @param plan
	 */
	public void setPlan(ArrayList<Course> plan) 
	{
		this.plan = new ArrayList<Course>(plan);
	}

	/**
	 * @param filename
	 */
	public void importData(String filename) 
	{

		try {
			String oneLine = "";
			String[] tokens;

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			oneLine = reader.readLine();
			//keep reading lines
			while (reader != null && oneLine != null) {
				
				//split lines into an array of tokens
				Course course = new Course();
				tokens = oneLine.split(",");
				
				//set the data from the tokens to the course
				course = catalog.findCourse(tokens[0]);
				course.setCourseStatus(tokens[1]);
				course.setCourseGrade(tokens[2]);
				course.setSemesterTaken(tokens[3]);
				plan.add(course);
				oneLine = reader.readLine();
			}
			reader.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * saves the current state of the PlanOfStudy to a file
	 */
	public void saveState()
	{

		try {
			ObjectOutputStream s = new ObjectOutputStream(
					new FileOutputStream("plan.sav"));
			s.writeObject(this);
			s.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**adds course to plan
	 * @param courseCode
	 * @param semester
	 */
	public void addCourse(String courseCode, String semester)
	{	
		//if the course to be added is in the catalog then add it to the plan
		Course newCourse = new Course();
		if (catalog.findCourse(courseCode) != null) {
			newCourse = catalog.findCourse(courseCode);
			newCourse.setSemesterTaken(semester);
			plan.add(newCourse);
		} 
		else {
			//make a new course by what they enetered
			plan.add(new Course(courseCode, semester));
		}
	}

	/**removes a course from plan
	 * @param courseCode
	 * @param semester
	 */
	public void removeCourse(String courseCode, String semester)
	{
		//removes a course if the course code equal
		Course course = getCourse(courseCode,semester);
		if(course != null) {
			plan.remove(course);
		}
			
	}

	/**if the course code matches one thats in the plan then update its information
	 * @param courseCode
	 * @param semester
	 * @param courseStatus
	 */
	public void setCourseStatus(String courseCode, String semester, String courseStatus) 
	{
		//if the course code matches one thats in the plan then update its information
		Course course = getCourse(courseCode,semester);
		
		if(course != null) {
			course.setSemesterTaken(semester);
			course.setCourseStatus(courseStatus);
		}
		
	}

	/**if the course code matches one thats in the plan then update its information
	 * @param courseCode
	 * @param semester
	 * @param grade
	 */
	public void setCourseGrade(String courseCode, String semester, String grade) 
	{
		//if the course code matches one thats in the plan then update its information
		Course course = getCourse(courseCode,semester);
		
		if(course != null) {
			course.setSemesterTaken(semester);
			course.setCourseGrade(grade);
		}
	}

	/**if the course code matches one thats in the plan then return it
	 * @param courseCode
	 * @param semester
	 * @return
	 */
	public Course getCourse(String courseCode, String semester) 
	{
		//if the course code matches one thats in the plan then return it
		for (Course course : plan) {
			if (course.getCourseCode().equals(courseCode)) {
				return course;
			}
		}
		return null;
	}
	
	/**if the course code matches one thats in the plan then update its information
	 * @param courseCode
	 * @param semester
	 * @param status
	 */
	public void setCourseApplication(String courseCode, String semester, String status) 
	{
		//if the course code matches one thats in the plan then update its information
		Course course = getCourse(courseCode,semester);
		
		if(course != null) {
			course.setSemesterTaken(semester);
			course.setApplication(status);
		}
	}
	
	@Override
	public String toString() 
	{
		String string = "";
		for(Course course: plan) {
			string += course.getCourseCode() + "," + course.getCourseStatus() + "," + course.getCourseGrade() + "," + course.getSemesterTaken() + "\n";
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
