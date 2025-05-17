package ShapeManagement.bouncebox;

import ShapeManagement.bounceboxframework.Shape;
import java.awt.Graphics2D;

public class Rectangle extends Shape{
    private double contactRadius;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height){
        super(x, y);
        this.width = width;
        this.height = height;
        contactRadius = Math.sqrt(this.width * this.width + this.height * this.height) / 2;
    }

    int getWidth(){
        return this.width;
    }
    int getHeight(){
        return this.height;
    }

    @Override
    public double getContactRadius() {
        return this.contactRadius;
    }

    @Override
    public double getMass() {
        return this.height * this.width;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        double left = getX() - getWidth() / 2;
        double top = getY() - getHeight() / 2;
        g.fillRect((int)left, (int)top, getWidth(), getHeight());
    }
}
