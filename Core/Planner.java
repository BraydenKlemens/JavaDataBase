package A12430;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Planner {

	public static void main(String[] args) 
	{

		boolean cont = false;
		int option = 0;
		Scanner sc = new Scanner(System.in);
		ArrayList<Course> notInPos = new ArrayList<Course>();
		PlanOfStudy plan = new PlanOfStudy();
		
		//loads the bootstrapped information
		Univ univ = new Univ();
		univ = univ.loadDisk("univ.sav");
		
		/*
		 * each step is understandable as it uses the functions from other files
		 * */

		while (cont == false) {

			System.out.println(("Enter a number to perform one of the following options:\n\n"
					+ "1. From a CSV file, load a list of classes which I have taken or plan to take, along with the\n"
					+ "status of the course and my grade if available\n"
					+ "2. Select my degree and Select my major from my selected degree\n"
					+ "3. Add courses to my plan of study and mark them as in-progress, complete, or planned\n"
					+ "4. Mark courses in my plan of study as required, elective, or minor (area of application)\n"
					+ "5. Remove a course from my plan of study\n"
					+ "6. Change my grade in a course that is in my plan of study\n"
					+ "7. Save my plan of study to a file\n" + "8. Load my plan of study from a previously saved file\n"
					+ "9. View a list of required courses for my degree and major that are not represented in my\n"
					+ "plan of study\n"
					+ "10. View a list of the prerequisite courses for any required course for my degree and major\n"
					+ "11. View the number of credits I have completed in my plan of study\n"
					+ "12. View the number of credits I have remaining to complete my plan of study\n"
					+ "13. Determine if I have met the completion requirements of my chosen degree\n" + "14. Quit"));

			option = sc.nextInt();

			switch (option) {
			case 1:
				String filename;
				System.out.println("Enter the filename to be loaded: ");
				filename = sc.next();
				plan.importData(filename);
				System.out.println("Complete...\n\n");
				break;
			case 2:
				String selectDegree = "";
				String major = "";

				System.out.println("Choose Degree: Honours Degree (BCH), General Degree (BCG)");
				selectDegree = sc.next();
				if (selectDegree.equals("BCH")) {
					System.out.println("Enter a valid Major [CS] or [SENG]:");
					major = sc.next();
					if (major.equals("CS") || major.equals("SENG")) {
						plan.setDegreeProgram(univ.getDegree(major));
					} 
					else {
						break;
					}
				} 
				else if (selectDegree.equals("BCG")) {
					major = "BCG";
					plan.setDegreeProgram(univ.getDegree(major));
					break;
				} 
				else {
					break;
				}
				break;
			case 3:
				String code, semester, status;
				System.out.println("Enter a CourseCode:");
				code = sc.next();
				System.out.println("Enter a Semester:");
				semester = sc.next();

				System.out.println("Enter the status of this course: (InProgress), (Complete), (Planned)");
				status = sc.next();

				if (status.equals("InProgress") || status.equals("Complete") || status.equals("Planned")) {
					plan.addCourse(code, semester);
					plan.setCourseStatus(code, semester, status);
					System.out.println("Added Course..");
				} 
				else {
					System.out.println("Invalid Input...");
				}
				break;
			case 4:
				System.out.println("Enter a course code:");
				code = sc.next();
				System.out.println("Enter the semester this course was taken:");
				semester = sc.next();
				System.out.println("Enter the status of this course: (required), (elective), (minor)");
				status = sc.next();

				if (status.equals("required") || status.equals("elective") || status.equals("minor")) {
					plan.setCourseApplication(code, semester, status);
					System.out.println("Area of Application Changed...");
				} 
				else {
					System.out.println("Invalid Input...");
				}
				break;
			case 5:
				System.out.println("Enter a course code for the course you would like to remove:");
				code = sc.next();
				plan.removeCourse(code, " ");
				System.out.println("Removed Successfully...");
				break;
			case 6:
				String grade;
				System.out.println("Enter a course code:");
				code = sc.next();
				System.out.println("Enter the new grade for this course:");
				grade = sc.next();
				System.out.println("Enter the semester this course was taken:");
				semester = sc.next();
				plan.setCourseGrade(code, semester, grade);
				System.out.println("Grade Changed...");
				break;
			case 7:
				plan.saveState();
				System.out.println("Saved plan of Study to a file...\n");
				break;
			case 8:
				System.out.println("Enter the filename to be loaded: ");
				filename = sc.next();
				//load plan of study from a previous saved file plan.sav
				try {
					ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
					plan = (PlanOfStudy) inputStream.readObject();
					inputStream.close();
					System.out.println("Successfully Loaded Plan of Study\n");
				} 
				catch (Exception e) {
					System.out.println(e);
					System.exit(0);
				}
				System.out.println("Imported Data Successfully: \n");
				break;
			case 9:
				notInPos = plan.getDegreeProgram().notInPlan(plan);
				System.out.println("Required courses not in Plan Of Study: ");
				if (notInPos != null) {
					for (Course c : notInPos) {
						System.out.println(c.getCourseCode());
					}
				}
				break;
			case 10:
				System.out.println("Enter a course code: ");
				code = sc.next();
				ArrayList <Course> f = new ArrayList<>();

				/*
				 * go through required courses of degree
				 * if finds code, get the preReq list
				 * */
				for (int i = 0; i < plan.getDegreeProgram().getRequiredCourses().size(); i++) {
					if (code.equals(plan.getDegreeProgram().getRequiredCourses().get(i).getCourseCode())) {
						f = plan.getDegreeProgram().getRequiredCourses().get(i).getPrerequisites();
					}
				}
				/*
				 * get the course codes of the preReq List and print
				 * */
				System.out.println("Prerequisites for " + code + ":");
				for(int i = 0; i < f.size();i++) {
					System.out.println(f.get(i).getCourseCode());
				}
				
				break;
			case 11:
				double credit = 0;
				credit = plan.getDegreeProgram().completeCourseCredits(plan);
				System.out.println("Complete Credits: " + credit);
				break;
			case 12:
				credit = plan.getDegreeProgram().numberOfCreditsRemaining(plan);
				System.out.println("Remaining credits to complete plan of study: " + credit);
				break;
			case 13:
				if (plan.getDegreeProgram().meetsRequirements(plan)) {
					System.out.println("Degree Complete: Requirments have been met");
				} 
				else {
					System.out.println("Degree Incomplete");
				}
				break;
			case 14:
				System.out.println("DONE");
				cont = true;
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}

		}
	}
}
