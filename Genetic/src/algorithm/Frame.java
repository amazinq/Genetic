package algorithm;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

	public Frame(ArrayList<Cell> cellList) {
		setSize(800,800);
		setVisible(true);
		setContentPane(new Panel(cellList));
		setResizable(false);
		
		
	}
}
