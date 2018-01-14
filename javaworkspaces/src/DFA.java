/* Name: Anthony Frazier
 * Class: CSCE355
 * Due Date: November 21, 2017
 * Assignment: Programming Assignment.
 * 
 * This is a DFA class I have created that will contain all of the pertinent information that we have gained
 * From parsing over a text file description of a DFA. It contains:
 * int[][] transition that keeps track of our transition table
 * int[] accepting that keeps track of our accepting states (we dummy initialize all values to ACCEPTING_DUMMY.
 * char[] alphabet that keeps track of our valid alphabet
 * int states that keeps track of the total number of states in our DFA (0...states-1)
 * 
 * (May need to rewrap all of this information into a file as output?) 
 * DFA Description Format: If you write a DFA as output, you must use this format. This allows flexibility of using the output of one
 * program as the input to another.
 * Number of states: 5
 * Accepting states: 1 4
 * Alphabet: 01
 * 0 1
 * 2 3
 * 4 0
 * 1 2
 * 3 4
 * 

 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class DFA {
	
	private int[][] transition;
	private int[] accepting;
	private char[] alphabet;
	private int states;
	private String alphaString;
	
	static final int ACCEPTING_DUMMY = -1;
	
	DFA()
	{
		this.transition = null;
		this.accepting = null;
		this.alphabet = null;
		this.states = 0;
	}
	public int[][] getTransition() { 
		return transition;
	}
	public void setTransition(int[][] transition) {
		this.transition = transition;
	}
	public int[] getAccepting() {
		return accepting;
	}
	public void setAccepting(int[] accepting) {
		this.accepting = accepting;
	}
	public char[] getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(char[] alphabet) {
		this.alphabet = alphabet;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	public String getAlphaString() { 
		return alphaString;
	}
	public void setAlphaString(String alphaString) { 
		this.alphaString = alphaString;
	}


	public static DFA ScanDescription(File inputFile) throws IOException
{	//Set up error output. (This code is directly copied from a stack overflow example)
		
	FileOutputStream f = new FileOutputStream("error_log.txt");	 
	System.setErr(new PrintStream(f));
	
	//Initialize all local variables
			
	DFA returningDFA = new DFA();
	Scanner input= new Scanner(inputFile);
	
	int[][] transition = null;
	int[] accepting = null;
	char[] alphabet = null;
	int state = 0;
	String token = "";
	
	// ** First line (single, positive decimal int): Number of states: n. DFA will have n states, with Q= {0,...,n-1}
	// Parse over the entire first line, cut from the string the unimportant portions
	// It is important to note that in a n state DFA, we will have states 0 -> n-1
	
	state = Integer.parseInt(input.nextLine().replaceAll("Number of states: ", ""));
	//System.err.println("\\n The number of states in this dfa is: " + state + "\n");

	/*  Second line(list of non-negative int): List of states that are accepting. 
	*     Numbers separated by whitespace, in increasing order.	
	* 	   Remove from the line everything up to the numbers i.e. removes Accepting states: 
	*/
	
	input.useDelimiter(":").next();
	input.useDelimiter("\\s").next();
	
	// Create our accepting array and parse next line
	accepting = new int[state];
	for (int i = 0; i < accepting.length; i++)
	{
		accepting[i] = ACCEPTING_DUMMY;
	}
	System.err.println("This is our accepting array: ");
	// Parse over the line(s) until we hit "Alphabet:". Put each int value into the accepting array as an accepting state.
	// I have tried multiple iterations of this to stop at the \n value, but that refuses to work. So using "Alphabet:" as
	//   sentinel value instead.
	for (int i = 0; i < state; i++)
	{
		token = input.useDelimiter("\\s").next();
		if (token.equals("Alphabet:"))
		{
			i = state;
		}
		else
		{
			accepting[i] = Integer.parseInt(token);
			System.err.print(accepting[i] + "\n");
		}	
	}
	// ** Third line: Alphabet: followed by a string of chars starting with the first char after the space after the first
	//  colon, and runs through the end of the line (not including final newline).
	//  Alphabet is ascii, no carriage returns, line feeds, new lines, etc. Space is allowed. ASCII 32-126 are allowed.
	
	// Read in the entire line, remove leading space, and create alphabet[] array
	String alphaString = input.nextLine();
	alphaString = alphaString.substring(1, alphaString.length());
	alphabet = new char[alphaString.length()];
	
	System.err.println("This is the alphabet string: " + alphaString + "\n");

	for(int x = 0; x < alphaString.length(); x++)
	{
		alphabet[x] = alphaString.charAt(x);
		System.err.print(alphabet[x]);
	}
	
	Arrays.sort(alphabet);
	// ** Remaining Lines: Transition table: rows of the table are terminated with a newline character.
	//      Each row consists of a sequence of nonnegative int seperated by whitespace, one number for each alphabet symbol.
	// Create our dfa array based on the number of states
	transition = new int[state][alphabet.length];
	
	for (int i = 0; i < state; i++) 
	{
		//System.err.println("State " + i + ": " + "\n");
		for (int j = 0; j < alphabet.length; j++)
		{
			transition[i][j] = input.nextInt();
			//System.err.print(alphabet[j] + " transition: " + transition[i][j] + " " + "\n");
		} 
		
		input.nextLine();
	}
	
	returningDFA.setTransition(transition);
	returningDFA.setAccepting(accepting);
	returningDFA.setAlphabet(alphabet);
	returningDFA.setStates(state);
	returningDFA.setAlphaString(alphaString);
	return returningDFA;
	
	} // end method

} // end class