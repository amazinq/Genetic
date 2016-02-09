package algorithm;

import java.awt.Point;

public class Cell {

	private Point position;
	private int size;
	
	public Cell(Point position, int size) {
		this.position = position;
		this.size = size;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
