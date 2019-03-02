public class Items {
	// Each item contains a name, calorie count and fat count
	private String name;
	private int calories;
	private int fat;
	private int protein;

	public Items(String name, int calories, int fat, int protein) {
		this.name = name;
		this.calories = calories;
		this.fat = fat;
		this.protein = protein;
	}

	// Retrieves the name of item
	public String getName() {
		return name;
	}

	// Retrieves the calories of item
	public int getCalories() {
		return calories;
	}

	// Retrieves the fat of item
	public int getFat() {
		return fat;
	}

	public int getProtein() {
		return protein;
	}
}
