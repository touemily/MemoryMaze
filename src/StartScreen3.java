/**
 * This class is used to display the StartScreen3 and create a new MemoryMazeGUI that will start the Memory Maze game. 
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartScreen3 extends JFrame implements ActionListener {

	//ImageIcon constant for title of the screen
	private static final ImageIcon TITLE = new ImageIcon("images/choosegender.png");	

	//ImageIcon constant for JButton of both genders
	private static final ImageIcon CHOOSEMALE = new ImageIcon("images/male.png");	
	private static final ImageIcon GENDERMALE = new ImageIcon("images/malegender.png");
	private static final ImageIcon CHOOSEFEMALE = new ImageIcon("images/female.png");
	private static final ImageIcon GENDERFEMALE = new ImageIcon("images/femalegender.png");

	//Buttons for user to choose gender of their character
	JButton maleButton = new JButton(CHOOSEMALE);
	JButton femaleButton = new JButton(CHOOSEFEMALE); 

	//integer variable that will change when gender is chosen
	public static int charaNum;

	//StartScreen3 constructor
	public StartScreen3() {

		//1. Setup the JFrame
		setSize(1000, 690);	//sets the width and length of the frame
		setLayout(null);	//absolute positioning requires manual positioning
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//closes the frame if X is clicked

		//1.1 Setup the JLabel that will prompt the user to choose a gender
		JLabel imageLabel3 = new JLabel(TITLE); 
		imageLabel3.setBounds(373, 100, 260, 339);	//size and position of the Jlabel
		add(imageLabel3);

		//1.2 Setup the JLabel that will prompt the user to choose male
		JLabel imageLabel = new JLabel(GENDERMALE); 
		imageLabel.setBounds(180, 20, 97, 120);	//size and position of the JLabel
		add(imageLabel);

		//1.3 Setup the JLabel that will prompt the user to choose a female
		JLabel imageLabel2 = new JLabel(GENDERFEMALE); 
		imageLabel2.setBounds(725, 20, 97, 120);	//size and position of the JLabel
		add(imageLabel2);

		//1.4 Setup the JButton for male option
		maleButton.setBounds(150, 150, 150, 455);	//size and position of the JButton
		maleButton.addActionListener(this);	//activates the JButton
		add(maleButton);

		//1.5 Setup the JButton for female option
		femaleButton.setBounds(700, 150, 150, 455);	//size and position of the JButton
		femaleButton.addActionListener(this);	//activates the JButton
		add(femaleButton);		

		//2. Make JFrame visible
		setVisible(true);

	}

	//Detects when the user clicks the male or female JButton
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == maleButton) {
			setVisible(false);	//1. Frame will be invisible when the JButton is clicked
			charaNum = 1;	//2. the value 1 will change the title of image files in the Character class to male
			//3. Create a new MemoryMaze GUI
			new MemoryMazeGUI();
		}

		if (event.getSource() == femaleButton) {
			setVisible(false);	//1. Frame will be invisible when the JButton is clicked
			charaNum = 2;	//2. the value 2 will change the title of image files in the Character class to female
			//3. Create a new MemoryMaze GUI
			new MemoryMazeGUI();
		}

	}

}