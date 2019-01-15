package A12430;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * Sets up the information for University degrees and saves to disk
 * */
public class Univ implements Serializable 
{
	private CourseCatalog catalog;
	private ArrayList<Degree> degrees;
	private ArrayList<String> codes;

	/**
	 * empty constructor
	 */
	public Univ() 
	{
		degrees = new ArrayList<>();
		catalog = new CourseCatalog();
		codes = new ArrayList<>();
	}

	/**
	 * for testing, prints out the required courses for a degree
	 */
	public void print() 
	{
		for (Degree degree : degrees) {
			System.out.println("Degree: " + degree.getDegreeTitle());
			for (int i = 0; i < degree.getRequiredCourses().size(); i++) {
				System.out.print(degree.getRequiredCourses().get(i).getCourseCode());
			}
		}
	}
	
	/**gets the degree based on the name
	 * @param name
	 * @return
	 */
	public Degree getDegree(String name) 
	{
		for(Degree degree: degrees) {
			if(name.equals(degree.getDegreeTitle())) {
				return degree;
			}
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public ArrayList<Degree> getDegrees() 
	{
		return degrees;
	}

	/**
	 * @param degrees
	 */
	public void setDegrees(ArrayList<Degree> degrees) 
	{
		this.degrees = new ArrayList<Degree>(degrees);
	}

	/**
	 * @param filename
	 */
	public void loadCatalog(String filename) 
	{
		catalog.initializeCatalog(filename);
	}

	/**
	 * @param filename
	 */
	public void addNewDegree(String filename) 
	{

		try {
			String oneLine = "";
			String[] tokens;
			Degree temp;

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			oneLine = reader.readLine();
			
			//keep reading lines
			while (reader != null && oneLine != null) {
				tokens = oneLine.split(",");
				
				//add the course codes to an array list of codes
				for (int i = 1; i < tokens.length; i++) {
					codes.add(tokens[i]);
				}
				
				/*creates a new degree based on the information from the file
				 * gives it required course codes
				 * sets the title to the first token
				 * */
				if(tokens[0].equals("BCG")) {
					temp = new BCG(tokens[0],catalog);
					temp.setDegreeTitle(tokens[0]);
					temp.setRequiredCourses(codes);
					degrees.add(temp);
					oneLine = reader.readLine();
				}else if(tokens[0].equals("CS")) {
					temp = new CS(tokens[0],catalog);
					temp.setDegreeTitle(tokens[0]);
					temp.setRequiredCourses(codes);
					degrees.add(temp);
					oneLine = reader.readLine();
				}else if(tokens[0].equals("SENG")) {
					temp = new SEng(tokens[0],catalog);
					temp.setDegreeTitle(tokens[0]);
					temp.setRequiredCourses(codes);
					degrees.add(temp);
					oneLine = reader.readLine();
				}else {
					System.out.println("Program Does Not Exist");
					oneLine = reader.readLine();
				}
				codes.clear();
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * writes the current state of this object to a file
	 */
	public void writeToDisk() 
	{
		try {
			ObjectOutputStream s = new ObjectOutputStream(
					new FileOutputStream("univ.sav"));
			s.writeObject(this);
			s.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**loads a file from the disk
	 * @param filename
	 * @return
	 */
	public Univ loadDisk(String filename) 
	{
		Univ univ = new Univ();
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename));
			univ = (Univ) inputStream.readObject();
			inputStream.close();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		return univ;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (obj == this) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() 
	{
		return "Univ [catalog=" + catalog + ", degrees=" + degrees + ", codes=" + codes + "]";
	}
}
