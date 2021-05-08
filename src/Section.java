/** 
 * This class represents the game board and includes methods to handle keyboard events and game actions.
 */

import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;


public class Section extends JPanel implements KeyListener, ActionListener, MouseListener {

	//Timer for game movement
	private Timer gameTimer = new Timer(250, this);

	//Timer for Character animation
	private Timer animateTimer = new Timer(50, this);

	//ImageIcon constant for blank
	private static final ImageIcon BLANK = new ImageIcon("images/Blank.png");

	//ImageIcon constant for gate
	private static final ImageIcon GATE = new ImageIcon("images/Gate.bmp");

	//ImageIcon constant for block
	private static final ImageIcon BLOCK = new ImageIcon("images/6.png");

	//ImageIcon constant for cactus
	private static final ImageIcon CACTUS = new ImageIcon("images/Cactus.png");

	//ImageIcon constant for mine
	private static final ImageIcon MINE = new ImageIcon("images/Mine.png");

	//ImageIcon constant for volcano
	private static final ImageIcon VOLCANO = new ImageIcon("images/Volcano.png");

	//ImageIcon constant for finish line
	private static final ImageIcon FINISH = new ImageIcon("images/Finish.png");;

	//Array to hold section characters from the text file
	private char[][] maze = new char[15][17];

	//Array to hold sections images
	private JLabel[][] cell = new JLabel[15][17];

	//Character object
	private Character character;

	//Array of Fish objects
	private Fish[] fish = new Fish[9];

	//Steps for animating Character
	private int pStep;	

	//Counter to count for the number of wall images
	private int imagescount = 1;

	//Section number from MemoryMazeGUI
	private int number;

	/**
	 * Construct the section including the layout, background, Character and fishes
	 * and calls the loadSection method. Receives the x and y position values of the sections from the MemoryMazeGUI class.
	 * @return 
	 */
	public Section(int x, int y) {

		//1. Set layout(grid) of section when clicked
		setLayout(new GridLayout(15, 17));
		setBounds(x,y,290,300);

		//2. Set the size of individual cell labels
		for(int row = 0; row < 15; row++) {
			for(int col = 0; col < 17; col++) {
				cell[row][col] = new JLabel();
				cell[row][col].setSize(22, 22);
				add(cell[row][col]);
			}			
		}

		//3. Create Character
		character = new Character(StartScreen3.charaNum);

		//4. Create 9 fishes
		for (int s = 0; s < 9; s++) {

			fish[s] = new Fish(s);

		}

	}


	/**
	 * Loads the mazes onto the screen from 6 text files
	 * @param  
	 */
	void loadSection(int sectionNumber) {

		//receives the number of the section that is clicked from MemoryMazeGUI
		number = sectionNumber;
		
		//keeps track of row number
		int r = 0;

		//1. Open the maze text file for input
		Scanner input = null;

		//1.1 Load maze text files according to their section number
		try {
			if (number == 0) {
				input = new Scanner(new File("maze1.txt"));

			}
			else if (number == 1) {
				input = new Scanner(new File("maze2.txt"));

			}

			else if (number == 2) {
				input = new Scanner(new File("maze3.txt"));

			}

			else if (number == 3) {
				input = new Scanner(new File("maze1x.txt"));

			}

			else if (number == 4) {
				input = new Scanner(new File("maze2x.txt"));

			}

			else if (number == 5) {
				input = new Scanner(new File("maze3x.txt"));

			}

			//2. Cycle through all the rows in the maze file reading one row at a time
			while (input.hasNext()) {

				//2.1 Read the next line from the maze file
				maze[r] = input.nextLine().toCharArray();

				//2.2 For each row cycle through all the columns.
				for (int c = 0; c < maze[r].length; c++) {

					//Depending on the symbol in the maze file assign the appropriate picture
					//2.2.1 If the symbol is a wall then assign a wall picture to the current square on the screen
					if (maze[r][c] == 'W') {

						ImageIcon wall = new ImageIcon("images/" + imagescount + ".png");	////ImageIcon constant for wall
						cell[r][c].setIcon(wall);
						if (imagescount == 12)
							imagescount = 1;
						imagescount++;

					}

					//2.2.2 Otherwise if the symbol is a M then assign a mine picture to the current square on the screen
					else if (maze[r][c] == 'M') {

						cell[r][c].setIcon(MINE);

					}

					//2.2.3 Otherwise if the symbol is a A then assign a gate picture to the current square on the screen
					else if (maze[r][c] == 'A') {

						cell[r][c].setIcon(GATE);

					}

					//2.2.4 Otherwise if the symbol is a C then assign a cactus picture to the current square on the screen
					else if (maze[r][c] == 'C') {

						cell[r][c].setIcon(CACTUS);

					}

					//2.2.5 Otherwise if the symbol is a V then assign a volcano picture to the current square on the screen
					else if (maze[r][c] == 'V') {

						cell[r][c].setIcon(VOLCANO);

					}

					//2.2.6 Otherwise if the symbol is a D then assign a door picture to the current square on the screen
					else if (maze[r][c] == 'D') {

						cell[r][c].setIcon(BLANK);

					}

					//2.2.7 Otherwise if the symbol is a D then assign a door picture to the current square on the screen
					else if (maze[r][c] == 'F') {

						cell[r][c].setIcon(FINISH);

					}

					//2.2.8 Otherwise if the symbol is a B then assign a blank picture to the current square on the screen
					else if (maze[r][c] == 'B') {

						cell[r][c].setIcon(BLANK);

					}

					//2.2.9 Otherwise if the symbol is Character then assign the character's right image and set Character's row, column, and direction (left)
					else if (maze[r][c] == 'P') {

						cell[r][c].setIcon(character.getIcon());
						character.setRow(r);
						character.setColumn(c);
						character.setDirection(2);	//start right

					}

					//2.2.10 Otherwise if the symbol is a fish then assign the appropriate fish image and set the fish's row, column
					else if (maze[r][c] == '0' || maze[r][c] == '1' || maze[r][c] == '2' || maze[r][c] == '3' || maze[r][c] == '4' || maze[r][c] == '5' || maze[r][c] == '6' || maze[r][c] == '7' || maze[r][c] == '8') {

						//int gNum = Character.getNumericValue(maze[r][c]);
						int gNum = (int)(maze[r][c])-48;

						cell[r][c].setIcon(fish[gNum].getIcon());
						fish[gNum].setRow(r);
						fish[gNum].setColumn(c);

					}

				}

				//2.3 Increment the row
				r++;

			}

			//3. Close the maze text file
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		}

	}

