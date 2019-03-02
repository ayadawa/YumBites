
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class DisplayGUI {
	JFrame frame;
	JFrame w;
	JFrame load;
	JFrame display;
	private JFrame foodFrame;
	private JFrame analyze;
	private JFrame eFrame;
	private JButton button;
	private JButton eButton;
	private ArrayList<Items> items;
	private ArrayList<ExItems> exItems;
	protected ArrayList<JButton> buttonList;
	protected ArrayList<JButton> eButtonList;
	private JTextArea textArea;
	private JTextArea exTextArea;
	private LinkedList<Items> list;
	private LinkedList<ExItems> exList;
	private Presenter foodPresent;
	private Presenter exPresent;
	private Person person;
	private ArrayList<JTextField> info;
	private JTextArea displayResult;
	private JComboBox<String> dayCombo;
	private String dayInput;

	/**
	 * Input date
	 */
	private int dateForInput;

	/**
	 * Default constructor.
	 */
	public DisplayGUI() {
		this(null);
	}

	/**
	 * Constructor with injected user.
	 *
	 * @param person
	 */
	public DisplayGUI(Person person) {
		this(person, Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * Constructor.
	 *
	 * @param person
	 * @param dateForInput
	 */
	public DisplayGUI(Person person, int dateForInput) {
		this.person = person;
		this.dateForInput = dateForInput;
	}

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DisplayGUI window = new DisplayGUI();
			window.intialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create contents of the window.
	 */
	void intialize() {
		// ----------------------------------------------------------------------
		// //
		// --------------------------- MAIN FRAME --------------------------- //
		// ----------------------------------------------------------------------
		// //
		frame = new JFrame("Health Fitness Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 400, 325);
		frame.setLayout(new BorderLayout());

		JPanel log = new JPanel();
		JLabel welcome = new JLabel("Welcome to YumBites");
		welcome.setFont(new Font("ROMAN_BASELINE", 25, 15));
		log.add(welcome);
		frame.add(log, BorderLayout.PAGE_START);

		JPanel buttons = new JPanel();
		
		person = new Person();
		JButton loadButton = new JButton("Load your account");
		loadButton.setBounds(70, 35, 230, 40);
		loadButton.addActionListener(new LoadProfile());
		buttons.add(loadButton);

		JButton register = new JButton("Register a new account");
		register.setBounds(70, 120, 230, 40);
		register.addActionListener(new CreateProfile());
		buttons.add(register);

		JButton exit = new JButton("Exit");
		exit.setBounds(70, 200, 230, 40);
		exit.addActionListener(new Exit());
		buttons.add(exit);

		frame.add(buttons);
		buttons.setLayout(null);
		frame.setVisible(true);
	}

	void register() {
		// INSERT NHU / LINGFANG CODE
		info = new ArrayList<JTextField>();
		w = new JFrame();
		w.setBounds(100, 100, 400, 325);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setTitle("Health Fitness Application");
		JPanel log = new JPanel();
		JLabel welcome = new JLabel("Welcome to YumBites");
		welcome.setFont(new Font("ROMAN_BASELINE", 25, 15));
		log.add(welcome);
		w.add(log, BorderLayout.PAGE_START);

		JPanel label = new JPanel();
		w.add(label);
		label.setLayout(null);
		info = new ArrayList<JTextField>();

		JLabel userLabel = new JLabel("Name:");
		userLabel.setBounds(70, 10, 80, 25);
		label.add(userLabel);

		JLabel ageLabel = new JLabel("Age: ");
		ageLabel.setBounds(70, 50, 80, 25);
		label.add(ageLabel);

		JLabel heightLabel = new JLabel("Height: ");
		heightLabel.setBounds(70, 90, 80, 25);
		label.add(heightLabel);

		JLabel weightLabel = new JLabel("Weight: ");
		weightLabel.setBounds(70, 130, 80, 25);
		label.add(weightLabel);

		JLabel dayLabel = new JLabel("Day: ");
		dayLabel.setBounds(70, 170, 80, 25);
		label.add(dayLabel);

		JTextField nameInput = new JTextField(10);
		nameInput.setBounds(140, 10, 160, 25);
		label.add(nameInput);
		info.add(nameInput);

		JTextField ageInput = new JTextField(10);
		ageInput.setBounds(140, 50, 160, 25);
		label.add(ageInput);
		info.add(ageInput);

		JTextField heightInput = new JTextField(10);
		heightInput.setBounds(140, 90, 160, 25);
		label.add(heightInput);
		info.add(heightInput);

		JTextField weightInput = new JTextField(10);
		weightInput.setBounds(140, 130, 160, 25);
		label.add(weightInput);
		info.add(weightInput);

		final DefaultComboBoxModel<String> day = new DefaultComboBoxModel<String>();
		day.addElement("1. SUNDAY");
		day.addElement("2. MONDAY");
		day.addElement("3. TUESDAY");
		day.addElement("4. WEDNESDAY");
		day.addElement("5. THURSDAY");
		day.addElement("6. FRIDAY");
		day.addElement("7. SATURDAY");
		day.addElement("0 for current week day");

		dayCombo = new JComboBox<String>(day);
		dayCombo.setSelectedIndex(0);
		dayCombo.setBounds(140, 170, 160, 25);

		label.add(dayCombo);

		JButton nextButton = new JButton("Next");
		nextButton.setBounds(70, 210, heightInput.getX() + 160 - 70, 25);
		nextButton.addActionListener(new Register());
		label.add(nextButton);

	}

	void load() {
		load = new JFrame("Health Fitness Application");
		load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		load.setBounds(100, 100, 400, 200);
		load.setLayout(new BorderLayout());

		JPanel log = new JPanel();
		JLabel title = new JLabel("Loading Zone");
		title.setFont(new Font("ROMAN_BASELINE", 25, 15));
		log.add(title);
		load.add(log, BorderLayout.PAGE_START);

		JPanel label = new JPanel();
		load.add(label);
		label.setLayout(null);

		JLabel userName = new JLabel("Username:");
		userName.setBounds(70, 10, 80, 25);
		label.add(userName);

		info = new ArrayList<JTextField>();

		JTextField userNameInput = new JTextField(10);
		userNameInput.setBounds(140, 10, 160, 25);
		label.add(userNameInput);
		
		info.add(userNameInput);

		JButton loadButton = new JButton("Load");
		loadButton.setBounds(70, 60, 230, 25);
		loadButton.addActionListener(new DisplayOption());
		label.add(loadButton);

	}

	void showResult() {
		display = new JFrame("Options & Results");
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setBounds(100, 100, 400, 400);
		display.setLayout(new BorderLayout());

		JPanel buttons = new JPanel();
		buttons.setLayout(null);

		JButton option1 = new JButton("Input daily calories");
		option1.setBounds(70, 20, 230, 20);
		option1.addActionListener(new Option1());
		buttons.add(option1);

		JButton option2 = new JButton("Check past week historic data");
		option2.setBounds(70, 50, 230, 20);
		option2.addActionListener(new Option2());
		buttons.add(option2);

		JButton option3 = new JButton("Find max daily calories in past week");
		option3.setBounds(70, 80, 230, 20);
		option3.addActionListener(new Option3());
		buttons.add(option3);

		JButton exit = new JButton("Exit");
		exit.setBounds(70, 110, 230, 20);
		exit.addActionListener(new Exit());
		buttons.add(exit);

		displayResult = new JTextArea(10, 20);
		displayResult.setText("Result");
		JScrollPane scrollingArea = new JScrollPane(displayResult);
		scrollingArea.setBounds(10, 150, display.getWidth() - 35, 200);

		buttons.add(scrollingArea, BorderLayout.PAGE_END);
		display.add(buttons);
		// display.add(content,BorderLayout.PAGE_END);

		display.setVisible(true);
	}

	class Option1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] options = { "1. SUNDAY", "2. MONDAY", "3. TUESDAY","4. WEDNESDAY", "5. THURSDAY", "6. FRIDAY", "7. SATURDAY", "0 for current week day" };
			String first = "1. SUNDAY";
			Object selected = JOptionPane.showInputDialog(null, "Please set let a date", "Select Date", JOptionPane.QUESTION_MESSAGE, null, options, first);
			if (selected != null)
			{
				int result = Arrays.asList(options).indexOf(selected);
				System.out.println("You have selected: " + options[result]);
				int selection2 = Integer.parseInt(options[result].substring(0, 1));
				if (selection2 > 7) {
					System.out.println("invalid input! exit");
					System.exit(1);
				}
				if (selection2 == 0) {
					//person = p
					dateForInput = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
				} else {
					//person = p;
					dateForInput = selection2;
				}
				
				createFoodFrame();
				load.setVisible(false);
				display.setVisible(false);
			}
			else
			{
				System.out.println("Working");
			}
			
		}

	}

	class Option2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = info.get(0).getText();
			person = Profile.loadPersonAccount(username);
			DailyCaloriesInput[] dcis = person.getWeekCalories();
			displayResult.setText("Result");
			displayResult.append("\nHistoric Data: \n");
			for (int i = 0; i < dcis.length; i++) {
				if (dcis[i] == null) {
					displayResult.append(WeekDay.getWeekDayType(i + 1).toString() + "\n");
					System.out.println(WeekDay.getWeekDayType(i + 1).toString());
				} else {
					displayResult.append(dcis[i].getWeekDay().toString() + " : " + "Consumed: "
							+ dcis[i].getCalorieToday() + ", Burned: " + dcis[i].getCalorieBurn() + "\n");
					System.out.println(dcis[i].getWeekDay().toString() + " : " + "Consumed: "
							+ dcis[i].getCalorieToday() + ", Burned: " + dcis[i].getCalorieBurn());
				}
			}
		}
	}

	class Option3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			displayResult.setText("Result");
			displayResult.append("\nMax daily calories in the past week: \n");
			displayResult.append(person.getDayConsumedMostCalWeekly().toString() + " :" + person.getMaxCalories());
			System.out.println(person.getDayConsumedMostCalWeekly().toString() + " :" + person.getMaxCalories());
		}

	}

	class LoadProfile implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			load();
			frame.setVisible(false);
			load.setVisible(true);
		}
	}

	class DisplayOption implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = info.get(0).getText();
			person = Profile.loadPersonAccount(username);
			System.out.println(
					"Account loaded successfully, please select options: 1. input daily calories, 2. check past week historic data, 3. Find max daily calories in past week, 0. exit");
			load.setVisible(false);
			showResult();
		}

	}

	class Exit implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(1);
		}
	}

	class CreateProfile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			DisplayGUI window = new DisplayGUI();
			window.register();
			window.w.setVisible(true);
			frame.setVisible(false);
		}

	}

	class Register implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			Profile pro = new Profile();
			Person p = new Person();
			for (JTextField each : info) {
				if (each.getText().length() == 0) {
					{
						JOptionPane.showMessageDialog(null, "Please enter your info before proceeding", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
			// Select the day
			dayInput = String.valueOf(dayCombo.getSelectedItem());
			System.out.println(dayInput);
			int selection2 = Integer.parseInt(dayInput.substring(0, 1));
			if (selection2 > 7) {
				System.out.println("invalid input! exit");
				System.exit(1);
			}
			if (selection2 == 0) {
				person = p;
				dateForInput = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			} else {
				person = p;
				dateForInput = selection2;
			}

			// Take all the values
			String name = info.get(0).getText().toString();
			if (Pattern.matches("[a-zA-Z]+", name))
				p.setName(name);
			else {
				JOptionPane.showMessageDialog(null, "Your name cannot be a digit", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				p.setHeight(Integer.parseInt(info.get(2).getText()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Height must be in inches", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			p.setAge(Integer.parseInt(info.get(1).getText()));
			p.setWeight(Integer.parseInt(info.get(3).getText()));

			// If the user already have a profile, do not overide it
			try {
				Scanner textfile = new Scanner(new File(p.getName() + ".txt"));
				System.out.println("Welcome back" + p.getName());
				Profile.showProfile(p);
				JOptionPane.showMessageDialog(null,
						"Welcome back," + p.getName() + "!\nLet's record what you ate and did today.");
			} catch (IOException e) {
				Profile.savePersonData(p);
				JOptionPane.showMessageDialog(null,
						"Your profile is created. \n" + "Your username is: " + p.getName().trim().toLowerCase() + "\n"
								+ " Step 1: Select the food that you ate today. \n"
								+ " Step 2: Select the workout that you have done. \n"
								+ " Step 3: We will analyze your calories balance. \n");
				w.setVisible(false);
				createFoodFrame();
				
			}
		}

	}

	// ----------------------------------------------------------------------
	// //
	// --------------------------- FOOD FRAME --------------------------- //
	// ----------------------------------------------------------------------
	// //
	void createFoodFrame() {

		// Change to a different frame

		foodFrame = new JFrame("Log Food");
		foodFrame.setBounds(100, 100, 850, 600);
		foodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(false); // Close main window
		foodFrame.setVisible(true); // Open food window

		// --------------------------- LOG FOOD PANEL
		// --------------------------- //
		JPanel foodPanel = new JPanel();
		JLabel foodLabel = new JLabel("What did you eat today?");
		foodPanel.add(foodLabel);
		foodFrame.add(foodPanel, BorderLayout.PAGE_START);

		// --------------------------- BUTTON PANEL
		// --------------------------- //
		JPanel buttonPanel = new JPanel();
		foodPresent = new Presenter();
		foodPresent.loadItems();

		// Retrieve the array list of items
		items = foodPresent.model.getItems();
		list = new LinkedList<Items>();

		// Find the square root of the total # of items
		// and get the ceiling of the result - this is the grid size
		double sqrtSize = Math.sqrt(items.size());
		int gridSize = (int) Math.ceil(sqrtSize);

		// Set the grid layout
		buttonPanel.setLayout(new GridLayout(gridSize, gridSize));

		// Iterate over the buttons
		buttonList = new ArrayList<JButton>();
		for (int i = 0; i < items.size(); i++) {
			button = new JButton(items.get(i).getName()); // create a
															// button
			// with name of item
			button.addActionListener(
					new ButtonListener(items.get(i).getName(), items.get(i).getCalories(), items.get(i).getFat())); // implement
																													// action
																													// listener
			buttonPanel.add(button); // add each button to panel
			buttonList.add(button); // add button to array list of
									// buttons
		}

		foodFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

		// --------------------------- DISPLAY FOOD PANEL
		// --------------------------- //
		JPanel displayFoodPanel = new JPanel();
		displayFoodPanel.setLayout(new BoxLayout(displayFoodPanel, BoxLayout.PAGE_AXIS));
		textArea = new JTextArea(10, displayFoodPanel.getWidth()); // Show
																	// a
																	// list
																	// of
																	// food
																	// items
		JScrollPane sp = new JScrollPane(textArea); // Add a scroll
													// panel to
													// text area
		textArea.setText("You selected the following: \n");
		textArea.setEditable(false); // User should not be able to edit
										// text
		displayFoodPanel.add(sp); // Add scroll panel (with text area)
									// into
									// display panel

		JButton finishFood = new JButton("Proceed to next step");
		finishFood.addActionListener(new CreateExerciseFrame()); // Goes
																	// into
																	// exercise
																	// window
		displayFoodPanel.add(finishFood, BorderLayout.CENTER);

		foodFrame.add(displayFoodPanel, BorderLayout.PAGE_END);
	}

	// --------------------------- BUTTON LISTENER
	// ---------------------------
	// //
	class ButtonListener implements ActionListener {
		private String name; // stores item name
		private int cal; // stores item price
		private int fat;

		public ButtonListener() {
			name = "";
			cal = 0;
			fat = 0;
		}

		public ButtonListener(String name, int cal, int fat) {
			this.name = name;
			this.cal = cal;
			this.fat = fat;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// Holds the index of the button pressed
			int i = buttonList.indexOf(event.getSource());
			if (i >= 0) {
				System.out.println("Name: " + name + " Cal: " + cal + " Fat: " + fat);
				textArea.append(name + "\n");
			}
			list.add(items.get(i));
		}
	}

	// ----------------------------------------------------------------------
	// //
	// --------------------------- EXERCISE FRAME
	// --------------------------- //
	// ----------------------------------------------------------------------
	// //
	class CreateExerciseFrame implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			eFrame = new JFrame("Log Exercises");
			eFrame.setBounds(100, 100, 850, 600);
			eFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			foodFrame.setVisible(false); // Close food window
			eFrame.setVisible(true); // Open exercise window

			// --------------------------- LOG EXERCISE PANEL
			// --------------------------- //
			JPanel logExercisePanel = new JPanel();
			JLabel exerciseLabel = new JLabel("Did you exercise today?");
			logExercisePanel.add(exerciseLabel);
			eFrame.add(logExercisePanel, BorderLayout.PAGE_START);

			// --------------------------- EXERCISE PANEL
			// --------------------------- //
			JPanel exercisePanel = new JPanel();
			eFrame.getContentPane().add(exercisePanel, BorderLayout.EAST);
			exPresent = new Presenter();
			exPresent.loadExerciseItems();

			// Retrieve the array list of items
			exItems = exPresent.eModel.getExerciseItems();
			exList = new LinkedList<ExItems>();

			// Find the square root of the total # of items
			// and get the ceiling of the result - this is the grid size
			double exSqrtSize = Math.sqrt(exItems.size());
			int exGridSize = (int) Math.ceil(exSqrtSize);

			// Set the grid layout
			exercisePanel.setLayout(new GridLayout(exGridSize, exGridSize));

			// Iterate over the buttons
			eButtonList = new ArrayList<JButton>();
			for (int i = 0; i < exItems.size(); i++) {
				eButton = new JButton(exItems.get(i).getExerciseName()); // create
																			// a
																			// button
				// with name of item
				eButton.addActionListener(
						new ExerciseListener(exItems.get(i).getExerciseName(), exItems.get(i).getCaloriesBurnt())); // implement
																													// action
																													// listener
				exercisePanel.add(eButton); // add each button to panel
				eButtonList.add(eButton);
			}

			eFrame.add(exercisePanel);

			// --------------------------- DISPLAY EXERCISE PANEL
			// --------------------------- //
			JPanel displayExercisePanel = new JPanel();
			displayExercisePanel.setLayout(new BoxLayout(displayExercisePanel, BoxLayout.PAGE_AXIS));
			exTextArea = new JTextArea(10, displayExercisePanel.getWidth()); // Show
																				// a
																				// list
																				// of
																				// exercise
																				// items
			JScrollPane sp = new JScrollPane(exTextArea); // Add a scroll
															// panel
															// to text area
			exTextArea.setText("You selected the following: \n");
			exTextArea.setEditable(false); // User should not be able to
											// edit
											// text
			displayExercisePanel.add(sp); // Add scroll panel (with text
											// area)
											// into display panel

			JButton finish = new JButton("That's all for today!");
			finish.addActionListener(new CreateResultFrame()); // Goes into
																// result
																// window
			displayExercisePanel.add(finish);

			eFrame.add(displayExercisePanel, BorderLayout.PAGE_END);
		}
	}

	// --------------------------- EXERCISE LISTENER
	// ---------------------------
	// //
	class ExerciseListener implements ActionListener {
		private String exName; // stores item name
		private int calBurnt; // stores calories burnt

		public ExerciseListener() {
			exName = "";
			calBurnt = 0;
		}

		public ExerciseListener(String exName, int calBurnt) {
			this.exName = exName;
			this.calBurnt = calBurnt;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// Holds the index of the button pressed
			int i = eButtonList.indexOf(event.getSource());
			if (i >= 0) {
				System.out.println("Exercise: " + exName + " Cal Burnt: " + calBurnt);
				exTextArea.append(exName + "\n");
			}
			exList.add(exItems.get(i));
		}
	}

	// ----------------------------------------------------------------------
	// //
	// --------------------------- RESULT FRAME ---------------------------
	// //
	// ----------------------------------------------------------------------
	// //
	class CreateResultFrame implements ActionListener

	{

		int totalCal = 0;
		int totalFat = 0;
		int totalProtein = 0;
		int totalCalIntake = 0;
		int totalCalBurned = 0;

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			analyze = new JFrame("Health Fitness Analysis");
			analyze.setBounds(100, 100, 850, 600);
			analyze.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			eFrame.setVisible(false); // Close exercise window
			analyze.setVisible(true); // Open analysis window

			// Insert Archana's Code
			DataStructure d = new DataStructure(list, exList);
			int totalCal = d.totalCal();
			int totalCalBurned = d.totalCalBurned();

			// Setup person.
			synchronized (this)
			{
				person.addDailyCalories(
						new DailyCaloriesInput(WeekDay.getWeekDayType(dateForInput), totalCal, totalCalBurned));
				Profile.savePersonData(person);
			}

			// calculate totals of items
			for (int i = 0; i < list.size(); i++) {

				Items n = list.get(i);

				totalCal = totalCal + n.getCalories();
				totalFat = totalFat + n.getFat();
				totalProtein = totalProtein + n.getProtein();

			}

			for (int i = 0; i < exList.size(); i++) {
				ExItems a = exList.get(i);
				totalCalBurned = totalCalBurned + a.getCaloriesBurnt();
				totalCalIntake = totalCal - totalCalBurned;
			}

			JPanel display = new JPanel();

			JLabel total1 = new JLabel("Total Calories Consumed: " + totalCal);
			JLabel total2 = new JLabel("\nTotal Grams of Fat Consumed: " + totalFat);
			JLabel total3 = new JLabel("\nTotal Grams of Protein Consumed: " + totalProtein);
			JLabel total4 = new JLabel("\nTotal Calories Burned: " + totalCalBurned);
			JLabel total5 = new JLabel("\nTotal Calorie Intake: " + totalCalIntake);

			total1.setFont(new Font("ROMAN_BASELINE", 40, 20));
			total2.setFont(new Font("ROMAN_BASELINE", 40, 20));
			total3.setFont(new Font("ROMAN_BASELINE", 40, 20));
			total4.setFont(new Font("ROMAN_BASELINE", 40, 20));
			total5.setFont(new Font("ROMAN_BASELINE", 40, 20));
			display.add(total1);
			display.add(total2);
			display.add(total3);
			display.add(total4);
			display.add(total5);
			analyze.add(display);

			// chart Display
			DefaultCategoryDataset chart = new DefaultCategoryDataset();

			chart.setValue(totalCal, "Amount", "Total Calories (Cal)");
			chart.setValue(totalFat, "Amount", "Total Fat (G)");
			chart.setValue(totalProtein, "Amount", "Total Protein (G)");
			chart.setValue(totalCalBurned, "Amount", "Total Burned (Cal)");
			chart.setValue(totalCalIntake, "Amount", "Total Intake (Cal)");

			JFreeChart jchart = ChartFactory.createBarChart("Food Intake", "Type of Intake", "Amount", chart,
					PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot plot = jchart.getCategoryPlot();
			plot.setRangeGridlinePaint(Color.BLACK);

			ChartFrame chartFrm = new ChartFrame("Food Intake", jchart, true);
			chartFrm.setVisible(true);
			chartFrm.setBounds(100, 200, analyze.getWidth(), analyze.getHeight() - 100);
			
		}
	}
}