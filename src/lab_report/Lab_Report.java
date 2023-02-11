/*Many students around the world studying science whether it is biology, chemistry, or physics are sometimes faced with the dilemma and challenge of writing science lab reports. Such challenges include not being able to know the right format for writing a science lab report, the word count limit for each section of the report, or the type of information that needs to be included per section of the report. This is where you come in, as a programmer you need to develop a science lab report generator system to automatically generate lab reports for students studying the sciences specifically aimed at the chemistry and biology science subjects. 
The lab report generator system should be programmed to provide students with guidelines for the information that needs to be included in each section of the report. The system should contain a word count subsystem for each section of the report to guide students on the number limit of words needed per section. The sections that will need to be included in the lab report generator include- 1. The lab report title, 2. the statement of the problem or the research question, 3. Hypothesis, 4. The variables during the lab experiment, 5. Materials used for the experiment, 6. The methods by which the experiment was conducted, 7. Picture(s) of the lab
experiment set up, 8. The data results of the experiment should be represented in a line chart, 9. The Conclusion. Information received from the user for each section of the lab report should be written in the lab report pdf file that will be generated for the user. Interactive GUI features should also be included in the lab report generator to make the user interface friendly.

To develop the science lab report generator programmed system, the following guidelines need to be followed during the design and development of the program:-

1. The main class will need to be created first. Then the user should be welcomed to the science lab report generator program. After that, the user will be prompted to enter the information in each section of the lab report. A StringBuilder class will be included to append the information entered for the first three sections of the lab report which are:- 1. The title of the lab experiment, the statement of the problem, and the hypothesis. The StringBuilder’s capacity method will be used to find the number of characters contained in the information entered for each section to control the user's entries. A for loop will also be included to count the number 
of words written in each section to display to the user(this's an alernative method of controlling the user's wording) without using the capacity method of the StringBuilder.

2. The Jfreechart and itext jar files need to be installed in the Java project so that the necessary packages can be imported.

3. The materials and methods sections will need to use the list method of the itext class so that the materials and methods of the experiment entered by the user can be written in the pdf file using the numbered list formatting system. A word count system using the for loop will also be included in these two sections to limit the number of words the user needs to enter.

4. The packages and key statements from the Jfreechart and itext packages that will need to be included in the programmed system to write and store the information the user has entered in the front-end GUI per section of the lab report.

5. Classes needed for inserting the line chart based on the data results that the user enters needs to be included in the lab report generator system using the Jfreechart library which will be explained the development of the lab generator system in this lesson.

6. Finally a loop structure whether while or do while loop will need to be implemented in the code to allow the user to re-run the lab report generator system if they would like to create another lab report. */

package lab_report;
import java.util.*;
import java.awt.Dimension; //Because we wanna take a screenshot of certain dimention(not the whole screen).
import java.awt.Rectangle; //helps us to determine the width and height of the screen that we would like to take a screenshot of.
import java.awt.Robot; //Handles mouse and keyboard controls and would actually take the screenshot just like we do by holding a key on your keyboard.
import java.awt.Toolkit; //Helps to open any application.
import java.awt.event.KeyEvent; //It allows the Robot Class and the object we created from the Robot Class to be actually take the screenshot itself.
import java.awt.image.BufferedImage; //Handles opening and saving the images to certain locations/places.
import javax.imageio.ImageIO; //It's to be able to know where the image is using image path location--that's the input.Ans the output is to be able to open the image.
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

//Packages for Chart, Line Chart that we want to be generated for the user based on the data results the user'll be entering for the lab experiment.
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; //Panel means empty window that'll contain the chart.
import org.jfree.chart.JFreeChart; //Dataset is the one that allows you to enter X Y values for whatever data you wanna plot on the line charts.
import org.jfree.data.category.DefaultCategoryDataset; //Allows the user to enter the X and Y values to plot on the line chart.

//Packages for hnadleing opening of the File.
import java.io.File;
import java.io.FileOutputStream; //to be able to get those files and save them in a particular location. Here we're saving images in the actual lab report which is a pdf file. Also that pdf file we need to store.
import java.net.URL;

