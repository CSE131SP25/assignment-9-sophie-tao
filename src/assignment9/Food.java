package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.03;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		double margin = FOOD_SIZE*3;
		x = margin + (1 - 2 * margin) * Math.random();
		y = margin + (1 - 2 * margin) * Math.random();
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
//		StdDraw.setPenColor(240, 157, 89);
//		StdDraw.filledCircle(x, y, FOOD_SIZE);
		StdDraw.picture(x, y, "slice-of-chocolate-cake-png.png", 0.1, 0.1);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getSize() {
		return FOOD_SIZE;
	}
	
}
