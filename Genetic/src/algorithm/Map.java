package algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	private ArrayList<Cell> cellList = new ArrayList<Cell>();
	private double fitness;
	
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public void generateMap() {
		Random random = new Random();
		for(int i = 0; i < 100; i++) {
			cellList.add(new Cell(new Point(random.nextInt(Genetic_Labour.MAXIMUM_X_POSITION), random.nextInt(Genetic_Labour.MAXIMUM_Y_POSITION))));
		}
		
		calculateFitness();
		
		
	}
	
	public void calculateFitness() {
		double smallestDistanceOverall = 1000;
		double largestDistance = 0;
		double smallestDistance = 1000;
		for(int m = 0; m < cellList.size(); m++) {
			for(int n = 0; n < cellList.size(); n++) {
				if(m != n) {
					int deltaX = cellList.get(m).getPosition().x - cellList.get(n).getPosition().x;
					int deltaY = cellList.get(m).getPosition().y - cellList.get(n).getPosition().y;
					double currentDistance = ((Double)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
					if(currentDistance < smallestDistance) {
						smallestDistance = currentDistance;
					}
				}
			}
			if(smallestDistance < smallestDistanceOverall) {
				smallestDistanceOverall = smallestDistance;
			} 
			if(smallestDistance > largestDistance) {
				largestDistance = smallestDistance;
			}
			smallestDistance = 1000;
		}
		fitness = (smallestDistanceOverall/largestDistance)*10000.0;
		//System.out.println(fitness);
	}
	public ArrayList<Cell> getCellList() {
		return cellList;
	}
	
	public void setCellList(ArrayList<Cell> cellList) {
		this.cellList = cellList;
		calculateFitness();
	}
}
