/*
Name: Anthony Frazier
Date: October 23, 2014
Program Name: Lab6
Description: Ask the user for a starting, ending and incrementing number. Count from the start to end by the increment, while constantly checking for even or odd numbers. Output every check / count to screen.
*/

import java.util.Scanner;
public class Lab7
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);		// Declare scanner input
		System.out.println("This program will count numbers based on your input.");

		int end, increment,evenodd;					// Declare variables. Had to initialize values for start and current because of possible issues with initialization in loops.

		int start = 0;
		int current = 0;
		boolean check = true;


		while (check == true)						// First while loop can perform entire program multiple times based on user input
		{
			System.out.println("Would you like to count? Type yes or no");
			char cont = input.next().charAt(0);		// Check index of first char in entered string. if y, count. if anything else, exit program

			if (cont == 'y')
			{										// Request three user inputs
				System.out.println("Please input the positive non zero number you wish to start counting at.");
				start = input.nextInt();

				System.out.println("Please input the positive non zero number you wish to finish counting at.");
				end = input.nextInt();

				System.out.println("Please input the positive number you wish to count by.");
				increment = input.nextInt();
				current = start;					//Assign start value to counter value current

					while (current<=end)			//While counter <= end value, perform loop.
					{
						evenodd = current%2;		// By dividing by two repeatedly, I will either be left with 0 or 1 remainder. if 1, odd. if 0, even (problem : doesn't function for value 0)
						if (evenodd == 1)
						{
							System.out.println("The number "+current+" is odd.");
						}
						else
						{
					System.out.println("The number "+current+" is even.");
						}
						current = current + increment;	// Increment counter
					}
			}
			else
			{
				check = false;				// If user inputs anything other than y, end program
				System.out.println("Thanks for playing!");
			}
		}
	}
}