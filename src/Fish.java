/** This class is used to create fish objects.
 * It includes a constant ImageIcon array to hold the various fish pictures
 * and a constructor method that sets the Fishes' images
 */

import javax.swing.ImageIcon;


public class Fish extends Mover{
	/**
	 * creates an array of ImageIcons representing 9 Fishes
	 */
	public static final ImageIcon[] IMAGE = {

			new ImageIcon("images/Fish1.png"),
			new ImageIcon("images/Fish2.png"),
			new ImageIcon("images/Fish3.png"),
			new ImageIcon("images/Fish4.png"),
			new ImageIcon("images/Fish5.png"),
			new ImageIcon("images/Fish6.png"),
			new ImageIcon("images/Fish7.png"),
			new ImageIcon("images/Fish8.png"),
			new ImageIcon("images/Fish9.png")
	};

	/**
	 * fish constructor
	 * gNum fish number - 0, 1 or 2
	 */
	public Fish(int gNum) {

		this.setIcon(IMAGE[gNum]);

	}

}