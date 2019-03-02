import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Profile object to load uses profile information.
 */
public final class Profile {

	/**
	 * Thread safe, used for generating person id.
	 */
	private static int PERSON_ID = 0;

	/**
	 * private constructor
	 */
	public Profile() {
	}

	/**
	 * Create person profile. As of May 2, any methods refers to this function
	 * is responsible for closing the input stream.
	 *
	 * @param p
	 *            person
	 *
	 * @return person with profile
	 */
	static Person createPerson() {

		Person p = new Person(generatePersonId());

		Scanner input = new Scanner(System.in);
		System.out
				.println("Input your name: (will be same as login user name)");
		String name = input.nextLine();
		p.setName(name);
		// input.close();

		input = new Scanner(System.in);
		System.out.println("Input your age: ");
		String age = input.nextLine();
		p.setAge(Integer.parseInt(age));
		// input.close();

		input = new Scanner(System.in);
		System.out.println("Input your height: ");
		String height = input.nextLine();
		p.setHeight(Integer.parseInt(height));
		// input.close();

		input = new Scanner(System.in);
		System.out.print("Input your weight: ");
		String weight = input.nextLine();
		p.setWeight(Integer.parseInt(weight));

		savePersonData(p);

		System.out.println("Account created successully, your user name is "
				+ p.getName());
		/**
		 * Comment out temporarily for historic testing.
		 */
		// input.close();

		return p;
	}

	/**
	 * Serializes person's profile.
	 *
	 * @param p
	 * @throws IOException
	 */
	static void savePersonData(Person p) {

		ObjectMapper mapper = new ObjectMapper();

		StringBuilder fileName = new StringBuilder();
		File file = new File(fileName.append(p.getName().trim().toLowerCase())
				.append(".json").toString());

		// Object to JSON in file
		try {
			mapper.writeValue(file, p);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show person's profile.
	 *
	 * @param p
	 *            person
	 */
	static void showProfile(Person p) {
		System.out.println("Personal Profile\n-----------------");
		System.out.println("ID: " + String.valueOf(p.getId()));
		System.out.println("Name: " + p.getName());
		System.out.println("Height: " + p.getHeight());
		System.out.println("Weight: " + p.getWeight());
	}

	/**
	 * @deprecated Save data.
	 *
	 * @throws IOException
	 */
	@Deprecated
	static void saveData() throws IOException {

		Scanner inputID = new Scanner(System.in);
		System.out.print("Input your ID: ");

		String idInput = inputID.nextLine();

		Scanner inputDate = new Scanner(System.in);
		System.out.print("Input the date: ");

		String date = inputDate.nextLine();
		File f = new File(idInput + ".txt");
		if (f.exists() && !f.isDirectory()) {
			FileWriter fileWritter = new FileWriter(f.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(date);
			bufferWritter.close();
		}
		inputID.close();
		inputDate.close();
	}

	/**
	 * Check if user already exists.
	 *
	 * @param name
	 *
	 * @return true or false
	 */
	static boolean checkAccount(String name) {
		StringBuilder fileName = new StringBuilder();
		File file = new File(fileName.append(name.trim().toLowerCase())
				.append(".json").toString());
		if (file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * Load person into system based on user name.
	 *
	 * @param name
	 *
	 * @return loaded person
	 */
	static Person loadPersonAccount(String name) {
		if (!checkAccount(name)) {
			System.out
					.println("Account not found, will create a new one, please follow instruction.");
			Person p = createPerson();
			return p;
		}
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder fileName = new StringBuilder();
		File file = new File(fileName.append(name.trim().toLowerCase())
				.append(".json").toString());
		try {
			Person person = mapper.readValue(file, Person.class);
			return person;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @deprecated use user name as file name, this function will be used when
	 *             system runs continuously.
	 *
	 *             Generate Person Id.
	 *
	 * @return Id
	 */
	@Deprecated
	synchronized static int generatePersonId() {
		return PERSON_ID++;
	}
}
