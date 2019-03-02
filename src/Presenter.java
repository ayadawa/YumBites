import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Presenter {
	ItemsModel model; // gets data of items

	// Reads the input file and loads the items into model array list
	public void loadItems() {
		System.out.println("Inside load items method");
		model = new ItemsModel();
		try {
			BufferedReader br = new BufferedReader(new FileReader("MyFile"));
			String fileRead = br.readLine();

			while (fileRead != null) {
				// Use string.split to load a string array with the values from
				// each line of the file, using a bar as the delimiter
				String[] tokenize = fileRead.split("\\|");

				// Assume file is made correctly and make temporary
				// variables for the two types of data
				String name = tokenize[0]; // name of item
				String temp1 = tokenize[1]; // calories of item
				String temp2 = tokenize[2]; // fat of item
				String temp3 = tokenize[3]; // protein of item

				// Convert the temp1 (read as String) to an integer
				int calories = Integer.parseInt(temp1);

				// Convert the temp2 (read as String) to an integer
				int fat = Integer.parseInt(temp2);

				// Convert the temp3 (read as String) to an Integer
				int protein = Integer.parseInt(temp3);

				// Add the two types of data to array list
				model.addItem(name, calories, fat, protein);

				// Read the next line before looping if end of file reached
				fileRead = br.readLine();
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ExerciseModel eModel; // used to get data of exercise items

	public void loadExerciseItems() {
		eModel = new ExerciseModel();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Exercises"));
			String fileRead = br.readLine();

			while (fileRead != null) {
				// Use string.split to load a string array with the values from
				// each line of the file, using a bar as the delimiter
				String[] tokenize = fileRead.split("\\|");

				// Assume file is made correctly and make temporary
				// variables for the two types of data
				String name = tokenize[0];
				String temp1 = tokenize[1];

				// Convert the temp (read as String) to a double
				int cal = Integer.parseInt(temp1);

				// Add the two types of data to array list
				eModel.addExerciseItem(name, cal);

				// Read the next line before looping if end of file reached
				fileRead = br.readLine();
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
