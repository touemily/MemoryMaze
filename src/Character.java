/**
 * This class is used to create a Character object. It extends the Mover class and 
 * includes an array of ImageIcons constants for the various states of Character.
 * A constructor sets the initial image of Character.
 */

import javax.swing.ImageIcon;

public class Character extends Mover {

	//integer variable that will determine which image files the character will show
	public int number;
	
	//Creates an array of ImageIcons representing various states of Character walking
	public ImageIcon[] image = new ImageIcon[4];

	//Character constructor
	public Character(int number){
		
		//the value of number depends on which gender button the user clicked from MemoryMazeGUI
		this.number = number;

		//ImageIcons for when character is facing different directions
		image[0] = new ImageIcon("images/" + number + "Left.png");
		image[1] = new ImageIcon("images/" + number + "Up.png");
		image[2] =  new ImageIcon("images/" + number + "Right.png");
		image[3] =  new ImageIcon("images/" + number + "Down.png");
	
		this.setIcon(image[2]); //set Character to facing right when game starts

	}

}