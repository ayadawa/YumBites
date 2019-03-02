
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Data model is capture daily input from front end.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyCaloriesInput implements Serializable {
	/**
	 * Default version.
	 */
	private static final long serialVersionUID = -9001338926639953542L;
	/**
	 * Which day in week.
	 */
	private WeekDay weekDay;
	/**
	 * Calories taken the day.
	 */
	private int calorieToday;
	/**
	 * Calories burn the day
	 */
	private int calorieBurn;

	/**
	 * Default constructor.
	 */
	public DailyCaloriesInput() {
	}

	/**
	 * Constructors.
	 *
	 * @param weekDay
	 * @param calorieToday
	 */
	public DailyCaloriesInput(WeekDay weekDay, int calorieToday, int calorieBurn) {
		this.weekDay = weekDay;
		this.calorieToday = calorieToday;
		this.calorieBurn = calorieBurn;
	}

	/**
	 * Get the value of day in week.
	 *
	 * @return day value
	 */
	public WeekDay getWeekDay() {
		return this.weekDay;
	}

	/**
	 * Sets weekday.
	 *
	 * @param weekDay
	 */
	public void setWeekDay(WeekDay weekDay) {
		this.weekDay = weekDay;
	}

	/**
	 * Returns calories taken the day.
	 *
	 * @return calories taken today.
	 */
	public int getCalorieToday() {
		return this.calorieToday;
	}

	/**
	 * Sets Calories consumed today.
	 *
	 * @param calorieToday
	 */
	public void setCaloriesToday(int calorieToday) {
		this.calorieToday = calorieToday;
	}

	/**
	 * Returns calories burn the day.
	 * 
	 * @return calories burn today.
	 */
	public int getCalorieBurn() {
		return this.calorieBurn;
	}

	/**
	 * Set Calories burn today.
	 * 
	 * @param calorieBurn
	 */
	public void setCaloriesBurn(int calorieBurn) {
		this.calorieBurn = calorieBurn;
	}
}
