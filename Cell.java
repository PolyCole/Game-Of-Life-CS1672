public class Cell {
	
	/*
	 * Author: Cole Polyak
	 * 17 January 2018
	 * Assignment 1
	 * 
	 * Cell.java
	 * 
	 * This class creates Cell Objects that are used to 
	 * populate the Enviroment
	 */
	
	
	//Integer used to determine status of cell.
	int alive;
	
	//Constructor
	public Cell(int x)
	{
		alive = x;
	}
	
	//Setter
	public void setAliveStatus(int x)
	{
		alive = x;
	}
	
	//Getter
	public int getAliveStatus()
	{
		return alive;
	}
}