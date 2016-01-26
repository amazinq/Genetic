package algorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4536401128460082104L;
	ArrayList<Cell> cellList;
	
	public Panel(ArrayList<Cell> cellList) {
		this.cellList = cellList;
		setLayout(null);
		repaint();
		revalidate();
	}
	
	 protected void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        Ellipse2D e;
	        Color color;
	        for(int j = 0; j < cellList.size(); j++)
	        {
	            e = (Ellipse2D)new Ellipse2D.Double(cellList.get(j).getPosition().getX()-2, cellList.get(j).getPosition().getY()-2, 4,4);
	            color = Color.RED;
	            g2.setPaint(color);
	            g2.fill(e);
	        }
	    }

}
