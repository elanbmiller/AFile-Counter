import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class wordcount {
	//-----------------------------------------------------------------------------
	//Counts number of words, lines, etc based on a string theFile and a string pattern, which is what will be counted in the file
	//Precondition: The main method will prompt the user for a file, which will be converted to String theFile
	//Postcondition: count method will split up the file line by line based on vowels or punctuation--whatever the user 
	//chooses as the String pattern, and it will return an integer value of the number of lines after the splits
	//-----------------------------------------------------------------------------
	public static int count(String theFile, String pattern){
		return theFile.split(pattern).length;
	}

	public static void main(String[] args) throws FileNotFoundException {

		//user is prompted to enter a file, which gets inputted into a scanner scan.
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the File:");
		File file = new File(input.nextLine());
		Scanner scan = new Scanner(new FileInputStream(file));


		//simply tests if the file is empty and exits if so
		if(!scan.hasNext()){
			
			System.out.println("The input file is empty");
			
			System.exit(0);
		}


		//String theFile is to be a string copy of the file

		String theFile = "";

		while(scan.hasNext()){
			
			theFile += scan.nextLine() + "\n";
		
		}
		scan.close();


		//integer values of the various things to be counted (punctuation, words, etc.)
		//Each use of the count method takes theFile and splits it up by whatever "pattern is chose", and the subsequent length is then returned
		int words = count(theFile, "[ \t\n\\p{Punct}]+");
		int lines = count(theFile, "[\n\r]");
		int vowels = count(theFile.toLowerCase(), "[aeiou]" ) -1;
		int alphanumeric = count(theFile, "\\p{Alnum}") -1 ;
		int sentences = count(theFile, "[\\.!?]") - 1;
		int punctuation = count(theFile, "\\p{Punct}") - 1;

		//Creates a string of the amount words, lines, vowels, alphanumeric characters, sentences and punctuation in the file to then be printed
		String output = String.format(" Words: %d\n\r" + " Lines: %d\n\r"
				+ " Vowels: %d\n\r" + " Alphanumeric: %d\n\r"
				+ " Sentences: %d\n\r" + " Punctuation: %d\n\r", 
				words, lines, vowels, alphanumeric, sentences, 
				punctuation);

		System.out.println(output);


		//creates file output.txt and fills it with data from String output
		File outputFile =  new File("output.txt");
		PrintWriter printer = new PrintWriter(new FileOutputStream(outputFile));
		printer.println(output);
		printer.flush();
		printer.close();
	}
}


