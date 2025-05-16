/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ShapeManagement.bouncebox;

import ShapeManagement.bounceboxframework.*;
import ShapeManagement.bounceboxframework.Shape;

import java.awt.*;

public class Circle extends Shape {
    
    private int r;
    public Circle(int x, int y, int r) {
        super(x,y);
        this.r = r;
    }
    
    public int getRadius(){return r;}
    
    public double getContactRadius() {return r;}
    public double getMass() {return Math.PI*r*r;}
    
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        double left = getX() - getRadius();
        double top = getY() - getRadius();
        g.fillOval((int)left,(int)top,getRadius()*2,
                                      getRadius()*2);
    }
}
