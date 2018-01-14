import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
/* Name: Anthony Frazier
 * Class: CSCE355
 * Due Date: November 21, 2017
 * Assignment: Programming Assignment.
 *
 * Determining properties of a DFA:
 * The sole command line argument gives the file describing
 * the DFA. Output is to standard output and consists of just two words separated by
 * whitespace: the first is either "empty" or "nonempty", and the second is either "finite" or
 * "infinite". Note that the language of a DFA is nonempty iff some final state is reachable
 * from the start state, and the language is infinite iff there is some cycle that is reachable from
 * the start state and from which a final state is reachable
*/
import java.util.Iterator;

public class MyProperties {
	
	
public static void main(String[] args) throws IOException
{	
	File inputFile1 = null;	
	DFA newDFA = new DFA();
	
	inputFile1 = new File(args[0]);
	
	newDFA = DFA.ScanDescription(inputFile1);
	boolean nonempty = isEmpty(newDFA);
	if (nonempty)
	{
		System.out.print("nonempty ");
	}
	else
	{
		System.out.print("empty ");
	}
	boolean infinite = isFinite(newDFA);
	if (infinite)
	{
		System.out.print("infinite");
	}
	else
	{
		System.out.print("finite");
	}
	
} // end main

public static boolean isEmpty(DFA dfa)
{
	// Create our reachable array
	boolean[] reachable = Reachable(dfa);
	// Initialize our arrays
	int[][] trans = dfa.getTransition();
	// After building our accepting array, for states that are not reachable, set all transitions = -1
	for (int i = 0; i < dfa.getStates(); i++)
	{
		if (reachable[i] == false)
		{
			System.err.println(i+" has been found to be unreachable. Setting all transitions to -1");
		}
		for (int j = 0; j < dfa.getAlphabet().length; j++)
		{
			if (reachable[i] == false)
			{
				trans[i][j] = -1;
			}	
		}
	}
	
	boolean nonempty = false;
	for(int i = 0; i < dfa.getStates(); i++)
	{
		for (int j = 0; j < dfa.getStates(); j++)
		{
			if(dfa.getAccepting()[j] != -1 && reachable[i] == true && dfa.getAccepting()[j] == i)
			{
				nonempty = true; // We can reach a final state
				System.err.println("We can reach final state "+dfa.getAccepting()[j]);
			}
		}
	}
	return nonempty;
}

public static boolean isFinite(DFA dfa)
{	// Create our reachable array
	boolean[] reachable = Reachable(dfa);
	
	// Initialize our arrays
	int[][] trans = dfa.getTransition();
	// After building our accepting array, for states that are not reachable, set all transitions = -1
	for (int i = 0; i < dfa.getStates(); i++)
	{
		if (reachable[i] == false)
		{
			System.err.println(i+" has been found to be unreachable. Setting all transitions to -1");
		}
		for (int j = 0; j < dfa.getAlphabet().length; j++)
		{
			if (reachable[i] == false)
			{
				trans[i][j] = -1;
			}	
		}
	}

/*
	boolean cycle = false;
	boolean infinite = false;
	boolean visited[] = new boolean[dfa.getStates()];
	for (int x = 0; x < dfa.getStates(); x++)
	{
		cycle = false;
		for (int i = 0; i < dfa.getStates(); i++)
		{
			for (int j = 0; j < dfa.getAlphaString().length(); j++)
			{
				visited = DFS(x, dfa);
				if (visited[dfa.getAccepting()[x]] && visited[x])
				{
					cycle = true;
					System.err.println("We found a cycle from "+dfa.getTransition()[i][j]+" to "+x);
				}
			}
		}
		
		if (cycle)
		{
			infinite = true;
			return infinite;
		}
	}
*/	
	boolean infinite = false;
	boolean cycle1 = false;
	boolean cycle2 = false;
	boolean[] tried = new boolean[dfa.getStates()];
	for (int x = 0; x < dfa.getStates(); x++)
	{
		cycle1 = false;
		cycle2 = false;
		for (int i = 0; i < dfa.getStates(); i++)
		{
			for (int j = 0; j < dfa.getAlphaString().length(); j++)
			{
				int middle = trans[i][j]; // this looks for an intermediate state as well
				/*if (middle == trans[x][j] && middle != -1 && middle != x && middle != i && tried[middle] == false)
				{
					for (int h = 0; h < dfa.getStates(); h++)
					{
						for (int m = 0; m < dfa.getAlphaString().length(); m++)
						{
							if ( x == trans[h][m])
							{
								cycle1 = true;
								cycle2 = true;
								System.err.println("A cycle has been found back to "+x);
							}
							
						}
						tried[h] = true;
					}
				}*/
				if ( x == trans[i][j])
				{
					cycle1 = true;
					System.err.println("A cycle has been found back to "+x);
				}
				
				if ( i == trans[x][j])
				{
					cycle2 = true;
					System.err.println("A cycle has been found back to "+i);
				}
				
				if (cycle1 && cycle2) // if we have found a cycle, we must get to a final state
				// We have established a cycle between states X and I. lets see if we can get from either of those to a final state
				{
					for(int y = 0; y < dfa.getAccepting().length; y++)
					{
						if (dfa.getAccepting()[y] != -1)
						{
							for (int z = 0; z < dfa.getStates(); z++)
							{
								for (int w = 0; w < dfa.getAlphaString().length(); w++)
								{
									int attempt = trans[x][w];
									if (attempt == dfa.getAccepting()[y] && dfa.getAccepting()[y] != -1)
									{
										infinite = true; // we have established a cycle and a path to a final state
										System.err.println("A path from "+x+" to accepting state "+dfa.getAccepting()[y]+" has been found");
									}
									attempt = trans[i][w];
									if (attempt == dfa.getAccepting()[y] && dfa.getAccepting()[y] != -1)
									{
										infinite = true; // we have established a cycle and a path to a final state
										System.err.println("A path from "+i+" to accepting state "+dfa.getAccepting()[y]+" has been found");
									}
								}
							}
						}
						else
						{
							y = dfa.getAccepting().length;
						}
					}
				cycle1 = cycle2 = false;
				} // end cycle1 cycle2 if
			}
		}
	}	
		/*if (cycle1 && cycle2) // if we have a cycle, we must get to a final state
		{
			for (int y = 0; y < dfa.getAccepting().length; y++)
			{
				for (int i = 0; i < dfa.getStates(); i++)
				{
					for (int j = 0; j < dfa.getAlphaString().length(); j++)
					{
						System.err.println("The accepting state we're trying to reach is: "+dfa.getAccepting()[]);
						if (dfa.getAccepting()[y] == trans[i][j] && dfa.getAccepting()[x] != -1)
						{
							System.err.println("We found a path to a final state");
							infinite = true;
							cycle = false;
							j = dfa.getAlphaString().length();
							i = dfa.getStates();
							x = dfa.getStates();
						}
					}
				}
		cycle = false;
		System.err.println("The cycle we found was no good");
		}	
	}
	*/
	return infinite;
} // end isInfinite function
public static boolean[] Reachable(DFA dfa) {
	
	// Get the total number of states and create a local transition table from our DFA
	int states = dfa.getStates();
	int[][] trans = dfa.getTransition();

	// Initialize a bool array, and set all except start state (0) equal to false
	boolean[] reachable = new boolean[states];
	reachable[0] = true;
	

	// I am going to run this ~3 times (magic number right now). My concern is the following:
	//   What happens if I say a state, 2 for example, is not reachable. Do I don't bother
	//   parsing its transition table. Now, in state 3, I discover that 2 is reachable. How
	//   do I go back and re add those? My solution is to wrap my structure in a while < 5
	//   because any transition I avoided in the first pass will now have reachable[]=true, thus
	//   we can add the new transitions. 
	
	
	int count = 0;
	while (count < 3)
	{	// Parse over the states. If a state is listed in the transition table,
		//  and the state we are currently in has a reached status of true,
		//  set the new found state as reached = true
		for (int i = 0; i < states; i++)
		{
			for (int j = 0; j < dfa.getAlphabet().length; j++)
			{
				if (reachable[i] == true)
				{
					int index = trans[i][j];
					reachable[index] = true;
					System.err.println("Transition: "+index+" is NOW reachable.");
				}
			}
		}
		System.err.println("\n\n");
		count++;
	}
	
	return reachable;
}

/*
public static boolean[] DFS (int root, DFA dfa)
{	// Attempting  DFS algorithm found online to cover cycles
	boolean visited[] = new boolean[dfa.getStates()];
	
	visited = Traverse(root, visited, dfa);
	return visited;
}

public static boolean[] Traverse(int root, boolean visited[], DFA dfa)
{
	visited[root] = true;
	System.err.println("We have visited " +root);
	
	for (int i = root; i < dfa.getStates(); i++)
	{
		for (int j = 0; j < dfa.getAlphaString().length(); j++)
		{
			int n = dfa.getTransition()[i][j];
			if (!visited[n])
			{
				Traverse(n, visited, dfa);
			}
		}
	}
	return visited;
}
*/
	
} // end class
