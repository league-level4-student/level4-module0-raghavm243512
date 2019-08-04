package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		selectNextPath(maze.getCell((int) (Math.random() * width), (int) (Math.random() * height)));

		// 5. call selectNextPath method with the randomly selected cell

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> arr = getUnvisited(currentCell);
		// C. if has unvisited neighbors,
		if (!arr.isEmpty()) {
			
			// C1. select one at random.
			Cell a = arr.get((int)(Math.random()*arr.size()));
			// C2. push it to the stack
			uncheckedCells.push(a);
			// C3. remove the wall between the two cells
			removeWalls(a, currentCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = a;
			currentCell.setBeenVisited(true);
			// C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}

		// D. if all neighbors are visited
		else {
			// D1. if the stack is not empty
			if (!uncheckedCells.isEmpty()) {
				// D1a. pop a cell from the stack
				currentCell = uncheckedCells.pop();
				// D1b. make that the current cell

				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if (c1.getX() < c2.getX()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}
		if (c1.getX() > c2.getX()) {
			c2.setEastWall(false);
			c1.setWestWall(false);
		}
		if (c1.getY() > c2.getY()) {
			c2.setSouthWall(false);
			c1.setNorthWall(false);
		}
		if (c1.getY() < c2.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);

		}
	}
	private static ArrayList<Cell> getUnvisited(Cell c) {
		ArrayList<Cell> a = new ArrayList<Cell>();
		int x = c.getX();
		int y = c.getY();
		for (int i = x - 1; i < x + 2; i++) {
			for (int j = y - 1; j < y + 2; j++) {
				if (i > -1 && i < maze.cells.length && j > -1 && j < maze.cells[i].length
						&& (i == x || j == y) && !maze.cells[i][j].hasBeenVisited()) {
					a.add(maze.cells[i][j]);
				}
			}
		}
		return a;
	}
	
}
