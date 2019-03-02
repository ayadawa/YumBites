/**
 * This class will store the exercise items and calories burnt.
 * 
 * @author Sravani Yajamanam
 *
 */

public class ExItems {
	// Each item contains a name and calorie burnt count
	private String exName;
	private int calBurnt;

	public ExItems(String exName, int calBurnt) {
		this.exName = exName;
		this.calBurnt = calBurnt;
	}

	// Retrieves the name of item
	public String getExerciseName() {
		return exName;
	}

	// Retrieves the price of item
	public int getCaloriesBurnt() {
		return calBurnt;
	}
}
