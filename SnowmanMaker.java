import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**

   Exam 2 recursive graphics question.

   @author Jim Teresco
   @version Spring 2020
*/

public class SnowmanMaker extends MouseAdapter implements Runnable {

    // list of snowmen currently on the screen
    private java.util.List<Snowman> list;
    
    private JPanel panel;

    // Snowman being defined now
    private Point center;
    private Point dragPoint;
    
    /**
       The run method to set up the graphical user interface
    */
    @Override
    public void run() {
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	JFrame frame = new JFrame("SnowmanMaker");
	frame.setPreferredSize(new Dimension(800,800));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// JPanel with a paintComponent method
	panel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
		    
		    super.paintComponent(g);

		    // in-progess, if there is one
		    if (dragPoint != null) {
			int radius = (int)center.distance(dragPoint);
			g.drawOval(center.x - radius,
				   center.y - radius,
				   radius*2, radius*2);
		    }
		    
		    // existing snowmen
		    for (Snowman s : list) {
			s.paint(g);
		    }
		}
	    };
	frame.add(panel);
	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);

	list = new ArrayList<Snowman>();
	
	frame.pack();
	frame.setVisible(true);
    }

    /**
       Mouse press event handler to create a new Snowman centered at
       the press point.

       @param e mouse event info
    */
    @Override
    public void mousePressed(MouseEvent e) {

	center = e.getPoint();
	dragPoint = null;
	panel.repaint();
    }

    /**
       Mouse press event handler to show the "rubber-banding" circle to 
       define the snowman's base.

       @param e mouse event info
    */
    @Override
    public void mouseDragged(MouseEvent e) {

	dragPoint = e.getPoint();
	panel.repaint();
    }

    /**
       Mouse release event handler to stop the growth of the most recently
       created bubble.

       @param e mouse event info
    */
    @Override
    public void mouseReleased(MouseEvent e) {

	Snowman newSnowman = new Snowman(center, e.getPoint());
	list.add(newSnowman);
	center = null;
	dragPoint = null;
	panel.repaint();
    }
    
    public static void main(String args[]) {

	javax.swing.SwingUtilities.invokeLater(new SnowmanMaker());
    }
}
   
