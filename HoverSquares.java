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

public class HoverSquares extends MouseAdapter implements Runnable,ActionListener {

    // square size
    public static final int SIZE = 50;

    // list of squares currently on the screen
    private java.util.List<Point> upperLefts;

    private JPanel panel;
    
    private Point currentMouse = null;
      
    private JButton clear, ran;

    /**
    The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("HoverSquares");
        frame.setPreferredSize(new Dimension(800,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel framePanel = new JPanel(new BorderLayout());
        frame.add(framePanel);
        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                // draw the squares
                for (Point p : upperLefts) {
                    boolean hit = p.x <= currentMouse.x && 
                    p.x + SIZE >= currentMouse.x && 
                    p.y <= currentMouse.y && 
                    p.y + SIZE >= currentMouse.y;
                    if(hit)
                    {
                        g.setColor(Color.RED);
                    }
                    else
                    {
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(p.x,p.y,SIZE,SIZE);
                }
            }
        };
        
        framePanel.add(panel,BorderLayout.CENTER);
        JPanel buttonLayout = new JPanel();
        clear = new JButton("Clear");
        ran = new JButton("Randomize");
        buttonLayout.add(clear);
        buttonLayout.add(ran);
        
        framePanel.add(buttonLayout, BorderLayout.SOUTH);
        clear.addMouseListener(this);
        ran.addMouseListener(this);

        upperLefts = new ArrayList<Point>();

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == clear)
        {
            upperLefts.clear();
            panel.repaint();
        }
        else
        {
            
        }
    }

    /**
    Mouse press event handler to create a new Square with upper left at
    the press point.

    @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {
        
        upperLefts.add(e.getPoint());
        currentMouse = e.getPoint();
        panel.repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        currentMouse = e.getPoint();
        panel.repaint();
    }
    
    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new HoverSquares());
    }
}

