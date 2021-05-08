/**
 * This class is used to display the StartScreen2 and create a new Startscreen3 that allow the user to choose a gender.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScreen2 extends JFrame implements ActionListener {

	//ImageIcon constant for title of screen
	private static final ImageIcon INSTRUCTIONS = new ImageIcon("images/howtoplay.png");

	//ImageIcon constant for background
	private static final ImageIcon BACKGROUND2 = new ImageIcon("images/background2.png");

	//Button for user to click to continue to next screen
	JButton continueButton = new JButton();

	//StartScreen2 constructor
	public StartScreen2() {

		//1. Setup the JFrame
		setSize(900, 503);	//sets the width and length of the frame
		setLayout(null);	//absolute positioning requires manual positioning
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//closes the frame if X is clicked

		//1.1 Setup JLabel for the title
		JLabel imageLabel = new JLabel(INSTRUCTIONS); 
		imageLabel.setBounds(350, 30, 150, 84);	//size and position of the JLabel
		add(imageLabel);

		//1.2 Setup JLabel for the text instructions for playing the game
		JLabel textLabel = new JLabel(); 
		textLabel.setBounds(50, 120, 780, 200);	//size and position of the JLabel
		textLabel.setForeground(Color.red);
		textLabel.setFont(new Font("Barlow", Font.ROMAN_BASELINE, 18));
		textLabel.setText("<html>" + "Click a card to reveal a section of the maze. If another card is clicked and the images of the maze "
				+ "<br />" + "match, you will gain a point. If they do not match, they will flip back to being hidden. During the matching "
				+ "process, you will continue through the maze to the finish line using the arrow keys. Watch out for mines, volcanos, "
				+ "cacti, and fish");
		add(textLabel);

		//1.3 Setup JLabel for background
		JLabel imageLabel2 = new JLabel(BACKGROUND2); 
		imageLabel2.setBounds(0, 0, 900, 503);	//size and position of the JLabel
		add(imageLabel2);

		//1.4 Setup JLabel for continueButton that play clicks to move on to next screen
		continueButton.setBounds(330, 300, 200, 50);	//size and position of the JButton
		continueButton.setBackground(Color.cyan);	//sets the colour to cyan
		continueButton.setText("CONTINUE");
		continueButton.addActionListener(this);	//activates the JButton
		continueButton.setFont(new Font("Barlow", Font.ROMAN_BASELINE, 18));
		add(continueButton);

		//2. Make JFrame visible
		setVisible(true);

	}

	//Detects when the user clicks the continueButton
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == continueButton) {
			setVisible(false);	//1. Frame will be invisible when the JButton is clicked
			//2. Create a new Startscreen3
			new StartScreen3();
		}

	}
}