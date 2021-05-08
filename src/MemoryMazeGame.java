/**
 * Name: Emily Tou
 * Date: January 19, 2018
 * Course Code: ICS3U1-01 Mr.Fernandes
 * Title: Memory Maze
 * Description: sentences that describe your product
 * Features: Choosing Gender, Images Disappearing for a Certain Time, Image Appearing if Same Section is Clicked Two Times
 * Major Skills: Different image icons are displayed according to gender button clicked, timer for amount of time card is visible, 
 * checking if section images match, loading mazes only when corresponding section is clicked, 
 * sending locations of section panels to Section class, using image count variable to setIcons for walls, for statements, if statements, 
 * arrays
 * Areas of Concern: Errors in the code for KeyPressed in the section class. 
 * 					 The appearing of the section images when clicked is slow to catch up to mouse click. Click the images slowly.
 */

public class MemoryMazeGame {

	//Main method to run program and create a new GUI
	public static void main(String[] args) {
		//Create a new StartScreen
		new StartScreen();

	}
}