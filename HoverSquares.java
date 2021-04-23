import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**

   Exam 2 event handler question.

   @author Jim Teresco
   @version Spring 2020
*/

public class HoverSquares extends MouseAdapter implements Runnable {

    // square size
    public static final int SIZE = 50;
    
    // list of squares currently on the screen
    private java.util.List<Point> upperLefts;
    
    private JPanel panel;

    /**
       The run method to set up the graphical user interface
    */
    @Override
    public void run() {
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("HoverSquares");
	frame.setPreferredSize(new Dimension(800,800));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// JPanel with a paintComponent method
	panel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
		    
		    super.paintComponent(g);

		    // draw the squares
		    for (Point p : upperLefts) {
			g.fillRect(p.x, p.y, SIZE, SIZE);
		    }
		}
	    };
	frame.add(panel);
	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);

	upperLefts = new ArrayList<Point>();
	
	frame.pack();
	frame.setVisible(true);
    }

    /**
       Mouse press event handler to create a new Square with upper left at
       the press point.

       @param e mouse event info
    */
    @Override
    public void mousePressed(MouseEvent e) {

	upperLefts.add(e.getPoint());
	panel.repaint();
    }

    public static void main(String args[]) {

	javax.swing.SwingUtilities.invokeLater(new HoverSquares());
    }
}
   
