package algorithm;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Genetic_Labour {

	public static int MAXIMUM_X_POSITION = 800;
	public static int MAXIMUM_Y_POSITION = 800;

	private ArrayList<Map> currentGeneration;
	private Map currentLowestFitness;
	private Map secondBestFitness;

	public Genetic_Labour() {
		currentGeneration = new ArrayList<Map>();
	}

	private void setFitnesses() {
		for (Map map : currentGeneration) {
			if (currentLowestFitness == null) {
				currentLowestFitness = map;
			} else if (secondBestFitness == null && map.getFitness() > currentLowestFitness.getFitness()) {
				secondBestFitness = map;
			} else if (secondBestFitness == null && map.getFitness() <= currentLowestFitness.getFitness()) {
				secondBestFitness = currentLowestFitness;
				currentLowestFitness = map;
			} else if (map.getFitness() < currentLowestFitness.getFitness()) {
				secondBestFitness = currentLowestFitness;
				currentLowestFitness = map;
			} else if (map.getFitness() > currentLowestFitness.getFitness()
					&& map.getFitness() < secondBestFitness.getFitness()) {
				secondBestFitness = map;
			}
		}
	}

	public Map generateMaps() {
		boolean generationSuccessful = false;
		while (generationSuccessful == false) {
			currentGeneration = new ArrayList<Map>();
			currentLowestFitness = null;
			secondBestFitness = null;
			for (int i = 0; i < 8; i++) {
				Map currMap = new Map();
				currMap.generateMap();
				currentGeneration.add(currMap);
			}
			setFitnesses();

			System.out.println(currentLowestFitness.getFitness());
			int xyz = 0;
			long time = System.currentTimeMillis();
			while (currentLowestFitness.getFitness() > 10 && xyz < 10000) {
				// System.out.println(currentGeneration.size());
				// größe größer 2
				int iterator = 0;
				int crossDivider = new Random().nextInt(currentLowestFitness.getCellList().size());
				Map childMap = new Map();
				ArrayList<Cell> childCellList = new ArrayList<Cell>();
				Random random = new Random();
				for (int i = 0; i < crossDivider; i++) {
					if (random.nextInt(100) >= 2) {
						childCellList.add(currentLowestFitness.getCellList().get(i));
					} else {
						childCellList.add(new Cell(
								new Point(random.nextInt(MAXIMUM_X_POSITION), random.nextInt(MAXIMUM_Y_POSITION)),
								random.nextInt(Map.CELL_MAXIMUM_SIZE - 4) + 4));
					}
				}
				for (int i = crossDivider; i < secondBestFitness.getCellList().size(); i++) {
					if (random.nextInt(100) >= 2) {
						childCellList.add(secondBestFitness.getCellList().get(i));
					} else {
						childCellList.add(new Cell(
								new Point(random.nextInt(MAXIMUM_X_POSITION), random.nextInt(MAXIMUM_Y_POSITION)),
								random.nextInt(Map.CELL_MAXIMUM_SIZE - 4) + 4));
					}
				}
				childMap.setCellList(childCellList);
				// System.out.printf("Elems: %s\n",
				// currentGeneration.keySet().stream().map(Ströing::valueOf).reduce((a,
				// b) -> String.format("%s %s", a, b)).get());
				// System.out.printf("Fitness: %f\n", childMap.getFitness());
				currentGeneration.remove(secondBestFitness);
				currentGeneration.add(childMap);
				setFitnesses();
				// System.out.println(currentLowestFitness.getFitness());
				xyz++;
			}
			System.out
					.println("Number of Generations: " + xyz + " Time needed: " + (System.currentTimeMillis() - time));
			if(xyz < 10000) {
				generationSuccessful = true;
			}
		}
		return currentLowestFitness;
	}
}