//itext jar is going to help to store the information the user is entering such as title of the lab report , including image, including underlying.
import com.lowagie.text.Chunk; //Chunk Class handles any String font formatting(like underlying, bold).
import com.lowagie.text.Document; //The Document Class is actually going to oprn the PDF document.
import com.lowagie.text.Element;
import com.lowagie.text.Image; //This Image Class actually allows us to insert the images into the PDF File.
import com.lowagie.text.Paragraph; //Helps to store information as a paragraph.
import com.lowagie.text.pdf.PdfWriter; //The PDF Writer acts like pen writing on the actual PDF document itself.
import com.lowagie.text.List; //List helps us to import bullet points, numbered list etc automatically.


public class Lab_Report extends JFrame{
	
	//Variable for counting word for each section.
	static int titleword_counter = 0;
	static int Statement_prb_counter = 0;
	static int hypothesis_counter = 0;
	static int materials_counter = 0;
	static int method_counter = 0;
	static int conclusion_counter = 0;
	
	static String labreport_title;
	static Document document;
	static PdfWriter writer;
	
	//Variables for word acceptence status for each section.
	static String title_acceptstatus = "";
	static String problem_acceptstatus = "";
	static String hypoth_acceptstatus = "";
	static String material_acceptstatus = "";
	static String method_acceptstatus = "";
	static String conclusion_acceptstatus = "";
	
	static String material;
	static String add4;
	static String method;
	static String add5;
	static String series_name;
	
	//This's part that generates and displays the line chart according to the user's dataset entry.
	//Creating Constructor (It's always used whenever we want to able to create an object of a Class and you want to that object to perform an action/operation).
	public Lab_Report(String title, String chart_title, String XAxis, String YAxis) {
		
		super(title);
		//Creating dataset that's going to handle allowing the user to enter X and Y values that'll be plotted in the line chart itself.
		DefaultCategoryDataset dataset = createDataset(); //we need to create createDataset method() which is going to contail X and Y points. All of the information of this method will be stored as the dataset.
		//Create Chart.
		JFreeChart chart = ChartFactory.createLineChart(chart_title, XAxis, YAxis, dataset);//JFreeChart Class actually generates the chart. ChartFactory contains different types of charts.
		ChartPanel panel = new ChartPanel(chart); //JFrame contains empty window. And We want to put the line chart on that window.
		setContentPane(panel);//This helps the line chart to actually pop up  and show to the user.
	}
	
	//Creating createDataset() method. 
	public DefaultCategoryDataset createDataset() {
		Scanner textobj = new Scanner(System.in);
		Scanner numobj = new Scanner(System.in);
		
		System.out.println("Enter the name of the series ");
		String data = textobj.nextLine();
		series_name = data;
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Series Name:- "+ series_name);
		System.out.println(ANSI_Colors.PURPLE+"How many datasets you would like to enter ");
		int dataset_no = numobj.nextInt();
		
		for(int i=0; i<dataset_no; i++) {
			System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"Enter the Y axis value(this should be in number): ");
			int y_axis = numobj.nextInt();
			System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"Enter the X axis value(this should be in text): ");
			String x_axis = textobj.nextLine();
			
