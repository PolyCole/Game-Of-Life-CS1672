//Import block
import edu.princeton.cs.introcs.StdDraw;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Enviroment {
	
	/*
	 * Author: Cole Polyak
	 * 17 January 2018
	 * Assignment 1
	 * 
	 * Enviroment.java
	 * 
	 * This class enviroment creates the array of cells, loads them in
	 * from file, and updates them accordingly.
	 */
	
	
	//Initializes initial array and necessary variables.
	private Cell[][] cells;
	int width;
	int height;
	int concernIndicie;
	
	public Enviroment(String filename)
	{
		//Try catch to establish file input.
		Scanner file = null;

		try
		{
			file = new Scanner(new FileInputStream(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found. Exiting...");
			System.exit(0);
		}
		
		//Reads in width and height and assigns them.
		width = file.nextInt();
		height = file.nextInt();
		
		concernIndicie = width -1;
		
		//Initializes new Cell array.
		cells = new Cell[width][height];
		
		//Populates cells array.
		for(int i = 0; i < cells.length; ++i)
		{
			for(int j = 0; j < cells[0].length; ++j)
			{
				cells[i][j] = new Cell(file.nextInt());
			}
		}
		
		//Canvas initialization
		StdDraw.setCanvasSize(1500,1500);
		StdDraw.setXscale(0,1);
		StdDraw.setYscale(0,1);
		StdDraw.enableDoubleBuffering();
	}
	
	public void runSimulation()
	{
		//Infinite animation loop.
		while(true)
		{
			
			//Creates duplicate array
			Cell[][] nextGen = new Cell[cells.length][cells[0].length];
			
			//Populates the duplicate array
			for(int i = 0; i < cells.length; ++i)
			{
				for(int j = 0; j < cells[0].length; ++j)
				{
					//This line is important. It creates new objects instead of pointers.
					nextGen[i][j] = new Cell(cells[i][j].getAliveStatus());
				}
			}
			
			//Update loop over duplicate array.
			for(int i = 0; i < nextGen.length; ++i)
			{
				for(int j = 0; j < nextGen[0].length; ++j)
				{	
					
					//Sum used to determine surrounding cells.
					int sum = 0;
					
					//If statement that checks if the cell is on the edge.
					if((i == 0 || i == concernIndicie) || ( j ==0 || j == concernIndicie))
					{
						//For use if the cell is upper left corner.
						if(i == 0 && j == 0)
						{
							sum = cells[i][j+1].getAliveStatus() +
									cells[i+1][j+1].getAliveStatus() +
									cells[i+1][j].getAliveStatus();

						}
						//For use if the cell is bottom left corner.
						else if(i == concernIndicie && j == 0) 
						{
							sum = cells[i-1][j].getAliveStatus() + 
									cells[i-1][j+1].getAliveStatus() + 
									cells[i][j+1].getAliveStatus();
						}
						
						//For use if the cell is upper right corner.
						else if(i == 0 && j == concernIndicie) 
						{
							sum = cells[i][j-1].getAliveStatus() +
									cells[i+1][j-1].getAliveStatus() + 
									cells[i+1][j].getAliveStatus();
						}
						
						//For use if the cell is bottom right corner.
						else if(i == concernIndicie && j == concernIndicie) 
						{
							sum = cells[i-1][j-1].getAliveStatus() + 
									cells[i-1][j].getAliveStatus() + 
									cells[i][j-1].getAliveStatus();
						}
						
						//For use if the cell is top row.
						else if( i == 0 && (j == 1 || j == 2 || j == 3))
						{
							sum = cells[i][j-1].getAliveStatus() +
									cells[i+1][j-1].getAliveStatus() +
									cells[i+1][j].getAliveStatus() +
									cells[i+1][j+1].getAliveStatus() + 
									cells[i][j+1].getAliveStatus();
						}

						//For use if the cell is right row.
						else if(j == concernIndicie && (i == 1 || i == 2 || i == 3))
						{
							sum = cells[i-1][j-1].getAliveStatus() + 
									cells[i-1][j].getAliveStatus() + 
									cells[i][j-1].getAliveStatus() +
									cells[i+1][j-1].getAliveStatus() +
									cells[i+1][j].getAliveStatus();
						}

						//For use if the cell is bottom row.
						else if(i == concernIndicie && (j == 1 || j == 2 || j == 3))
						{
							sum = cells[i][j-1].getAliveStatus() + 
									cells[i-1][j-1].getAliveStatus() + 
									cells[i-1][j].getAliveStatus() + 
									cells[i-1][j+1].getAliveStatus() + 
									cells[i][j+1].getAliveStatus();
						}
						
						//For use if the cell is left row.
						else if(j == 0 && (i == 1 || i == 2 || i == 3))
						{
							sum = cells[i-1][j].getAliveStatus() + 
									cells[i-1][j+1].getAliveStatus() +
									cells[i][j+1].getAliveStatus() +
									cells[i+1][j+1].getAliveStatus() + 
									cells[i+1][j].getAliveStatus();
						}


					}
					
					//Else statement if the cell in question if the cell is normally located.
					else
					{
						sum = cells[i-1][j-1].getAliveStatus() + //0
								  cells[i-1][j].getAliveStatus() + //1
								  cells[i-1][j+1].getAliveStatus() + //2
								  cells[i][j-1].getAliveStatus() + //3
								  cells[i][j+1].getAliveStatus() + //5
								  cells[i+1][j-1].getAliveStatus() + //6
								  cells[i+1][j].getAliveStatus() + //7
								  cells[i+1][j+1].getAliveStatus(); //8
					}

					//Logic controlling the reproduction and death of cells.
					if(sum < 2)
					{
						nextGen[i][j].setAliveStatus(0);
					}
					
					else if(sum == 3 && (cells[i][j].getAliveStatus() == 0))
					{
						nextGen[i][j].setAliveStatus(1);
					}
					
					else if(sum == 3 && (cells[i][j].getAliveStatus() == 1))
					{
						continue;
					}
					else if(sum == 2 && cells[i][j].getAliveStatus() == 1)
					{
						continue;
					}
					
					else if(sum > 3)
					{
						nextGen[i][j].setAliveStatus(0);
					}
					
				}
			}
			
			//Realigning the pointer of the original array to the next generation.
			cells = nextGen;
			
			//Clears previous frame.
			StdDraw.clear();
			
			
			//Loops through and redraws the cell.
			for(int i = 0; i < cells.length; ++i)
			{
				for(int j = 0; j < cells[0].length; ++j)
				{
					//Controls the coloring of the cell if it's alive or dead.
					if(cells[i][j].getAliveStatus() == 1)
					{
						StdDraw.setPenColor(StdDraw.RED);
					}
					else
					{
						StdDraw.setPenColor(StdDraw.WHITE);
					}
					
					//Draws the squares.
					StdDraw.filledSquare(
							(i*(1/(double)width)+(0.5*width)), 
							(1-(j*(1/(double)height)+(0.5*height))),
							width/(double)2);
				}
			}
			
			//Shows and pauses the current frame.
			StdDraw.show();
			StdDraw.pause(5);
		}
	}
	
	
}
