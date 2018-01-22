import java.util.Scanner;

/*
 * Author: Cole Polyak
 * 17 January 2018
 * Assignment 1
 * 
 * EnviromentDriver.java
 * 
 * This driver tests the enviroment class.
 */


public class EnviromentDriver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		//Asks for which file you would like to access.
		System.out.println("What is the file you would like to access?");
		String filename = keyboard.next();
		
		//Alternatively, you can hardcode the file input.
//		String filename = "GameOfLife4.txt";
		
		//Initializes enviroment with the specified filename.
		Enviroment e = new Enviroment(filename);
		
		//Closes keyboard and runs simulation.
		e.runSimulation();
	}

}