import java.util.LinkedList;

public class DataStructure {
	private LinkedList<Items> list;
	private LinkedList<ExItems> exItems;

	/*
	 * Constructor
	 * 
	 * @param l list being
	 */
	public DataStructure(LinkedList<Items> l, LinkedList<ExItems> a) {
		list = l;
		exItems = a;
	}

	/*
	 * Calculate the total calories being consumed or burned
	 * 
	 * @return the total calories being consumed or burned
	 */
	public int totalCal() {
		int totalCal = 0;
		for (int i = 0; i < list.size(); i++) {
			Items n = list.get(i);
			totalCal = totalCal + n.getCalories();
		}
		return totalCal;
	}

	public int totalCalBurned() {
		int totalCalBurned = 0;
		for (int i = 0; i < exItems.size(); i++) {
			ExItems a = exItems.get(i);
			totalCalBurned = totalCalBurned + a.getCaloriesBurnt();
		}
		return totalCalBurned;
	}

	/*
	 * Return the list
	 * 
	 * @return the list
	 */
	public LinkedList<Items> getLinkedList() {
		return list;
	}

	/*
	 *
	 */
	public void undo() {
		list.pollLast();
	}
	/*
	 *
	 */
}