			dataset.addValue(y_axis, series_name, x_axis);
		}
		return dataset;
		
	}
	
	
	public static void main(String[] args) throws Exception{
		Scanner text = new Scanner(System.in);
		Scanner number = new Scanner(System.in);
		System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Welcome to the Science lab Report Generator!");
		Thread.sleep(2000);
		System.out.println();
		System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Would you like to start creating lab reports(yes or no)? ");
		String user_response = text.nextLine();
		while(user_response.equalsIgnoreCase("yes")) {
			System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"Please enter you name: ");
			String name = text.nextLine();
			System.out.println();
			System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Welcome "+name+" let's get started");
			StringBuilder title = new StringBuilder(name.toUpperCase()+ "'s LAB REPORT");
			labreport_title = name + "'s Lab Report" + ".pdf";
			String user_name = title.toString();
			Thread.sleep(2000);
			 
			
			//1. Title of the Lab Experiment.
			System.out.println();
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
			System.out.println("We'll start by entering the title of your lab experiment!");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Title Guidelines:-\nProvide a brief concise yet descriptive title");
			System.out.println("Title Word Count: 100");
			System.out.println();
			System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Your Title: ");
			String exp_title = text.nextLine();
			String add = " "+ exp_title;
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"***********************************************************************************");
			//Counting the number of words in the experiment titlethe user've entered.
			StringBuilder exp_titlecount = new StringBuilder(exp_title);
			for(int i=0; i<add.length(); i++) {
				if(add.charAt(i) == ' ') {
					titleword_counter = titleword_counter + 1;
				}
			}
			int check_titlecapacity = exp_titlecount.capacity() - 16; //For counting characters in the StrinBuilder.
			
			while(check_titlecapacity > 600 || check_titlecapacity < 140) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in your experiment title.\nYou need to enter between 140 to 600 characters.\nYour word count is:- "+titleword_counter+"\n"+"Your character count is:- "+check_titlecapacity);
				System.out.println("*************************************************************************");
				//Asking the user to re-enter the title.
				System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Enter your title again: ");
				exp_title = text.nextLine();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
				add = " "+ exp_title;
				exp_titlecount.setLength(0);
				titleword_counter = 0;
				exp_titlecount = new StringBuilder(exp_title);
				check_titlecapacity = exp_titlecount.capacity() - 16;
				//Counting the number of words in the experiment titlethe user've entered.
				exp_titlecount = new StringBuilder(exp_title);
				for(int i=0; i<add.length(); i++) {
					if(add.charAt(i) == ' ') {
						titleword_counter = titleword_counter + 1;
					}
				}
				
			}
			if(check_titlecapacity >= 140 && check_titlecapacity <= 600) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+titleword_counter);
				title_acceptstatus = "valid";
			}
			
			
			//2. Statement of problem of the Lab Experiment.
			System.out.println();
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"**************************************************************************");
			System.out.println("Next You need to enter the statement of problem or reserarch question for your lab experiment!");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Statement Of Problem Guidelines:-\nWhat questions are you trying to answer\nProvide any priliminary background information about the subject");
			System.out.println("Statement Of Problem Word Count: 100");
			System.out.println();
			System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Your Statement Of Problem: ");
			String exp_problem = text.nextLine();
			String add2 = " "+ exp_problem;
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
			//Counting the number of words in the experiment Statement of problem the user've entered.
			StringBuilder exp_problemcount = new StringBuilder(exp_problem);
			for(int i=0; i<add2.length(); i++) {
				if(add2.charAt(i) == ' ') {
					Statement_prb_counter += 1;
				}
			}
			int check_problemcapacity = exp_problemcount.capacity() - 16; //For counting characters in the StrinBuilder.
			
			while(check_problemcapacity > 600 || check_problemcapacity < 140) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in the statement of problem.\nYou need to enter between 140 to 600 characterss.\nYour word count is:- "+Statement_prb_counter+"\n"+"Your character count is:- "+check_problemcapacity);
				//Asking the user to re-enter the statement of problem.
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
				System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Enter your statement of problem again: ");
				exp_problem = text.nextLine();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
				add2 = " "+ exp_problem;
				Statement_prb_counter = 0;
				exp_problemcount.setLength(0);
				exp_problemcount = new StringBuilder(exp_problem);
				check_problemcapacity = exp_problemcount.capacity() - 16;
				//Counting the number of words in the statement of problem the user've entered.
				exp_problemcount = new StringBuilder(exp_problem);
				for(int i=0; i<add2.length(); i++) {
					if(add2.charAt(i) == ' ') {
						Statement_prb_counter += 1;
					}
				}
				
			}
			if(check_problemcapacity >= 140 && check_problemcapacity <= 600) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+Statement_prb_counter);
				problem_acceptstatus = "valid";
			}
			Thread.sleep(2000);
			
			
            //3. Hypothesis of the Lab Experiment.
			System.out.println();
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"************************************************************************");
			System.out.println("Next You need to enter the hypothesis for your lab experiment!");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Hypothesis Guidelines:-\nWrite a prediction of what you think will be results of the experiment\nMake sure the soltution you've written is a complete sentence\nMAke sure the prediction statement is testable\nFinally, the prediction statement should referance the dependent, independent and control variables");
			System.out.println("Statement Of Problem Word Count: 100");
			System.out.println();
			System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Your Hypothesis: ");
			String exp_hypothesis = text.nextLine();
			String add3 = " "+ exp_hypothesis;
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"***********************************************************************");
			//Counting the number of words in the hypothesis the user've entered.
			StringBuilder exp_hypothesiscount = new StringBuilder(exp_hypothesis);
			for(int i=0; i<add3.length(); i++) {
				if(add3.charAt(i) == ' ') {
					hypothesis_counter += 1;
				}
			}
			int check_hypothesiscapacity = exp_hypothesiscount.capacity() - 16; //For counting characters in the StrinBuilder.
			
			while(check_hypothesiscapacity > 600 || check_hypothesiscapacity < 140) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in the hypothesis.\nYou need to enter between 140 to 600 characters.\nYour word count is:- "+hypothesis_counter+"\n"+"Your character count is:- "+check_hypothesiscapacity);
				System.out.println("***********************************************************************");
				//Asking the user to re-enter the hypothesis.
				System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Enter your hypothesis again: ");
				exp_hypothesis = text.nextLine();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"***********************************************************************");
				add3 = " "+ exp_hypothesis;
				Statement_prb_counter = 0;
				exp_problemcount.setLength(0);
				exp_hypothesiscount = new StringBuilder(exp_hypothesis);
				check_problemcapacity = exp_problemcount.capacity() - 16;
				//Counting the number of words in the statement of problem the user've entered.
				exp_problemcount = new StringBuilder(exp_problem);
				for(int i=0; i<add3.length(); i++) {
					if(add3.charAt(i) == ' ') {
						hypothesis_counter += 1;
					}
				}
				
			}
			if(check_hypothesiscapacity >= 140 && check_hypothesiscapacity <= 600) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+hypothesis_counter);
				hypoth_acceptstatus = "valid";
			}
			Thread.sleep(2000);
			
			
            //4. Materials used in the Lab Experiment.
			System.out.println();
			System.out.println("***********************************************************************");
			System.out.println("Now you need to write the materials used in the lab experiment");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Material Guidelines:-\nMake a list of the materials used for the lab experiment");
			System.out.println("Material Word Count: 150");
			System.out.println();
			System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"How many materials would you like to list out? ");
			int total_material = number.nextInt();
			List list1 = new List(List.ORDERED); //For ordered list.
			list1.setFirst(1); //Order will start from 1.
			for(int i=0; i<total_material; i++) {
				System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Enter material number "+(i+1) );
				material = text.nextLine();
				list1.add(material);
				add4 = " " + (i+1) + "." + " "+ material;
				
				for(int j=0; j<add4.length(); j++) {
					if(add4.charAt(j) == ' ') {
						materials_counter += 1;
					}
				}	
			}
			while( materials_counter > 150 || materials_counter < 25) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in the materials.\nYou need to enter between 25 to 150 words.\nYour word count is:- "+materials_counter);
				System.out.println("***********************************************************************");
				
				//Asking the user to re-enter the materials.
				materials_counter = 0;
				System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"How many materials would you like to list out? ");
				total_material = number.nextInt();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"***********************************************************************");
				list1 = new List(List.ORDERED); //For ordered list.
				list1.setFirst(1); //Order will start from 1.
				for(int i=0; i<total_material; i++) {
					System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Enter material number "+(i+1) );
					material = text.nextLine();
					list1.add(material);
					add4 = " " + (i+1) + "." + " "+ material;
					
					for(int j=0; j<add4.length(); j++) {
						if(add4.charAt(j) == ' ') {
							materials_counter += 1;
						}
					}	
				}		
			}
			
			if(materials_counter >= 25 || materials_counter <= 150) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+materials_counter);
				material_acceptstatus = "valid";
			}
			Thread.sleep(2000);
			
			
            //5. Method used for the Lab Experiment.
			System.out.println();
			System.out.println("***********************************************************************");
			System.out.println("Now you need to write the methods used in the lab experiment");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Method Guidelines:-\nWrite a paragraph or a list which explains what you did in the lab.\nYour procedure should be written so than anyone else could repeat the experiment.");
			System.out.println("Method Word Count: 150");
			System.out.println();
			System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"How many steps would you like to list out? ");
			int total_steps = number.nextInt();
			List list2 = new List(List.ORDERED); //For ordered list.
			list2.setFirst(1); //Order will start from 1.
			for(int i=0; i<total_steps; i++) {
				System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Enter step number "+(i+1) );
				method = text.nextLine();
				list2.add(method);
				add5 = " " + (i+1) + "." + " "+ method;
				
				for(int j=0; j<add5.length(); j++) {
					if(add5.charAt(j) == ' ') {
						method_counter += 1;
					}
				}	
			}
			while( method_counter > 150 || method_counter < 25) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in the method.\nYou need to enter between 25 to 150 words.\nYour word count is:- "+method_counter);
				System.out.println("*************************************************************************");
				
				//Asking the user to re-enter the steps of the method.
				method_counter = 0;
				System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"How many steps would you like to list out? ");
				total_steps = number.nextInt();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
				list2 = new List(List.ORDERED); //For ordered list.
				list2.setFirst(1); //Order will start from 1.
				for(int i=0; i<total_steps; i++) {
					System.out.println(ANSI_Colors.PURPLE_BOLD_BRIGHT+"Enter step number "+(i+1) );
					method = text.nextLine();
					list2.add(method);
					add5 = " " + (i+1) + "." + " "+ method;
					
					for(int j=0; j<add5.length(); j++) {
						if(add5.charAt(j) == ' ') {
							method_counter += 1;
						}
					}	
				}		
			}
			
			if(method_counter >= 25 || method_counter <= 150) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+method_counter);
				method_acceptstatus = "valid";
			}
			Thread.sleep(2000);
			
			//Adding the image of the lab experiment set up.
			System.out.println();
			System.out.println(ANSI_Colors.BLUE_BOLD_BRIGHT+"Enter the path for your lab experiment set up\nMake sure to the add image name and extention (in jpg, png)\nFor example:- C:\\Users\\nasri\\Documents\\IMAGES\\item3.png");
			String path = text.nextLine();
			//Getting the image from the location the user'll enter(which is this path) and store the image in the PDF file.
			Image image = Image.getInstance(path); //This method helps us to get the image from th path location.
			
			Thread.sleep(1000);
			
			
			//6. Data results for the lab experiment.
			System.out.println();
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"A Line Chart will be created based on your lab experiment data results");
			System.out.println();
			System.out.println(ANSI_Colors.BLACK_BOLD+"Enter the Main Title of your experiment: ");
			String chart_main = text.nextLine();
			System.out.println(ANSI_Colors.BLACK_BOLD+"Enter the Chart Title: ");
			String chart_title = text.nextLine();
			System.out.println(ANSI_Colors.BLACK_BOLD+"Enter the X-Axis Title: ");
			String xaxis_title = text.nextLine();
			System.out.println(ANSI_Colors.BLACK_BOLD+"Enter the Y-Axis Title: ");
			String yaxis_title = text.nextLine();
			
			//Creating and displaying the line chart.
			Lab_Report results = new Lab_Report(chart_main, chart_title, xaxis_title,yaxis_title);
			results.setAlwaysOnTop(true); //This methos puts the chart on th top of the window.
			results.pack(); //All the data is put properly and spread out evenly.
			results.setSize(500, 500);
			results.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			results.setVisible(true);
			
			//Taking Screenshot of the line chart and storing the screenshot in the location spection by user(path).
			Thread.sleep(2000);
			
			try {
				Robot robo = new Robot();
				System.out.println();
				System.out.println("Enter the file path for which you would like to store the image in");
				String path_ss = text.nextLine();
				System.out.println();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Please open the image of the item from your browser back up \nWait for 5seconds on the image so the screenshot can be taken	!");
				Thread.sleep(5000);
				
				Dimension dimension = new Dimension(500, 500);
				Rectangle capture = new Rectangle(dimension);
				BufferedImage Image = robo.createScreenCapture(capture);
				ImageIO.write(Image, "png", new File(path_ss));
				System.out.println();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"The image has been saved successfully!");
		
			}catch(Exception E) {
				E.printStackTrace();
			}
			
			//Asking the user again to give the path location of the screenshot image to be able to add it in the document later.
			System.out.println();
			String linechart_path;
			System.out.println(ANSI_Colors.BLACK_BOLD+"Enter the file location for the line chart image ");
			linechart_path = text.nextLine();
			Image image2 = Image.getInstance(linechart_path); //It's going to get that image for instance.
			
			
            //7. Conclusion of the Lab Experiment.
			System.out.println();
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
			System.out.println("Finally, you need to enter the conclusion for your lab experiment");
			System.out.println();
			System.out.println(ANSI_Colors.FOREST_GREEN+"Conclusion Guidelines:-\nYou need to state why you accepted or rejected your hypothesis using data from the lab\nInclude a summary of the data ~ averages, highest, lowest etc. to help the reader understand your results.");
			System.out.println("Title Word Count: 100");
			System.out.println();
			System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Your Conclusion: ");
			String exp_conclusion = text.nextLine();
			String add6 = " "+ exp_conclusion;
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"************************************************************************");
			//Counting the number of words in the experiment conclusion the user've entered.
			StringBuilder exp_conclusioncount = new StringBuilder(exp_conclusion);
			for(int i=0; i<add6.length(); i++) {
				if(add6.charAt(i) == ' ') {
					conclusion_counter += 1;
				}
			}
			int check_conclusioncapacity = exp_conclusioncount.capacity() - 16; //For counting characters in the StrinBuilder.
			
			while(check_conclusioncapacity > 600 || check_conclusioncapacity < 140) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered more or less number of words in your experiment conclusion.\nYou need to enter between 140 to 600 characters.\nYour word count is:- "+conclusion_counter+"\n"+"Your character count is:- "+check_conclusioncapacity);
				System.out.println("*************************************************************************");
				//Asking the user to re-enter the conclusion.
				System.out.println(ANSI_Colors.BLACK_BOLD_BRIGHT+"Enter your conclusion again: ");
				exp_conclusion = text.nextLine();
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"*************************************************************************");
				add6 = " "+ exp_conclusion;
				conclusion_counter = 0;
				exp_conclusioncount.setLength(0);
				exp_conclusioncount = new StringBuilder(exp_conclusion);
				check_conclusioncapacity = exp_conclusioncount.capacity() - 16;
				//Counting the number of words in the experiment conclusion the user've entered.
				exp_conclusioncount = new StringBuilder(exp_conclusion);
				for(int i=0; i<add6.length(); i++) {
					if(add6.charAt(i) == ' ') {
						conclusion_counter += 1;
					}
				}
				
			}
			if(check_conclusioncapacity >= 140 && check_conclusioncapacity <= 600) {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"You've entered acceptable number of words!");
				System.out.println("Your word count is "+conclusion_counter);
				conclusion_acceptstatus = "valid";
			}
			Thread.sleep(2000);
			
			
			//Copying all the information to the pdf file.
			if(title_acceptstatus.equals("valid") && problem_acceptstatus.equals("valid") && hypoth_acceptstatus.equals("valid") && material_acceptstatus.equals("valid") && method_acceptstatus.equals("valid") && conclusion_acceptstatus.equals("valid")) {
				try {
					document = new Document();
					writer = PdfWriter.getInstance(document, new FileOutputStream(labreport_title)); //First we need to create the document and assign the name of the document.
					document.open();
					Paragraph centeralign = new Paragraph(user_name); //Creating a object of Paragraph Class to center align the user_name(title of the lab report).
					centeralign.setAlignment(Element.ALIGN_CENTER);
					document.add(centeralign);
					Chunk underline = new Chunk(exp_title); //Creating a object of Chunk Class to underline the exp_title(title of the lab experiment).
					underline.setUnderline(0.8f, -1f); //Position of the underline.
					Paragraph centeralign2 = new Paragraph(underline); //Creating a object of Paragraph Class to center align the underline.
					centeralign2.setAlignment(Element.ALIGN_CENTER);
					document.add(centeralign2);
					
					//Start adding the titles(statement of problem,hypothesis,method etc.) and the informations under titles. 
					document.add(new Paragraph("\nStatement Of Problem:-\n" + exp_problem));
					document.add(new Paragraph("\nHypothesis:-\n" + exp_hypothesis));
					document.add(new Paragraph("\nMaterials:-\n"));
					document.add(list1);
					document.add(new Paragraph("\nMethods:-\n"));
					document.add(list2);
					document.newPage(); //Creating new page in the document.
					document.add(new Paragraph("\nLab Setup:-\n"));
					document.add(image);
					document.newPage();
					document.add(new Paragraph("\nData Results:-\n"));
					document.add(image2);
					document.newPage();
					document.add(new Paragraph("\nConclusion:-\n" + exp_conclusion));
					document.close();
					writer.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			else {
				System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Some of the information you've entered is invalid");
			}
		
			
			//Asking for creating another lab report pdf.
			titleword_counter = 0;
			Statement_prb_counter = 0;
			hypothesis_counter = 0;
			materials_counter = 0;
			method_counter = 0;
			conclusion_counter = 0;
			
			title_acceptstatus = "";
			problem_acceptstatus = "";
			hypoth_acceptstatus = "";
			material_acceptstatus = "";
			method_acceptstatus = "";
			conclusion_acceptstatus = "";
			
			System.out.println(ANSI_Colors.RED_BOLD_BRIGHT+"Would you like to start creating another lab reports(yes or no)? ");
			user_response = text.nextLine();
			
		}
		System.out.println(ANSI_Colors.GREEN_BOLD_BRIGHT+"Thank You!");
		
	}

}

