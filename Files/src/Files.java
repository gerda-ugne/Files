import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

/**
 * 
 * @author Gerda Ugne Pupelyte
 * @version 11/7/2019
 * 
 */
public class Files {

	private static final String crypt1 = "cipherabdfgjk";
	private static final String crypt2 = "lmnoqstuvwxyz";
	public Files() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Files file1 = new Files();
		
		file1.processUserChoices();
	}
	
/**
 * Method for running file tests
 */
	public void runFileTests()
	{
		System.out.println("Running file tests...");
		openFile();
		copyFiles();
		
	}
/**
 * Method that opens a file:
 * User provides the name of the file to be opened
 * The contents of file are displayed
 * 
 */
	public static void openFile()
	{
		//Variable to hold user's input
		String fileName;
		
		Scanner s = new Scanner(System.in);
		System.out.println("Please enter the file name that you wish to open: ");
		
		// User's input is stored in the local variable
		fileName = s.nextLine();
		
		//FileReader and BufferedReader for reading the file contents
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try
		{
			// An extension .txt is added at the end of the provided file name
			fileReader = new FileReader(fileName + ".txt");
			bufferedReader = new BufferedReader(fileReader);
			
			//First line of the file is read
			String nextLine = bufferedReader.readLine();
			while (nextLine!= null) // As long as there are more lines to read, the file is read
			{
				
				System.out.println(nextLine); // Read line is displayed on screen
				nextLine = bufferedReader.readLine();
			}
		}
		catch (IOException e) // In case of error, an error message is displayed
		{
			System.out.println("Error:" + e);
		}
		finally // BufferedReader is closed
		{
			try
			{
				if(bufferedReader != null ) bufferedReader.close();
			}
			catch (IOException e)
			{
				System.out.println("Error:" + e);
			}
		}
		
	}
	
	/**
	 * Method for writing to a text file:
	 * 
	 * User provides a name for a text file to be created
	 * User's input is taken and put into a text file
	 */
	public static void writeToFile()
	{
		//Variable to hold user's input
		String fileName;
				
		//FileOutputStream and PrintWriter for writing to file
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		try
		{
			Scanner s = new Scanner(System.in);
			System.out.println("Please provide a file name that you wish to write to: ");
			
			//A text file is created and named according to the user
			fileName = s.nextLine();
			fileName = fileName + ".txt";
			
			outputStream = new FileOutputStream(fileName);
			printWriter = new PrintWriter(outputStream);
			
			
			
			Scanner line = new Scanner(System.in);
			
			//Variable to hold a line of text that user inputs
			String nextLine;
			
			// User is prompted to enter a line of text
			System.out.println("Enter a line of text: ");
			nextLine = line.nextLine();
			
			//The text line is printed to the text file
			printWriter.print(nextLine + "\n");
			
			while(!nextLine.equals("")) // User's input is taken as long as input isn't empty
			{
				System.out.println("Enter the next line of text");
				nextLine = line.nextLine();
				
				
				//Line is printed to the text file
				printWriter.print(nextLine + "\n");
				
				
			}
		}
		
		catch (IOException e) // In case of error, an error message is displayed
		{
			System.out.println("Error" + e);
		}
		finally // PrintWriter is closed
		{
			if(printWriter != null) printWriter.close();
		}
	}
	/**
	 * Method for copying files:
	 * 
	 * Contents of one text file are copied into a new text file
	 */
	public static void copyFiles()
	{
		//Variables for reading file
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		//Variables for writing to file
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		try
		{
			String inputFileName;
			
			Scanner s = new Scanner(System.in);
			System.out.println("Please enter the file name from which you wish to copy the contents: ");
			
			// User is prompted to enter the file name from which they wish to copy
			inputFileName = s.nextLine() + ".txt";
			
			
			String outputFileName;

			System.out.println("Please provide a file name that you wish to write to: ");
			
			// User is prompted to enter the file name to which they wish to copy
			outputFileName = s.nextLine() + ".txt";
			
			fileReader = new FileReader(inputFileName);
			bufferedReader = new BufferedReader(fileReader);
			
			outputStream = new FileOutputStream(outputFileName);
			printWriter = new PrintWriter(outputStream);
			
			String nextLine = bufferedReader.readLine();
			while (nextLine!= null) // while file still has lines to read, the loop continues 
			{
				System.out.println(nextLine);
				// Line from the input file is read
				nextLine = bufferedReader.readLine();
				
				//If the line is empty, loop breaks
				if(nextLine == null) break;
				
				// The read line is put into the new text file
				printWriter.print(nextLine + "\n");
			}
				
		}
		catch (IOException e) //In case of error, an error message is displayed
		{
			System.out.println("Error" + e);
		}
		finally // PrintWriter and BufferedReader are closed
		{
			try
			{
				if(printWriter != null) printWriter.close();
				if(bufferedReader != null ) bufferedReader.close();
			}
			catch (IOException e)
			{
				System.out.println("Error" + e);
			}
		}
		
	}
	/**
	 * Method for deciphering a text file:
	 * 
	 * Input file mystery.txt is deciphered by using the method cipherdecipherString()
	 */
	public static void decipherFile()
	{
		//Variable for reading file
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		//Variables for writing to file
		FileOutputStream outputStream;
		PrintWriter printWriter = null;
		
		try
		{
			// File to decipher from
			fileReader = new FileReader("mystery.txt");
			bufferedReader =  new BufferedReader (fileReader);
			
			//File that will hold the deciphered text
			outputStream = new FileOutputStream ("deciphered.txt");
			printWriter = new PrintWriter (outputStream);
			
			// First line is read
			String nextLine = bufferedReader.readLine();
			String decipheredLine;
			
			//Line is deciphered using another method
			decipheredLine = cipherDecipherString(nextLine);
			
			//Line is printed to the file and displayed on the screen
			System.out.println(decipheredLine);
			printWriter.print(decipheredLine+"\n");
			
			while (nextLine!= null) // input is read as long there are more lines to read
			{
				// A line from mystery.txt is read
				nextLine = bufferedReader.readLine();
				
				if(nextLine == null) break; // if there are no lines to read, the loop breaks
				
				//The read line is deciphered using cipherDecipherString method
				decipheredLine = cipherDecipherString(nextLine);
				
				//Deciphered line is printed
				System.out.println(decipheredLine);
				
				//Deciphered line is put into the new text file
				printWriter.print(decipheredLine+"\n");
			}
		}
		catch (IOException e) // In case of error an error message is displayed
		{
			System.out.println("Error");
			
		}
		finally // PrintWriter and BufferedReader are closed
		{
			try
			{
				if(printWriter != null) printWriter.close();
				if(bufferedReader != null ) bufferedReader.close();
			}
			catch (IOException e)
			{
				System.out.println("Error" + e);
			}
		}
		
	}
	
	/**
	 * method to encipher and decipher a given String using parallel arrays (crypt1 & crypt2)
	 *
	 * @param text A String containing text that is to be enciphered or deciphered
	 * @return A new String containing the result, e.g. the en/deciphered version of the String provided as an input
	 */
	private static String cipherDecipherString(String text)
	{
	    // declare variables we need
	    int i, j;
	    boolean found = false;
	    String temp="" ; // empty String to hold converted text

	    for (i = 0; i < text.length(); i++) // look at every character in text
	    {
	        found = false;
	        if ((j = crypt1.indexOf(text.charAt(i))) > -1) // is char in crypt1?
	        {           
	            found = true; // yes!
	            temp = temp + crypt2.charAt(j); // add the cipher character to temp
	        } 
	        else if ((j = crypt2.indexOf(text.charAt(i))) > -1) // and so on
	        {
	            found = true;
	            temp = temp + crypt1.charAt(j);
	        }

	        if (! found) // to deal with cases where char is NOT in crypt2 or 2
	        {
	            temp = temp + text.charAt(i); // just copy across the character
	        }
	    }
	    return temp;
	}
	
	/**
	 * Method that processes the data of details.txt file
	 * Average score for each competitor is calculated 
	 * Results are displayed on screen and printed to average_score.txt file
	 */
	public void calculateAverageScore()
	{

		//Variables for reading the file contents
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		
		//Variables for writing to a file
		FileOutputStream outputStream;
		PrintWriter printWriter = null;
		
		try
		{
			fileReader = new FileReader("details.txt");
			bufferedReader = new BufferedReader(fileReader);
			
			outputStream = new FileOutputStream ("average_score.txt");
			printWriter = new PrintWriter (outputStream);
			
			//First line of the file is read
			String nextLine = bufferedReader.readLine();
			
			//Contents of the line are split into array elements
			String[] list = nextLine.split(" ");
			
			//Variable to hold the average score of each competitor
			float averageScore = 0f;
			//Declared decimal format to display results in #.00 format
			DecimalFormat df = new DecimalFormat("#.00");
			
			//Array to hold converted int values of Strings
			int[] scores = new int[5];
			
			
			for(int i=0; i<scores.length; i++)
			{
				//String score is converted into int
				scores[i] = Integer.parseInt(list[i+2]);
				averageScore = averageScore + scores[i];
				
			}
			
			//Average is calculated
			averageScore = averageScore/scores.length;
			
			//Results printed to file and displayed on screen
			System.out.println(list[1] + "," + list[0] + ":" + "Average score is " + df.format(averageScore));
			printWriter.println(list[1] + "," + list[0] + ":" + "Average score is " + df.format(averageScore));	
			
			
			while (nextLine!= null) // As long as there are more lines to read, the file is read
			{
	
				nextLine = bufferedReader.readLine();
				if(nextLine == null) break; //if line is empty, exit the loop
				
				
				list = nextLine.split(" "); //string is split
				
				averageScore = 0;
				
				for(int i=0; i<scores.length; i++)
				{
					//String is converted into int
					scores[i] = Integer.parseInt(list[i+2]);
					averageScore = averageScore + scores[i];
				}
				
				
				averageScore = averageScore/scores.length;
				
				//Results printed to file and displayed on screen
				System.out.println(list[1] + "," + list[0] + ": " + "Average score is " + df.format(averageScore));
				printWriter.println(list[1] + "," + list[0] + ": " + "Average score is " + df.format(averageScore));
				
			}
		}
		catch (IOException e) // In case of error, an error message is displayed
		{
			System.out.println("Error" + e);
		}
		finally // BufferedReader, PrintWriter are closed
		{
			try
			{
				if(bufferedReader != null ) bufferedReader.close();
				if(printWriter != null) printWriter.close();
			}
			catch (IOException e)
			{
				System.out.println("Error" + e);
			}
		}
		
	}
	
	/**
	 * Method that displays the available menu options
	 */
	public void displayMenu()
	{
		System.out.println("Please select one of the options below");
		System.out.println("1. Open and read from a text file");
		System.out.println("2. Write to a text file");
		System.out.println("3. Copy file's content to a new file");
		System.out.println("4. Decipher a text file");
		System.out.println("5. Calculate average scores");
		System.out.println("6. Write a 2D array to a file");
		System.out.println("7. Check if file exists and is readable");
		System.out.println("0. Exit");
	}
	
	
	/**
	 * Method that allows user to exit the program
	 */
	public void exit()
	{
		System.out.println("Goodbye");
	}
	
	
	/**
	 * Method that processes user's choices
	 * 
	 * User's input is read and the selected option is executed 
	 */
	public void processUserChoices()
	{
		// Variable to hold user's input
		String usersChoice;
		Scanner s = new Scanner(System.in);
		
		
		// Loop to keep the user selecting menu options until the exit option is chosen
		do
		{
			
		displayMenu();
		//User's input is compared and the selected choice is executed. 
		usersChoice = s.nextLine();
		
		
		//Choice 1 - Open and read from a text file
		if (usersChoice.equals("1"))
		{
			openFile();
			
		}
		
		// Choice 2 -  Write to a text file
		else if(usersChoice.equals("2"))
		{
			writeToFile();					
		}
		
		//Choice 3 - Copy file's content to a new file
		else if (usersChoice.equals("3"))
		{
			copyFiles();
		}
		
		
		//Choice 4 -  Decipher a text file
		else if (usersChoice.equals("4"))
		{
			decipherFile();
			
		}
		
		//Choice 5 - Calculate average scores
		else if (usersChoice.equals("5"))
		{
			calculateAverageScore();
			
		}
		
		//Choice 6 - Write a 2D array to a file
		else if(usersChoice.equals("6"))
		{
			writeToA2DArray();
		}
		
		//Choice 7 - Check if file exists and is readable
		else if(usersChoice.equals("7"))
		{
			Scanner s7 = new Scanner(System.in);
			System.out.println("Please enter the name of the file that you wish to check readabilty of:");
			
			String fileName = s7.nextLine();
			
			fileName = fileName + ".txt";
			checkIfReadable(fileName);
		}
		else if(usersChoice.equals("0"))
		{	
			//Calls the exit method 
			exit();
		}
		
		
		else 
		{
			//In case the user enters an invalid number, an error message is displayed
			System.out.println("An invalid option was selected.");
		}
		} while (!usersChoice.equals("0"));
		
	}
	
	/**
	 * Method to check if file exists and is readable
	 * @param fileName - string that contains the checked file's name
	 * @return canBeReadFrom - either true or false depending if file exists and is readable
	 */
	public boolean checkIfReadable(String fileName)
	{
		boolean canBeReadFrom = false;
		File file = new File(fileName);
		
		if(file.canRead() && file.isFile() )
		{
		    System.out.println("The file exists and can be read");
		    canBeReadFrom = true;
		    
		}else{
		    System.out.println("The file does not exist or is not readable");
		}
		
		
		
		return canBeReadFrom;
	}
	
	
	/**
	 * Method for writing 2D array to a file
	 */
	public void writeToA2DArray ()
	{
		int[][] numbers = { {2, 5, 6, 7},
							{4, 9, 10, 5},
							{9, 12, 1, 5}};
		
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		try
		{
			outputStream = new FileOutputStream("array.txt");
			printWriter = new PrintWriter(outputStream);
			
		
			
			
			for(int i=0; i<numbers.length;i++)
			{
				for (int j=0; j<numbers[i].length; j++)
				{
					printWriter.print(numbers[i][j] + " ");
				}
				
				printWriter.println();
			}
		}
		
		catch (IOException e) // In case of error, an error message is displayed
		{
			System.out.println("Error");
		}
		finally // PrintWriter is closed
		{
			if(printWriter != null) printWriter.close();
		}
		
		
	}
	

}
