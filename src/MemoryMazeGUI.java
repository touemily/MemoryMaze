/*
 * This class creates a MemoryMaze GUI that extends the JFrame class. It has 6 sections (JPanels)
 * and a constructor method that sets up the frame and adds MouseListener to the sections.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import javax.swing.*;

public class MemoryMazeGUI extends JFrame implements MouseListener, ActionListener {

	//Array of 6 Section panels
	private Section[] section = new Section[6];

	//Array of 6 JLabels to hold section images
	private JLabel[] image = new JLabel[6];

	//ImageIcon constant for section images
	private static final ImageIcon SECTION = new ImageIcon("images/section.png");

	//ImageIcon constant for start image that indicates where the character is starting
	private static final ImageIcon START = new ImageIcon("images/Start.png");

	//ImageIcon constant for match image that indicates correct match
	private static final ImageIcon MATCH = new ImageIcon("images/match.png");

	//ImageIcon constant for wrong image that indicates incorrect match
	private static final ImageIcon WRONG = new ImageIcon("images/wrong.png");

	//ImageIcon constant for lost image that indicates the end of the game
	private static final ImageIcon LOST = new ImageIcon("images/lost.png");

	//JLabel that will hold the start image
	private JLabel startLabel = new JLabel(START);

	//JLabel that will hold the match image
	private JLabel matchLabel = new JLabel(MATCH);

	//JLabel that will hold the wrong image
	private JLabel wrongLabel = new JLabel(WRONG);

	//JLabel that will hold the lost image
	private JLabel lostLabel = new JLabel(LOST);

	//JLabel that will display the number of lives
	private JLabel livesLabel = new JLabel();

	//JLabel that will display the number of points
	private JLabel pointsLabel = new JLabel();

	//keeps track of the number of clicks
	int numClicks = 0;

	//user are allowed to click 8 times
	int lives = 8;

	//user starts with 0 points
	int points = 0;

	//keeps track of first to eight clicks
	int firstClick = 0;
	int secondClick = 0;
	int thirdClick = 0;
	int fourthClick = 0;
	int fifthClick = 0;
	int sixthClick = 0;
	int seventhClick = 0;
	int eighthClick = 0;

	//MemoryMaze GUI constructor
	public MemoryMazeGUI() {

		//1. Set up the GUI
		setSize(1000, 690);
		setTitle("Memory Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		//1.1 Set up the start JLabel that will indicate where the character is starting
		startLabel.setBounds(10, 30, 70, 66);	//size and position of the JLabel
		add(startLabel);

		//1.2 Set up the match JLabel that will display "It's a match"
		matchLabel.setBounds(150, 100, 620, 329);	//size and position of the JLabel
		add(matchLabel);
		matchLabel.setVisible(false);

		//1.3 Set up the wrong JLabel that will display "Wrong"
		wrongLabel.setBounds(300, 150, 380, 253);	//size and position of the JLabel
		add(wrongLabel);
		wrongLabel.setVisible(false);

		//1.4 Set up the lost JLabel that will display "You Lose"
		lostLabel.setBounds(120, 100, 736, 407);	//size and position of the JLabel
		add(lostLabel);
		lostLabel.setVisible(false);

		//1.5 Set up the livesLabel that will display the number of lives remaining
		livesLabel.setFont(new Font("Barlow", Font.ROMAN_BASELINE, 18));
		livesLabel.setForeground(Color.MAGENTA);
		livesLabel.setBounds(410, 10, 100, 25);	//size and position of the JLabel
		livesLabel.setText("Lives: " + lives);
		add(livesLabel);

		//1.6 Set up the pointsLabel that will display the number of points earned
		pointsLabel.setFont(new Font("Barlow", Font.ROMAN_BASELINE, 18));
		pointsLabel.setForeground(Color.ORANGE);
		pointsLabel.setBounds(510, 10, 100, 25);	//size and position of the JLabel
		pointsLabel.setText("Points: " + points);
		add(pointsLabel);

		//1.7 Each of the 6 JLabels will display the section image
		for (int v = 0; v < 6; v++) {

			image[v] = new JLabel(SECTION);

		}

		//1.8 Position of 6 sections is sent to the class Section
		section[0] = new Section(60,30);
		section[1] = new Section(340,30);
		section[2] = new Section(620,30);
		section[3] = new Section(60,325);
		section[4] = new Section(340,325);
		section[5] = new Section(620,325);

		//1.9 Position and size of 6 JLabels
		image[0].setBounds(60, 30, 290, 300);
		image[1].setBounds(340, 30, 290, 300);
		image[2].setBounds(620, 30, 290, 300);
		image[3].setBounds(60, 325, 290, 300);
		image[4].setBounds(340, 325, 290, 300);
		image[5].setBounds(620, 325, 290, 300);

		//2. Adding KeyListener and MouseListener to each of the 6 sections
		for (int v = 0; v < 6; v++) {

			addKeyListener(section[v]);
			section[v].addMouseListener(this);
			add(image[v]);	//adding the 6 image JLabels
			add(section[v]);	//adding the sections

		}

		//3. Make GUI visible
		setVisible(true);

	}

	public void mouseClicked(MouseEvent e) {

		//increases by 1 each time a mouse clicks a section panel
		numClicks++;

		//number of lives decrease each time a user clicks on a section panel
		lives--;
		livesLabel.setText("Lives: " + lives);

		//1. If the user lose all their lives
		if (lives == 0 ) {

			//1.1 Make the lostLabel visible for 2 seconds
			lostLabel.setVisible(true);	

			int delay = 2000; //delay for 2000 milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//1.2 Make GUI invisible
					setVisible(false);
					//1.3 Create the first StartScreen
					new StartScreen();
				}
			};
			new Timer(delay, taskPerformer).start();

		}

		//2. If the number of clicks is 1
		if (numClicks == 1) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {

					//2.1 Load the section that was clicked
					section[v].loadSection(v);
					//2.2 Make the section image invisible
					image[v].setVisible(false);
					//2.3 Make firstClick equal to the number of the section clicked
					firstClick = v;

				}
			}
		}

		//3. If the number of clicks is 2
		if (numClicks == 2) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {

					//3.1 Load the section that was clicked
					section[v].loadSection(v);
					//3.2 Make the section image invisible
					image[v].setVisible(false);
					//3.3 Make secondClick equal to the number of the section clicked
					secondClick = v;

				}
			}

			//3.4 If the first section that was clicked is clicked again
			if(firstClick == secondClick) {

				//3.4.1 Make the section image visible to hide the maze
				image[firstClick].setVisible(true);

			}

			//3.5 If the absolute difference between secondClick and firstClick is 3 
			if(Math.abs(secondClick - firstClick) == 3) {

				//3.5.1 Then the sections match and increment the number of points
				points++;
				pointsLabel.setText("Points: " + points);
				//3.5.2 Remove MouseListener for the matched sections so they cannot be chosen again
				section[firstClick].removeMouseListener(this);
				section[secondClick].removeMouseListener(this);
				//3.5.3 Remove the section images to reveal the maze
				remove(image[firstClick]);
				remove(image[secondClick]);
				//3.5.4 Make the matchLabel visible for 1 second
				matchLabel.setVisible(true);

				//timer to time a second before the matchLabel is invisible
				int delay = 1000;  //delay for 1000 milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//3.5.5 Make the matchLabel invisible after 1 second
						matchLabel.setVisible(false);
					}
				};
				new Timer(delay, taskPerformer).start();
			}

			//3.6 If the absolute difference between secondClick and firstClick is not 3
			if(Math.abs(secondClick - firstClick) != 3) {

				//3.6.1 Then the sections do not match and the wrongLabel will be visible
				wrongLabel.setVisible(true);

				//timer to time a second before the section images hide the two mazes again and the wrongLabel is invisible
				int delay2 = 1000; //delay for 1000 milliseconds
				ActionListener taskPerformer2 = new ActionListener() {
					public void actionPerformed(ActionEvent evt2) {
						//3.6.2 Section images will be visible to hide the mazes after a second
						image[firstClick].setVisible(true);
						image[secondClick].setVisible(true);
						//3.6.3 wrongLabel will be invisible after a second
						wrongLabel.setVisible(false);

					}
				};
				new Timer(delay2, taskPerformer2).start();
			}

		}

		//4. If the number of clicks is 3
		if (numClicks == 3) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {
					//4.1 Load the section that was clicked
					section[v].loadSection(v);	
					//4.2 Make the section image invisible
					image[v].setVisible(false);
					//4.3 Make thirdClick equal to the number of the section clicked
					thirdClick = v;

				}
			}
		}			

		//5. If the number of clicks is 4
		if (numClicks == 4) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {
					//5.1 Load the section that was clicked
					section[v].loadSection(v);
					//5.2 Make the section image invisible
					image[v].setVisible(false);
					//5.3 Make fourthClick equal to the number of the section clicked
					fourthClick = v;

				}
			}

			//5.4 If the third section that was clicked is clicked again
			if(thirdClick == fourthClick) {

				//5.4.1 Make the section image visible to hide the maze
				image[thirdClick].setVisible(true);

			}

			//5.5 If the absolute difference between fourthClick and thirdClick is 3 
			if(Math.abs(fourthClick - thirdClick) == 3) {

				//5.5.1 Then the sections match and increment the number of points
				points++;
				pointsLabel.setText("Points: " + points);
				//5.5.2 Remove MouseListener for the matched sections so they cannot be chosen again
				section[thirdClick].removeMouseListener(this);
				section[fourthClick].removeMouseListener(this);
				//5.5.3 Remove the section images to reveal the maze
				remove(image[thirdClick]);
				remove(image[fourthClick]);

				//5.5.4 Make the matchLabel visible for 1 second
				matchLabel.setVisible(true);

				//timer to time a second before the matchLabel is invisible
				int delay = 1000;	//delay for 1000 milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//5.5.5 Make the matchLabel invisible after 1 second
						matchLabel.setVisible(false);
					}
				};
				new Timer(delay, taskPerformer).start();
			}

			//5.6 If the absolute difference between fourthClick and thirdClick is not 3
			if(Math.abs(fourthClick - thirdClick) != 3) {

				//5.6.1 Then the sections do not match and the wrongLabel will be visible
				wrongLabel.setVisible(true);

				//timer to time a second before the section images hide the two mazes again and the wrongLabel is invisible
				int delay2 = 1000; //delay for 1000 milliseconds
				ActionListener taskPerformer2 = new ActionListener() {
					public void actionPerformed(ActionEvent evt2) {
						//5.6.2 Section images will be visible to hide the mazes after a second
						image[fourthClick].setVisible(true);
						image[thirdClick].setVisible(true);
						//5.6.3 wrongLabel will be invisible after a second
						wrongLabel.setVisible(false);

					}
				};
				new Timer(delay2, taskPerformer2).start();
			}

		}

		//6. If the number of clicks is 5
		if (numClicks == 5) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {
					//6.1 Load the section that was clicked
					section[v].loadSection(v);
					//6.2 Make the section image invisible
					image[v].setVisible(false);
					//6.3 Make fifthClick equal to the number of the section clicked
					fifthClick = v;

				}
			}
		}

		//7. If the number of clicks is 6
		if (numClicks == 6) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {
					//7.1 Load the section that was clicked
					section[v].loadSection(v);
					//7.2 Make the section image invisible
					image[v].setVisible(false);
					//7.3 Make sixthClick equal to the number of the section clicked
					sixthClick = v;

				}
			}

			//7.4 If the fifth section that was clicked is clicked again
			if(fifthClick == sixthClick) {

				//7.4.1 Make the section image visible to hide the maze
				image[fifthClick].setVisible(true);

			}

			//7.5 If the absolute difference between sixthClick and fifthClick is 3
			if(Math.abs(sixthClick - fifthClick) == 3) {

				//7.5.1 Then the sections match and increment the number of points
				points++;
				pointsLabel.setText("Points: " + points);
				//7.5.2 Remove MouseListener for the matched sections so they cannot be chosen again
				section[fifthClick].removeMouseListener(this);
				section[sixthClick].removeMouseListener(this);
				//7.5.3 Remove the section images to reveal the maze
				remove(image[fifthClick]);
				remove(image[sixthClick]);

				//7.5.4 Make the matchLabel visible for 1 second
				matchLabel.setVisible(true);

				//timer to time a second before the matchLabel is invisible
				int delay = 1000; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//7.5.5 Make the matchLabel invisible after 1 second
						matchLabel.setVisible(false);
					}
				};
				new Timer(delay, taskPerformer).start();
			}

			//7.6 If the absolute difference between sixthClick and fifthClick is not 3
			if(Math.abs(sixthClick - fifthClick) != 3) {

				//7.6.1 Then the sections do not match and the wrongLabel will be visible
				wrongLabel.setVisible(true);

				//timer to time a second before the section images hide the two mazes again and the wrongLabel is invisible
				int delay2 = 1000; //delay for 1000 milliseconds
				ActionListener taskPerformer2 = new ActionListener() {
					public void actionPerformed(ActionEvent evt2) {
						//7.6.2 Section images will be visible to hide the mazes after a second
						image[sixthClick].setVisible(true);
						image[fifthClick].setVisible(true);
						//7.6.3 wrongLabel will be invisible after a second
						wrongLabel.setVisible(false);

					}
				};
				new Timer(delay2, taskPerformer2).start();
			}

		}

		//8. If the number of clicks is 7
		if (numClicks == 7) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {
					//8.1 Load the section that was clicked
					section[v].loadSection(v);
					//8.2 Make the section image invisible
					image[v].setVisible(false);
					//8.3 Make seventhClick equal to the number of the section clicked
					seventhClick = v;

				}
			}
		}

		//9. If the number of clicks is 8
		if (numClicks == 8) {

			for (int v = 0; v < 6; v++) {

				if (e.getSource() == section[v]) {

					//9.1 Load the section that was clicked
					section[v].loadSection(v);
					//9.2 Make the section image invisible
					image[v].setVisible(false);
					//9.3 Make eighthClick equal to the number of the section clicked
					eighthClick = v;

				}
			}

			//9.4 If the seventh section that was clicked is clicked again
			if(seventhClick == eighthClick) {

				//9.4.1 Make the section image visible to hide the maze
				image[seventhClick].setVisible(true);

			}

			//9.5 If the absolute difference between eighthClick and seventhClick is 3
			if(Math.abs(eighthClick - seventhClick) == 3) {

				//9.5.1 Then the sections match and increment the number of points
				points++;
				pointsLabel.setText("Points: " + points);
				//9.5.2 Remove MouseListener for the matched sections so they cannot be chosen again
				section[seventhClick].removeMouseListener(this);
				section[eighthClick].removeMouseListener(this);
				//9.5.3 Remove the section images to reveal the maze
				remove(image[seventhClick]);
				remove(image[eighthClick]);

				//9.5.4 Make the matchLabel visible for 1 second
				matchLabel.setVisible(true);

				//timer to time a second before the matchLabel is invisible
				int delay = 1000; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						//9.5.5 Make the matchLabel invisible after 1 second
						matchLabel.setVisible(false);
					}
				};
				new Timer(delay, taskPerformer).start();
			}

			//9.6 If the absolute difference between eighthClick and seventhClick is not 3
			if(Math.abs(eighthClick - seventhClick) != 3) {

				//9.6.1 Then the sections do not match and the wrongLabel will be visible
				wrongLabel.setVisible(true);

				//timer to time a second before the section images hide the two mazes again and the wrongLabel is invisible
				int delay2 = 1000; //delay for 1000 milliseconds
				ActionListener taskPerformer2 = new ActionListener() {
					public void actionPerformed(ActionEvent evt2) {
						//9.6.2 Section images will be visible to hide the mazes after a second
						image[eighthClick].setVisible(true);
						image[seventhClick].setVisible(true);
						//9.6.3 wrongLabel will be invisible after a second
						wrongLabel.setVisible(false);

					}
				};
				new Timer(delay2, taskPerformer2).start();
			}

		}

	}

	/**
	 * Mandatory method to implement MouseListener interface
	 */
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Not used
	}

	/**
	 * Mandatory method to implement MouseListener interface
	 */
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Not used
	}

	/**
	 * Mandatory method to implement MouseListener interface
	 */
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Not used
	}

	/**
	 * Mandatory method to implement MouseListener interface
	 */
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// Not used
	}

	/**
	 * Mandatory method to implement ActionListener interface
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Not used

	}

}