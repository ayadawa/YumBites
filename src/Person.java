import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Person object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {
	/**
	 * Serialize id.
	 */
	private static final long serialVersionUID = 3135993609847761619L;
	/**
	 * Person id
	 */
	private int id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Weight
	 */
	private int weight;
	/**
	 * Height
	 */
	private int height;
	/**
	 * age
	 */
	private int age;

	/**
	 * Max daily Calories gained.
	 */
	private int maxDailyCal;

	/**
	 * Which day gained most calories.
	 */
	private WeekDay maxCalDay;

	/**
	 * Buffer of current week.
	 */
	DailyCaloriesInput[] caloriesInWeek = new DailyCaloriesInput[7];

	/**
	 * Backups for past days.
	 */
	List<DailyCaloriesInput> backUps = new LinkedList<>();

	/**
	 * Default constructor.
	 */
	public Person() {
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 */
	public Person(int id) {
		this.id = id;
	}

	/**
	 * Get person id.
	 *
	 * @return id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets id.
	 *
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets person name.
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set person weight.
	 *
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * Gets person weight.
	 *
	 * @return weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Sets person height.
	 *
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets person height.
	 *
	 * @return height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Sets age.
	 *
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets age.
	 *
	 * @return age
	 */
	public int getAge() {
		return this.age;
	}

	/**
	 * Loads today's calories.
	 *
	 * @param dailyCaloriesInput
	 */
	public void addDailyCalories(DailyCaloriesInput dailyCaloriesInput) {
		int index = dailyCaloriesInput.getWeekDay().getWeekDayValue() - 1;
		if (caloriesInWeek[index] != null) {
			backUps.add(0, caloriesInWeek[index]);
		}
		caloriesInWeek[index] = dailyCaloriesInput;
		calculateMaxDailyCal();
	}

	/**
	 * Calculates max calories gained daily and which day.
	 */
	private void calculateMaxDailyCal() {
		WeekDay weekDay = WeekDay.SUNDAY;
		int maxCal = 0;
		for (int i = 0; i < 7; i++) {
			if (caloriesInWeek[i] != null
					&& (caloriesInWeek[i].getCalorieToday() - caloriesInWeek[i]
							.getCalorieBurn()) > maxCal) {
				maxCal = caloriesInWeek[i].getCalorieToday()
						- caloriesInWeek[i].getCalorieBurn();
				weekDay = WeekDay.getWeekDayType(i + 1);
			}
		}
		this.maxCalDay = weekDay;
		this.maxDailyCal = maxCal;
	}

	/**
	 * Gets the max calories in the past week.
	 *
	 * @return max calories
	 */
	public int getMaxCalories() {
		if (this.maxDailyCal == 0) {
			this.calculateMaxDailyCal();
		}
		return this.maxDailyCal;
	}

	/**
	 * Set max daily calories.
	 *
	 * @param maxDailyCal
	 */
	public void setMaxDailyCal(int maxDailyCal) {
		this.maxDailyCal = maxDailyCal;
	}

	/**
	 * Gets the day in past week with most calories consumed.
	 *
	 * @return weekday
	 */
	public WeekDay getDayConsumedMostCalWeekly() {
		if (this.maxCalDay == null) {
			this.calculateMaxDailyCal();
		}
		return this.maxCalDay;
	}

	/**
	 * Sets max calories gained day.
	 *
	 * @param maxCalDay
	 */
	public void setMaxCalDay(WeekDay maxCalDay) {
		this.maxCalDay = maxCalDay;
	}

	/**
	 * Gets calories in the past week.
	 *
	 * @return calories
	 */
	public DailyCaloriesInput[] getWeekCalories() {
		return this.caloriesInWeek;
	}

	/**
	 * Sets calories in past week.
	 *
	 * @param inputs
	 */
	public void setWeekCalories(DailyCaloriesInput[] inputs) {
		this.caloriesInWeek = new DailyCaloriesInput[7];
		if (inputs != null) {
			int length = inputs.length;
			for (int i = 0; i < 7; i++) {
				if (i < length) {
					caloriesInWeek[i] = inputs[i];
				} else {
					caloriesInWeek[i] = null;
				}
			}
		}
	}

	/**
	 * Gets the past calorie data.
	 *
	 * @param date
	 *
	 * @return calorie
	 */
	public int getHistoricCalorie(Date date) {
		int distance = (int) (System.currentTimeMillis() - date.getTime())
				/ (86400 * 1000);
		DailyCaloriesInput current = null;
		Iterator<DailyCaloriesInput> it = backUps.iterator();
		while (distance-- > 0) {
			if (it.hasNext()) {
				current = it.next();
			} else {
				return 0;
			}
		}
		if (current == null) {
			return 0;
		}
		return current.getCalorieToday();
	}

	/**
	 * Gets backups.
	 *
	 * @return list of historic calorie
	 */
	public List<DailyCaloriesInput> getBackUps() {
		return this.backUps;
	}

	/**
	 * Sets backups of historic calories.
	 *
	 * @param backUpCals
	 */
	public void setBackUps(List<DailyCaloriesInput> backUpCals) {
		if (backUpCals != null) {
			this.backUps.addAll(backUpCals);
		}
	}
}
