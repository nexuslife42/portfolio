/*
Name: Anthony Frazier
Date: October 23, 2014
Program Name: Lab8
Description: Lab Assignment 8: Methods

Attached is a Java program that asks the user to input a number of lines to be printed, the number of characters per line, and the character to be printed. The program then calls a method called printLines, whose job is to print lines on the screen matching the requirements that the user entered, and returning the total number of visible characters printed. The main procedure must capture that return value and print it in the format shown. For example, if the user wants 3 lines, 4 characters per line, using the $ character, the program should print on the screen:

$$$$
$$$$
$$$$
main reports 12 characters printed

Your job will be to write the definition of the printLines function/method that matches the context of the call in the main procedure. Use nested "while loops" to print the appropriate characters on the screen. The number of characters can be computed or generated by a counter. Use the supplied "driver" program as-is. Do not rewrite or change it! Note that the line-printing must be done entirely in the printLines method.

 Upload it to the D2L dropbox named Lab8.

Carefully read and adhere to the "coding standards" distributed in class
Provide a comment block like the one in our Hello program
Choose meaningful identifiers for all variables and constants
Use blank lines here and there to separate logical steps in your code
Use a one-line comment before each block to describe what you are doing � don�t describe the code syntax, but rather the purpose of the code. These comments should effectively describe the steps in your �algorithm.�
Spell things carefully, including capitalization, since Java is fussy about that
Indent things carefully, as per the standards document
Check your results!


Here is a sample run of the program, with user input underlined for emphasis:

How many lines do you want? 3
How many characters per line? 9
What character? +
+++++++++
+++++++++
+++++++++
main reports 27 characters printed.

Do another? (y/n) y
How many lines do you want? 5
How many characters per line? 20
What character? Z
ZZZZZZZZZZZZZZZZZZZZ
ZZZZZZZZZZZZZZZZZZZZ
ZZZZZZZZZZZZZZZZZZZZ
ZZZZZZZZZZZZZZZZZZZZ
ZZZZZZZZZZZZZZZZZZZZ
main reports 100 characters printed.

Do another? (y/n) n
Goodbye!
*/

import java.util.Scanner;
public class Lab8
{
		public static void main(String[] args)
  		 {
   			Scanner keyboard= new Scanner(System.in);
			String ans;
  		 	do
      		{
      			System.out.print("How many lines you want?  ");
     			int noLines=keyboard.nextInt();
    		 	System.out.print("How many characters per line?  ");
      			int charPerLine=keyboard.nextInt();
       		    System.out.print("What character?    ");
     		 	char theChar=keyboard.next().charAt(0);
     		 	int countx=printLines(noLines,charPerLine,theChar);


      			System.out.println("main reports "+countx+"  characters printed");
      			System.out.println("Do another?(y/n)  ");
      			ans=keyboard.next();
  		    }while(ans.equals("y"));

			System.out.println("GoodBye!!  ");
		}
		// Method definition
		public static int printLines(int numLines, int charLines, char cha)
		{
			int counter1 = 0, counter2 = 0;			// using two counters to count up
			int countx = (numLines * charLines);	// math to calculate countx
			while (counter1 < numLines)				// print number of lines
			{
				while (counter2 < charLines)		// print chars per line
				{
					System.out.print(cha);
					counter2++;
				}
				counter2 = 0;						// reset inner counter
				counter1++;
				System.out.println("");
			}
			return countx;							// return value to main method
		}



}