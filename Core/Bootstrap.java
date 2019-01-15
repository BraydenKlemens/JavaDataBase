package A12430;

public class Bootstrap {

	public static void main(String[] args) {
		
		Univ univ = new Univ();
		//loads catalog with information from the file
		univ.loadCatalog("courselist.csv");
		//gives the degree information
		univ.addNewDegree("requiredcourses.csv");
		//loads this information to a file
		univ.writeToDisk();
	}
}
