package ShapeManagement;

import ShapeManagement.bouncebox.Circle;
import ShapeManagement.bouncebox.Rectangle;
import ShapeManagement.bouncebox.Square;
import ShapeManagement.bouncebox.Triangle;
import ShapeManagement.bounceboxframework.Shape;
import org.junit.jupiter.api.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeManagementGUITest {

    private ShapeManagementGUI gui;


    private File tempFile;

    @BeforeEach
    void setUp() throws IOException {

        gui = new ShapeManagementGUI();


        tempFile = File.createTempFile("test-shapes", ".txt");
        writeSampleShapesToFile(tempFile);
    }

    @AfterEach
    void tearDown() {

        gui.dispose();

        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void testLoadShapesFromFile_successfullyLoadsShapes() {

        String filePath = tempFile.getAbsolutePath();


        gui.loadShapesFromFile(filePath);

        List<Shape> shapes = gui.model.getShapes();


        assertEquals(4, shapes.size());


        assertTrue(shapes.get(0) instanceof Circle);
        assertTrue(shapes.get(1) instanceof Square);
        assertTrue(shapes.get(2) instanceof Rectangle);
        assertTrue(shapes.get(3) instanceof Triangle);
    }

    // === Helper Methods ===

    private JButton getCalculateAreaButton() {
        JPanel controlPanel = (JPanel) gui.getContentPane().getComponent(1); // 控制面板位于 SOUTH
        return (JButton) controlPanel.getComponent(1); // 第二个是 Calculate Area 按钮
    }


    private void writeSampleShapesToFile(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("// Sample Shapes\n");
            writer.write("Circle 100 100 50 1 1 255 0 0\n");
            writer.write("Square 200 200 60 2 -1 0 255 0\n");
            writer.write("Rectangle 300 300 80 50 -2 1 0 0 255\n");
            writer.write("Triangle 400 400 60 40 0 -2 128 128 128\n");
        }
    }
}