	/** 
	 * Handles keyboard entries - to start the game and control Character's movements
	 */
	public void keyPressed(KeyEvent key) {

		//1. If the game isn't running and Character is alive then start the game timer
		if (gameTimer.isRunning() == false && character.isDead() == false)
			gameTimer.start();

		//2. If Character is still alive and the game is not over then
		if (character.isDead() == false) {

			//2.1 Track direction based on the key pressed - 37 since ASCII codes for cursor keys start at 37:
			int direction = key.getKeyCode()-37;

			//2.2. Change direction of Character; 37-left,38-up,39-right,40-down
			if (direction==0 && maze[character.getRow()][character.getColumn()-1] != 'W')
				character.setDirection(0);
			else if (direction==1 && maze[character.getRow()-1][character.getColumn()] != 'W')
				character.setDirection(1);
			else if (direction==2 && maze[character.getRow()][character.getColumn()+1] != 'W')
				character.setDirection(2);
			else if (direction==3 && maze[character.getRow()+1][character.getColumn()] != 'W')
				character.setDirection(3);		

		}

	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyReleased(KeyEvent key) {
		// Not used
	}

	/**
	 * Mandatory method to implement KeyListener interface
	 */
	public void keyTyped(KeyEvent key) {
		// Not used
	}

	/**
	 * Allows an object to move and updates both on the maze and screen based on: 
	 * the object, direction, and change in row and column
	 */
	private void performMove(Mover mover) {

		//1. If a mover is at a door then teleport to other side
		if (mover.getColumn()==1){
			mover.setColumn(16);
		} else if (mover.getColumn()==17){
			mover.setColumn(2);
		}

		//2. If there is no wall in the direction that the Mover object wants to go then
		if (maze[mover.getNextRow()][mover.getNextColumn()] != 'W') {

			//2.1. If the Mover object is Character then start animate timer
			if (mover==character) {
				if (maze[mover.getNextRow()][mover.getNextColumn()] != 'A')
					animateTimer.start();
			}


			//2.2 Otherwise the Mover is a fish
			else {

				cell[mover.getRow()][mover.getColumn()].setIcon(BLANK);

				//2.2.3 Move the fish's position
				mover.move();

				//2.2.4 If a collision has occurred then death occurs
				if (collided()) {
					death();

				}
				//2.2.5 Otherwise update the picture on the screen
				else 
					//cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());

					if ((fish[0].getRow() < 10 || fish[0].getRow() > 15 || fish[0].getColumn() < 11 || fish[0].getColumn() > 17) && (fish[1].getRow() < 10 || fish[1].getRow() > 15 || fish[1].getColumn() < 11 || fish[1].getColumn() > 17) && (fish[2].getRow() < 10 || fish[2].getRow() > 15 || fish[2].getColumn() < 11 || fish[2].getColumn() > 17)) {
						cell[6][9].setIcon(BLOCK);
						maze[6][9] = 'W';	
					}

			}

		}

	}


	/**
	 * Determines if Character has collided with a fish
	 */
	private boolean collided() {

		//1. Cycle through all the fishes to see if anyone has caught Character
		for (Fish g: fish) {

			//1.1. If the fish is in the same location then return that a collision occurred
			if (g.getRow()==character.getRow() && g.getColumn()==character.getColumn()) {
				return true;

			}
		}

		//2. If no fishes were in the same location then return that no collision occurred
		return false;

	}

	/**
	 * Stop the game when Character and a fish 'collide'
	 */
	private void death() {

		//1. Set Character dead
		character.setDead(true);

		//2. Stop the game
		stopGame();
		
		//3. Sleep for 2 seconds
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//4. Create the first StartScreen
		new StartScreen();

	}

	/**
	 * Stops the game timer 
	 */
	private void stopGame() {

		//1. if Character is dead or all the food is eaten then
		// stop the timers
		if (character.isDead()) {
			animateTimer.stop();
			gameTimer.stop();

		}

	}

	/**
	 * Moves the fishes in a random pattern
	 */
	private void moveFishes(){

		//1. Cycle through all the fishes
		for (Fish g: fish) {

			int dir=0;

			//1.1. Keep selecting random directions to avoid
			// 'back-tracking'
			do {
				dir = (int)(Math.random()*4);
			} while (Math.abs(g.getDirection() - dir) == 2);

			//1.2. If the fishes cross row 6 then they will follow character
			if (g.getRow() < 6) {
				if(character.getColumn() < g.getColumn())
					dir = 0;
				if(character.getRow() < g.getRow())
					dir = 1;
				if(character.getColumn() > g.getColumn())
					dir = 2;
				if(character.getRow() > g.getRow())
					dir = 3;
			}

			//1.3. Set the fishes direction
			g.setDirection(dir);

			//1.4 Move the fish
			performMove(g);

		}

	}

	/**
	 * Determines the source of the action as either the game timer or 
	 * the animation timer and then performs the corresponding actions
	 */
	public void actionPerformed(ActionEvent e) {

		//1. If the action is the game timer
		if (e.getSource()==gameTimer) {

			//1.1. Then move the Character and the fishes
			performMove(character);
			moveFishes();

			//2. Otherwise, if the action is the animation timer
		} else if (e.getSource()==animateTimer) {

			//2.1. Animate Character through the current step
			animateCharacter();

			//2.2. Increment the step number
			pStep++;

			//2.3. If the step is the last step then reset the step to 0
			if (pStep==3)
				pStep=0;

		}

	}		

	/**
	 * Animates Character in 3 steps 
	 */
	private void animateCharacter() {

		//1. If it is step 0 of animation
		if (pStep == 0) {

			//1.1 Face direction in current cell
			cell[character.getRow()][character.getColumn()].setIcon
			(character.image[character.getDirection()]);

			//1.2 Delay the animation timer
			animateTimer.setDelay(100);

		}

		//2. Otherwise if it is step 1 of animation
		else if (pStep==1)

			//2.1. Blank the current cell
			cell[character.getRow()][character.getColumn()].setIcon(BLANK);

		//3. Otherwise if it is step 2 of animation
		else if (pStep ==2) {

			//3.1. Move Character
			character.move();

			//3.2. If there is a finish line in the new square on the maze and the Mover is Character then
			if (maze[character.getRow()][character.getColumn()]=='F'){

				//3.2.1. Create a new first StartScreen
				new StartScreen();

			}

			//3.3. If there is a mine in the new square on the maze and the Mover is Character then
			if (maze[character.getRow()][character.getColumn()]=='M'){

				//3.3.1. Create a new first StartScreen
				new StartScreen();

			}

			//3.4. Stop the animation timer
			animateTimer.stop();

			//3.4. If Character is dead then show a skull
			if (character.isDead()) {
			}

			//3.6 Otherwise show the appropriate closed Character
			// based on its direction
			else
				cell[character.getRow()][character.getColumn()].setIcon(character.image[character.getDirection()]);

		}

	}


	/**
	 * Mandatory method to implement MouseListener interface
	 */
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// Not used
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

}

