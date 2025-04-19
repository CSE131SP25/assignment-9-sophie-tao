package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int currentDirection;
	
	public Snake() {
		segments = new LinkedList<>();
		BodySegment s1 = new BodySegment(0.5,0.5,0.02);
		segments.add(s1);
		deltaX = 0;
		deltaY = 0;
		currentDirection = 0;
	}
	
	public void changeDirection(int direction) {
		
		if (direction == -1) {
			return;
		}
		
		if (currentDirection == 1 && direction == 2) {
		    return;
		}
		
		if (currentDirection == 2 && direction == 1) {
		    return;
		}
		
		if (currentDirection == 3 && direction == 4) {
		    return;
		}
		
		if (currentDirection == 4 && direction == 3) {
		    return;
		}
		
		
		
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
		currentDirection = direction;
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		
		// Save the previous positions of each segment
	    double prevX = segments.get(0).getX();
	    double prevY = segments.get(0).getY();

	    // Move the head
	    segments.get(0).move(deltaX, deltaY);

	    // Move the rest of the body
	    for (int i = 1; i < segments.size(); i++) {
	        BodySegment current = segments.get(i);
	        double tempX = current.getX();
	        double tempY = current.getY();
	        current.move(prevX - tempX, prevY - tempY);
	        prevX = tempX;
	        prevY = tempY;
	    }
		
		
//		for (int i = segments.size()-1; i >= 0; i--) {
//			(segments.get(i)).move(deltaX, deltaY);
//		}
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (BodySegment s : segments) {
			s.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		
		double myX = (segments.get(0)).getX();
		double myY = (segments.get(0)).getY();
		double d = Math.sqrt(Math.pow(f.getX() - myX, 2) + Math.pow(f.getY() - myY, 2));
		double d2 = d - SEGMENT_SIZE - f.getSize();
		if (d2 <= 0) {
			BodySegment s = new BodySegment((segments.get(segments.size()-1)).getX()+deltaX,(segments.get(segments.size()-1)).getY()+deltaY,SEGMENT_SIZE);
			segments.add(s);
			move();
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		double myX = (segments.get(0)).getX();
		double myY = (segments.get(0)).getY();
		if (myX + SEGMENT_SIZE > 1 || myX - SEGMENT_SIZE < 0) {
			return false;
		}
		if (myY + SEGMENT_SIZE > 1 || myY - SEGMENT_SIZE < 0) {
			return false;
		}
		return true;
	}
	
	public boolean isAlive() {
	    BodySegment head = segments.get(0);
	    double headX = head.getX();
	    double headY = head.getY();

	    for (int i = 1; i < segments.size(); i++) {
	        BodySegment segment = segments.get(i);
	        double d = Math.sqrt(Math.pow(segment.getX() - headX, 2) + Math.pow(segment.getY() - headY, 2));
	        if (d < SEGMENT_SIZE * 0.9) { // using 0.9 as a buffer for overlap
	            return false; // Snake collided with itself
	        }
	    }
	    return true; // No collision
	}
	
	public int getPoints() {
		return segments.size()-1;
	}
}
