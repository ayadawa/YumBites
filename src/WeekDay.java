import java.util.HashMap;
import java.util.Map;

/**
 * Week Day Definition.
 */
public enum WeekDay {
	/**
	 * Sunday.
	 */
	SUNDAY(1),
	/**
	 * Monday.
	 */
	MONDAY(2),
	/**
	 * Tuesday.
	 */
	TUDSDAY(3),
	/**
	 * Wednesday.
	 */
	WEDNESDAY(4),
	/**
	 * Thursday
	 */
	THURSDAY(5),
	/**
	 * Friday.
	 */
	FRIDAY(6),
	/**
	 * Saturday.
	 */
	SATURDAY(7),

	/**
	 * invalid day.
	 */
	INVALID(-1);

	/**
	 * Weekday value.
	 */
	private int weekDay;

	/**
	 * Private constructor.
	 * 
	 * @param weekDay
	 */
	private WeekDay(int weekDay) {
		this.weekDay = weekDay;
	}

	// provides a function to retrieve weekday from integer value
	private static final Map<Integer, WeekDay> weekdayMap = new HashMap<>();
	static {
		for (WeekDay weekday : values()) {
			weekdayMap.put(weekday.getWeekDayValue(), weekday);
		}
	}

	/**
	 * Return week day value.
	 *
	 * @return value of weekday.
	 */
	public int getWeekDayValue() {
		return this.weekDay;
	}

	/**
	 * Retrieve weekday type based on passed in value.
	 *
	 * @param type
	 *
	 * @return WeekDay
	 */
	public static WeekDay getWeekDayType(int typeValue) {
		return weekdayMap.get(typeValue);
	}
}
