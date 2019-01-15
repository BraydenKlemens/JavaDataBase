package A12430;
/*
 * Brayden Klemens
 * 1000487
 * October 25, 2018
 * Child of degree, special type of degree
 * */

public abstract class HonoursDegree extends Degree 
{

	/**
	 * @param title
	 * @param catalog
	 */
	public HonoursDegree(String title, CourseCatalog catalog) 
	{
		//calls super constructor and passes info up
		super(title,catalog);
	}

}
