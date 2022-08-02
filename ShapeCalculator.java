import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ShapeCalculator {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the file name for Shape data input");
		System.out.println("Enter nothing if you want all text files in current directory to be processed.");
		System.out.print("Enter here: ");

		String fileName = input.nextLine();
		fileName = fileName.trim();
		input.close();

		String path = ".";

		if(fileName.length() > 0) {	//Check if a file name was typed
			if(!fileName.toLowerCase().endsWith(".txt"))	//Check if the file name as the extension
				fileName = fileName.concat(".txt");	//Adds the ".txt" file extension
			path += "\\" + fileName;	//Gets the file from the directory where the jar file is.

			System.out.println("\n"+fileName);	//Prints the text file that is being processed
			processShapeFile(new File(path));	//Processes the file for Shape Data
		} else {
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles(new TextFileFilter());	//Gets all text files in the current directory

			for (File myFile : listOfFiles) {	//Processes all text files in the current directory
			    System.out.println("\n"+myFile.getName());	//Prints the text file that is being processed
				processShapeFile(myFile);
			}
		}
	}

	private static void processShapeFile(File folder) {
		try {	//Catches all errors
		    BufferedReader lineReader = new BufferedReader(new FileReader(folder));
		    String lineText = null;

		    System.out.printf("%-9s %10s %10s\n","Shape","Perimeter","Area");	//Uses similar Padding to the Challenge documentation

		    while ((lineText = lineReader.readLine()) != null) {	//Gets all the lines in the text file
		    	if(lineText.equals("END") || lineText.charAt(0) == '#')	//If the line is the EMD or a comment, skip the file
		    		continue;

		    	String[] lineSplit = lineText.trim().split(" ");	//Splits the space separated string
		    	calculateShape(lineSplit);
		    }

		    lineReader.close();
		} catch (Exception e) {	//Catches IO errors and anything else too
		    System.err.println(e);
		}
	}

	private static void calculateShape(String[] lineSplit) {
		switch(lineSplit[0]) {
			case "C":
				try {
					double d1 = Double.parseDouble(lineSplit[1]);	//All of the input doubles should be positive, the absolute can be used to ensure this is the case.
					System.out.printf("%-9s %10.2f %10.2f\n","Circle",Circle.computePerimeter(d1),Circle.computeArea(d1));	//Uses the static methods to calculate

				}	catch(Exception e){	//Catches errors from the Double conversion and array access.
					System.err.println(e);
				}
				break;
			case "S":
				try {
					double d1 = Double.parseDouble(lineSplit[1]);
					System.out.printf("%-9s %10.2f %10.2f\n","Square",Square.computePerimeter(d1),Square.computeArea(d1));

				}	catch(Exception e){
					System.err.println(e);
				}
				break;
			case "E":
				try {
					double d1 = Double.parseDouble(lineSplit[1]);
					double d2 = Double.parseDouble(lineSplit[2]);
					System.out.printf("%-9s %10.2f %10.2f\n","Ellipse",Ellipse.computePerimeter(d1, d2),Ellipse.computeArea(d1, d2));

				}	catch(Exception e){
					System.err.println(e);
				}
				break;
			case "R":
				try {
					double d1 = Double.parseDouble(lineSplit[1]);
					double d2 = Double.parseDouble(lineSplit[2]);
					System.out.printf("%-9s %10.2f %10.2f\n","Rectangle",Rectangle.computePerimeter(d1, d2),Rectangle.computeArea(d1, d2));

				}	catch(Exception e){
					System.err.println(e);
				}
				break;
			case "T":
				try {
					double d1 = Double.parseDouble(lineSplit[1]);
					double d2 = Double.parseDouble(lineSplit[2]);
					double d3 = Double.parseDouble(lineSplit[3]);
					System.out.printf("%-9s %10.2f %10.2f\n","Triangle",Triangle.computePerimeter(d1, d2, d3),Triangle.computeArea(d1, d2, d3));

				}	catch(Exception e){
					System.err.println(e);
				}
				break;
			default:	//If the text file didn't have the expected Shape Character
				System.out.println("File doesn't have the expected Shape Data Input");
		}
	}

}
