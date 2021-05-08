/**
 * This class is used to display the StartScreen and create a new StartScreen2 that will explain the rules of the game.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScreen extends JFrame implements ActionListener {

	//ImageIcon constant for title of the game
	private static final ImageIcon memory = new ImageIcon("images/memory.png");

	//ImageIcon constant for title of the game
	private static final ImageIcon maze = new ImageIcon("images/maze.jpg");

	//ImageIcon constant for background
	private static final ImageIcon BACKGROUND = new ImageIcon("images/background.png");

	//Button for user to move on to learn how to play
	JButton howtoplayButton = new JButton();

	//StartScreen constructor
	public StartScreen() {

		//1. Setup the JFrame
		setSize(800, 522);	//sets the width and length of the frame
		setLayout(null);	//absolute positioning requires manual positioning
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//closes the frame if X is clicked		

		//1.1 Setup JLabel for the title memory
		JLabel imageLabel = new JLabel(memory); 
		imageLabel.setBounds(250, 70, 300, 96);	//size and position of the JLabel
		add(imageLabel);

		//1.2 Setup JLabel for the title maze
		JLabel imageLabel2 = new JLabel(maze); 
		imageLabel2.setBounds(250, 145, 300, 110);	//size and position of the JLabel
		add(imageLabel2);

		//1.3 Setup JLabel for the background
		JLabel imageLabel3 = new JLabel(BACKGROUND); 
		imageLabel3.setBounds(0, 0, 800, 522);	//size and position of the JLabel
		add(imageLabel3);

		//1.4 Setup JButton for player to press to move on to next screen
		howtoplayButton.setBounds(300, 310, 200, 50);	//size and position of the JButton
		howtoplayButton.setBackground(Color.cyan);	//sets the colour to cyan
		howtoplayButton.addActionListener(this);	//activates the JButton
		howtoplayButton.setText("HOW TO PLAY");	//displays text on JButton
		howtoplayButton.setFont(new Font("Barlow", Font.ROMAN_BASELINE, 18));	//sets font of text on JButton
		add(howtoplayButton);

		//2. Make JFrame visible
		setVisible(true);

		//3. Play music clip
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("sounds/strings2.wav")));
			clip.start();
		}
		catch (Exception exc) {
			exc.printStackTrace(System.out);
		}

	}

	//Detects when the user clicks the howtoplayButton
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == howtoplayButton) {
			setVisible(false);	//1. Frame will be invisible when the JButton is clicked
			//2. Create a new StartScreen2
			new StartScreen2();
		}

	}
}