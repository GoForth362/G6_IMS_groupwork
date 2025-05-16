package ShapeManagement.bouncebox;

import ShapeManagement.bounceboxframework.*;

import java.awt.*;

public class Triangle extends Shape{
    private int base;
    private int height;
    private double contactRadius;

    public Triangle(int x, int y,int base, int height){
        super(x, y);
        this.base = base;
        this.height = height;
    }

    public int getBase(){
        return this.base;
    }
    public int getHeight(){
        return  this.height;
    }

    @Override
    public double getContactRadius() {
        contactRadius = Math.max(Math.sqrt(Math.pow(base/2, 2) + Math.pow(height, 2)), base/2);
        return contactRadius;
    }

    @Override
    public double getMass() {
        return this.base * this.height / 2.0;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        int[] xPoints = { (int)getX(), (int)(getX() - base / 2), (int)(getX() + base / 2) };
        int[] yPoints = {  (int) (getY() - height / 2), (int) (getY() + height / 2), (int) (getY() + height / 2)};
        g.fillPolygon(xPoints, yPoints, 3);
    }
}
