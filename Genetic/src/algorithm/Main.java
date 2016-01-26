package algorithm;

public class Main {
	public static void main(String[] args) {
		Genetic_Labour labour = new Genetic_Labour();
		new Frame(labour.generateMaps().getCellList());
	}
}
