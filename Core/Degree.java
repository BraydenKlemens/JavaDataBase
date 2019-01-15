package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * information for a degree is held here, holds a list of required courses
 * */
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Degree implements Serializable 
{
	private String title;
	private CourseCatalog catalog;
	private ArrayList<Course> requiredCourses;

	/**
	 * @param title
	 * @param catalog
	 */
	public Degree(String title, CourseCatalog catalog) 
	{

		requiredCourses = new ArrayList<>();
		this.catalog = catalog;
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getDegreeTitle() 
	{
		return title;
	}

	/**
	 * @param title
	 */
	public void setDegreeTitle(String title) 
	{
		this.title = title;
	}

	/**
	 * @param listOfRequiredCourseCodes
	 */
	public void setRequiredCourses(ArrayList<String> listOfRequiredCourseCodes) 
	{
		//if the course exists (from list passed) in the catalog then add to required
		for (String code : listOfRequiredCourseCodes) {
			if (catalog.findCourse(code) != null) {
				requiredCourses.add(catalog.findCourse(code));
			} 
			else {
				requiredCourses.add(new Course());
			}
		}
	}

	/**
	 * @return
	 */
	public ArrayList<Course> getRequiredCourses() 
	{
		return requiredCourses;

	}
	
	/**
	 * @param plan
	 * @return
	 */
	public ArrayList<Course> notInPlan(PlanOfStudy plan)
	{
		ArrayList<Course> req = new ArrayList<>();
		ArrayList<Course> courses = new ArrayList<>();
		
		//gets the list data
		req = plan.getDegreeProgram().getRequiredCourses();
		courses = plan.getPlan();
		
		//loop through both checking every element, if they match then remove from required and return required
		//the returned required will be those without courses from the planOfStudy
		for(int i = 0; i < req.size();i++) {
			for(int j = 0; j < courses.size();j++) {
				if(courses.get(j).getCourseCode().equals(req.get(i).getCourseCode())) {
					req.remove(i);
				}
			}
		}
		return req;
	}

	/**
	 * @param thePlan
	 * @return
	 */
	public boolean meetsRequirements(PlanOfStudy thePlan) 
	{
		//meets requirments for the PlanOfStudy
		double credits = numberOfCreditsRemaining(thePlan);
		if(credits == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @param thePlan
	 * @return credits total from remaining courses
	 */
	public double numberOfCreditsRemaining(PlanOfStudy thePlan)
	{
		
		double credits = 0;
		ArrayList<Course> required = new ArrayList<>();
		//get the remaining courses list
		required = remainingRequiredCourses(thePlan);
		
		//if its empty - none
		if(required.size() == 0) {
			return 0;
		}
		else {
			//sum credits from remaining
			for(int i = 0; i < required.size(); i++) {
				credits += required.get(i).getCourseCredit();
			}
		}
		return credits;
	}

	/**
	 * @param thePlan
	 * @return required courses to complete in the plan of study
	 */
	public ArrayList<Course> remainingRequiredCourses(PlanOfStudy thePlan) 
	{
		
		ArrayList<Course> required = new ArrayList<>();
		
		//loops through plan list, if not complete add
		for(int i = 0; i < thePlan.getPlan().size();i++) {
			if(!(thePlan.getPlan().get(i).getCourseStatus().equals("Complete"))) {
				required.add(thePlan.getPlan().get(i));
			}
		}
		
		return required;
	}
	
	/**
	 * @param thePlan
	 * @return
	 */
	public double completeCourseCredits(PlanOfStudy thePlan) 
	{
		
		double credits = 0;
		//looop through plan list, if complete then total it
		for(int i = 0; i < thePlan.getPlan().size();i++) {
			if((thePlan.getPlan().get(i).getCourseStatus().equals("Complete"))) {
				credits += thePlan.getPlan().get(i).getCourseCredit();
			}
		}
		
		return credits;
	}
	
	/**
	 * @param course
	 * @return
	 */
	public boolean checkReqCourse(Course course) 
	{
		//check if its a required course code
		for(Course c: requiredCourses) {
			if(c.getCourseCode().equals(course.getCourseCode())) {
				return true;
			}
		}
		return false;
		
	}

	@Override
	public String toString() 
	{
		return this.title;
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
