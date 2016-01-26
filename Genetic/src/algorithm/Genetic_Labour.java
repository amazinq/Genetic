package algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Genetic_Labour {

	public static int MAXIMUM_X_POSITION = 1000;
	public static int MAXIMUM_Y_POSITION = 1000;

	private TreeMap<Double, Map> currentGeneration;
	private Set<Double> keySet;
	private double currentLowestFitness;
	private double currentHighestFitness;

	public Genetic_Labour() {
		currentGeneration = new TreeMap<Double, Map>();
		currentLowestFitness = 1000;
		currentHighestFitness = 0;
	}

	public Map generateMaps() {
		for (int i = 0; i < 8; i++) {
			Map currMap = new Map();
			currMap.generateMap();
			currentGeneration.put(currMap.getFitness(), currMap);
		}
		keySet = currentGeneration.keySet();
		for (Double key : keySet) {
			Map map = currentGeneration.get(key);
			if (map.getFitness() < currentLowestFitness) {
				currentLowestFitness = map.getFitness();
			} else if (map.getFitness() > currentHighestFitness) {
				currentHighestFitness = map.getFitness();
			}
		}
	
		System.out.println(currentLowestFitness);
		while (currentLowestFitness > 10) {
//			System.out.println(currentGeneration.size());
			Map bestFitness = currentGeneration.get(currentLowestFitness);
			// größe größer 2
			Map secondBestFitness = null;
			int iterator = 0;
			for (double key : keySet) {
				if (iterator == 1) {
					secondBestFitness = currentGeneration.get(key);
					break;
				}
				iterator++;
			}
			int crossDivider = new Random().nextInt(bestFitness.getCellList()
					.size());
			Map childMap = new Map();
			ArrayList<Cell> childCellList = new ArrayList<Cell>();
			Random random = new Random();
			for (int i = 0; i < crossDivider; i++) {
				if (random.nextInt(100) >= 2) {
					childCellList.add(bestFitness.getCellList().get(i));
				} else {
					childCellList.add(new Cell(new Point(random.nextInt(MAXIMUM_X_POSITION), random.nextInt(MAXIMUM_Y_POSITION))));
				}
			}
			for (int i = crossDivider; i < secondBestFitness.getCellList().size(); i++) {
				if (random.nextInt(100) >= 2) {
					childCellList.add(secondBestFitness.getCellList().get(i));
				} else {
					childCellList.add(new Cell(new Point(random.nextInt(MAXIMUM_X_POSITION), random.nextInt(MAXIMUM_Y_POSITION))));
				}
			}
			childMap.setCellList(childCellList);
//			System.out.printf("Elems: %s\n", currentGeneration.keySet().stream().map(Ströing::valueOf).reduce((a, b) -> String.format("%s %s", a, b)).get());
//			System.out.printf("Fitness: %f\n", childMap.getFitness());
			currentGeneration.remove(secondBestFitness.getFitness());
			currentGeneration.put(childMap.getFitness(), childMap);
			keySet = currentGeneration.keySet();
			for (double key : keySet) {
				Map map = currentGeneration.get(key);
				if (map.getFitness() < currentLowestFitness) {
					currentLowestFitness = map.getFitness();
				} else if (map.getFitness() > currentHighestFitness) {
					currentHighestFitness = map.getFitness();
				}
			}
		}
		System.out.println(currentLowestFitness);
		return currentGeneration.get(currentGeneration.firstKey());
	}
}
