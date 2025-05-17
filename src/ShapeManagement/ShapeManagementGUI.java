package ShapeManagement;

import ShapeManagement.bouncebox.BounceBox;
import ShapeManagement.bouncebox.Circle;
import ShapeManagement.bouncebox.Rectangle;
import ShapeManagement.bouncebox.Square;
import ShapeManagement.bouncebox.Triangle;
import ShapeManagement.bounceboxframework.BounceModel;
import ShapeManagement.bounceboxframework.BouncePanel;
import ShapeManagement.bounceboxframework.Shape;
import ShapeManagement.bounceboxframework.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ShapeManagementGUI extends JFrame {
    protected BounceModel model;
    protected JButton loadFileButton, calculateAreaButton;
    protected Timer timer;
    private BouncePanel bouncePanel;

    public ShapeManagementGUI() {
        super("Shape Parsing & Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        model = new BounceModel();

        model.addWall(new Wall(1, 0, 0));
        model.addWall(new Wall(-1, 0, 800));
        model.addWall(new Wall(0, 1, 0));
        model.addWall(new Wall(0, -1, 600));

        bouncePanel = new BouncePanel(800, 600);

        model.addObserver((obs, obj) -> bouncePanel.repaint());

        timer = new Timer(BounceBox.TIMER_INTERVAL, this::startAnimationAction);
        timer.start();

        JPanel controlPanel = new JPanel();
        loadFileButton = new JButton("Load File");
        calculateAreaButton = new JButton("Calculate Total Area");

        loadFileButton.addActionListener(e -> loadShapesFromUserSelectedFile());
        calculateAreaButton.addActionListener(e -> calculateAndShowTotalArea());

        controlPanel.add(loadFileButton);
        controlPanel.add(calculateAreaButton);

        add(controlPanel, BorderLayout.SOUTH);
        add(bouncePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void startAnimationAction(ActionEvent e) {
        model.moveShapes(BounceBox.TIMER_INTERVAL / 1000.0);
    }

    private void loadShapesFromFile(String filename) {
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) continue;

                String[] parts = line.split("\\s+");
                String shapeType = parts[0];

                switch (shapeType) {
                    case "Circle":
                        handleCircle(parts);
                        break;
                    case "Square":
                        handleSquare(parts);
                        break;
                    case "Rectangle":
                        handleRectangle(parts);
                        break;
                    case "Triangle":
                        handleTriangle(parts);
                        break;
                    default:
                        System.out.println(shapeType + " is not recognized.");
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: " + filename);
        }
    }

    private void handleCircle(String[] parts) {
        if (parts.length < 4) return;

        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int r = Integer.parseInt(parts[3]);

        Circle circle = new Circle(x, y, r);
        if (parts.length > 5) {
            int vx = Integer.parseInt(parts[4]);
            int vy = Integer.parseInt(parts[5]);
            circle.setVelocity(vx, vy);
        }

        if (parts.length > 8) {
            int red = Integer.parseInt(parts[6]);
            int green = Integer.parseInt(parts[7]);
            int blue = Integer.parseInt(parts[8]);
            circle.setColor(new Color(red, green, blue));
        }

        model.addShape(circle);
        bouncePanel.addDrawable(circle);
    }

    private void handleSquare(String[] parts) {
        if (parts.length < 4) return;

        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int size = Integer.parseInt(parts[3]);

        Square square = new Square(x, y, size);
        if (parts.length > 5) {
            int vx = Integer.parseInt(parts[4]);
            int vy = Integer.parseInt(parts[5]);
            square.setVelocity(vx, vy);
        }

        if (parts.length > 8) {
            int red = Integer.parseInt(parts[6]);
            int green = Integer.parseInt(parts[7]);
            int blue = Integer.parseInt(parts[8]);
            square.setColor(new Color(red, green, blue));
        }

        model.addShape(square);
        bouncePanel.addDrawable(square);
    }

    private void handleRectangle(String[] parts) {
        if (parts.length < 5) return;

        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int width = Integer.parseInt(parts[3]);
        int height = Integer.parseInt(parts[4]);

        Rectangle rectangle = new Rectangle(x, y, width, height);
        if (parts.length > 6) {
            int vx = Integer.parseInt(parts[5]);
            int vy = Integer.parseInt(parts[6]);
            rectangle.setVelocity(vx, vy);
        }

        if (parts.length > 9) {
            int red = Integer.parseInt(parts[7]);
            int green = Integer.parseInt(parts[8]);
            int blue = Integer.parseInt(parts[9]);
            rectangle.setColor(new Color(red, green, blue));
        }

        model.addShape(rectangle);
        bouncePanel.addDrawable(rectangle);
    }

    private void handleTriangle(String[] parts) {
        if (parts.length < 5) return;

        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int base = Integer.parseInt(parts[3]);
        int height = Integer.parseInt(parts[4]);

        Triangle triangle = new Triangle(x, y, base, height);
        if (parts.length > 6) {
            int vx = Integer.parseInt(parts[5]);
            int vy = Integer.parseInt(parts[6]);
            triangle.setVelocity(vx, vy);
        }

        if (parts.length > 9) {
            int red = Integer.parseInt(parts[7]);
            int green = Integer.parseInt(parts[8]);
            int blue = Integer.parseInt(parts[9]);
            triangle.setColor(new Color(red, green, blue));
        }

        model.addShape(triangle);
        bouncePanel.addDrawable(triangle);
    }

    private void loadShapesFromUserSelectedFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            model.getShapes().clear();
            bouncePanel.clearDrawables();

            String filePath = fileChooser.getSelectedFile().getPath();
            loadShapesFromFile(filePath);

            bouncePanel.repaint();
        }
    }

    private void calculateAndShowTotalArea() {
        List<Shape> shapes = model.getShapes();
        double totalArea = 0;

        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }

        JOptionPane.showMessageDialog(this,
            String.format("Total Area: %.2f", totalArea),
            "Area Calculation",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
