package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * Child of the Honours Degree, grand child of Degree impments its own version of meeting the requirments
 * */
public class CS extends HonoursDegree
{

	/**
	 * @param title
	 * @param catalog
	 */
	public CS(String title,CourseCatalog catalog) 
	{
		super(title,catalog);
		this.setDegreeTitle("CS");
	}
	
	@Override
	public boolean meetsRequirements(PlanOfStudy thePlan) 
	{
		double creditsNeeded = 0;
		double have = 0;
		
		/*goes through the required courses for a program and adds the credits to a total
		 * */
		if(thePlan.getDegreeProgram().equals("CS")) {
			for(int i = 0; i < thePlan.getDegreeProgram().getRequiredCourses().size(); i++) {
				creditsNeeded += thePlan.getDegreeProgram().getRequiredCourses().get(i).getCourseCredit();
			}
		}
		else {
			return false;
		}
		
		/*goes through the plan of study course list, running total of credits completed
		 * */
		for(int i = 0; i < thePlan.getPlan().size();i++) {
			if(thePlan.getPlan().get(i).getCourseStatus().equals("Complete")) {
				if(thePlan.getDegreeProgram().checkReqCourse(thePlan.getPlan().get(i))) {
					have += thePlan.getPlan().get(i).getCourseCredit();		
				}	
			}
		}
		//if the credits have are equal to what we need, then degree is complete
		if(creditsNeeded == have) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public String toString()
	{
		return getClass().getName()+"@"+Integer.toHexString(hashCode());
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
