package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * information for a course is held here
 * */
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable 
{

	private String courseCode, courseTitle, courseStatus, grade, semester,application;
	private double credit;
	private ArrayList<Course> preReqList;

	/**
	 * @param courseCode
	 * @param credit
	 * @param courseTitle
	 * @param courseStatus
	 * @param grade
	 * @param semester
	 * @param preReqList
	 */
	public Course(String courseCode, double credit, String courseTitle, String courseStatus, String grade,
			String semester, ArrayList<Course> preReqList) 
	{
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
		this.courseStatus = courseStatus;
		this.grade = grade;
		this.semester = semester;
		this.credit = credit;
		this.preReqList = preReqList;
	}

	/**
	 * empty constructor
	 */
	public Course() 
	{
		courseCode = "NA";
		courseTitle = "NA";
		courseStatus = "NA";
		grade = "NA";
		semester = "NA";
		application = "NA";
		credit = 0;
		preReqList = new ArrayList<>();
	}

	/**
	 * @param courseCode
	 */
	public Course(String courseCode) 
	{
		this.courseCode = courseCode;
		courseTitle = "NA";
		courseStatus = "NA";
		application = "NA";
		grade = "NA";
		semester = "NA";
		credit = 0;
		preReqList = new ArrayList<>();
	}

	/**copy constructor makes a deep copy of the informartion
	 * @param course
	 */
	public Course(Course course) 
	{

		this.courseCode = course.getCourseCode();
		this.courseTitle = course.getCourseTitle();
		this.courseStatus = course.getCourseStatus();
		this.grade = course.getCourseGrade();
		this.semester = course.getSemesterTaken();
		this.credit = course.getCourseCredit();
		this.application = course.getApplication();
		this.preReqList = course.getPrerequisites();
	}

	/**
	 * @param courseCode
	 * @param semester
	 */
	public Course(String courseCode, String semester)
	{
		this.courseCode = courseCode;
		this.semester = semester;
		courseTitle = "NA";
		courseStatus = "NA";
		application = "NA";
		grade = "NA";
		credit = 0;
		preReqList = new ArrayList<>();
	}
	
	/**
	 * @param courseCode
	 * @param semester
	 * @param application
	 */
	public Course(String courseCode, String semester, String application) 
	{
		this.courseCode = courseCode;
		this.semester = semester;
		this.application = application;
		courseTitle = "NA";
		courseStatus = "NA";
		grade = "NA";
		credit = 0;
		preReqList = new ArrayList<>();
	}
	
	/**
	 * @return
	 */
	public String getApplication() 
	{
		return application;
	}
	
	/**
	 * @param application
	 */
	public void setApplication(String application) 
	{
		this.application = application;
	}

	/**
	 * @return
	 */
	public String getCourseCode() 
	{
		return courseCode;
	}

	/**
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) 
	{
		this.courseCode = courseCode;
	}

	/**
	 * @return
	 */
	public String getCourseTitle() 
	{
		return courseTitle;
	}

	/**
	 * @param courseTitle
	 */
	public void setCourseTitle(String courseTitle)
	{
		this.courseTitle = courseTitle;
	}

	/**
	 * @return
	 */
	public double getCourseCredit() 
	{
		return credit;
	}

	/**
	 * @param credit
	 */
	public void setCourseCredit(double credit) 
	{
		this.credit = credit;
	}

	/**
	 * @return
	 */
	public ArrayList<Course> getPrerequisites() 
	{
		return preReqList;
	}

	/**
	 * @param preReqList
	 */
	public void setPrerequisites(ArrayList<Course> preReqList) 
	{
		this.preReqList = new ArrayList<Course>(preReqList);
	}

	/**
	 * @param courseStatus
	 */
	public void setCourseStatus(String courseStatus) 
	{
		this.courseStatus = courseStatus;
	}

	/**
	 * @return
	 */
	public String getCourseStatus() 
	{
		return courseStatus;
	}

	/**
	 * @param grade
	 */
	public void setCourseGrade(String grade) 
	{
		this.grade = grade;
	}

	/**
	 * @return
	 */
	public String getCourseGrade() 
	{
		return grade;
	}

	/**
	 * @param semester
	 */
	public void setSemesterTaken(String semester)
	{
		this.semester = semester;
	}

	/**
	 * @return
	 */
	public String getSemesterTaken() 
	{
		return semester;
	}

	/**prints the preRequisite courses in the course object
	 * @return
	 */
	public String printPreReq() 
	{
		String string = "";
		for (Course course : preReqList) {
			if (preReqList.size() >= 2) {
				string += course.getCourseCode() + ":";
			} 
			else {
				string += course.getCourseCode();
			}
		}
		return string;
	}

	@Override
	public String toString() 
	{
		return courseCode + "," + credit + "," + courseTitle + "," + courseStatus + "," + grade + "," + semester + ","
				+ printPreReq() + "\n";
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
