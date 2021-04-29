import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
This class stores and draws an individual Snowman object.

@author Jim Teresco
@version Spring 2020
 */
public class Snowman extends Thread {

    // min size of a component snowball
    public static final int MIN_SIZE = 50;

    // location
    private Point upperLeft;

    // diameter
    private int diameter;

    /**
    Construct a new Snowman object.

    @param center point for the center of the snowman's base
    @param other another point, which will be on the edge of the 
    snowman's base
     */
    public Snowman(Point center, Point other) {

        this.diameter = (int)center.distance(other) * 2;
        this.upperLeft = new Point(center.x - diameter/2, center.y - diameter/2);
    }

    /**
    Draw the snowman at its current location.

    @param g the Graphics object on which the ball should be drawn
     */
    public void paint(Graphics g) {
        recursiveSnowman(upperLeft.x, upperLeft.y, diameter, g);
    }
    
    protected void recursiveSnowman(int x, int y, int d, Graphics g)
    {
        g.drawOval(x, y, d, d);
        int newD = d;
        newD *= 0.75;
        if(newD > 50)
        {
            recursiveSnowman(x + d / 8,y - newD,newD,g);
        }

    }
}
