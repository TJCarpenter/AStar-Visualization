import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JPanel implements MouseListener, MouseMotionListener {

    private JFrame window;
    private AStar PathFinder;

    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {

        addMouseListener(this);
        addMouseMotionListener(this);
        setLayout(null);

        PathFinder = new AStar();

        window = new JFrame("A* Visualization");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setPreferredSize(new Dimension(625, 625));
        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        final int NODE_SIZE = 25;

        int height = getHeight();
        int width = getWidth();

        // Draw Base Grid
        g.setColor(Color.lightGray);
        for (int col = 0; col < height; col += NODE_SIZE) {
            for (int row = 0; row < width; row += NODE_SIZE) {
                g.drawRect(row, col, NODE_SIZE, NODE_SIZE);
            }
        }

        // Draw Start Point
        g.setColor(Color.BLUE);
        Node StartNode = PathFinder.getStartNode();
        g.fillRect(StartNode.getY() * 25, StartNode.getX() * 25, NODE_SIZE, NODE_SIZE);

        // Draw End Point
        g.setColor(Color.red);
        Node EndNode = PathFinder.getEndNode();
        g.fillRect(EndNode.getY() * 25, EndNode.getX() * 25, NODE_SIZE, NODE_SIZE);

        // Draw Walls
        g.setColor(Color.black);
        for (Node WallNode : PathFinder.getWalls()) {
            g.fillRect(WallNode.getY() * 25, WallNode.getX() * 25, NODE_SIZE, NODE_SIZE);
        }

    }

    /* ===== MouseListenter ===== */
    @Override
    public void mouseClicked(MouseEvent e) {
        MapMouseEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /* ===== MouseMotionListenter ===== */
    @Override
    public void mouseDragged(MouseEvent e) {
        MapMouseEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void MapMouseEvent(MouseEvent e) {

        // Toggle Wall
        if (SwingUtilities.isLeftMouseButton(e)) {

            // Get Node Positon
            int x = (e.getX() - (e.getX() % 25)) / 25;
            int y = (e.getY() - (e.getY() % 25)) / 25;

            // Get Node
            Node WallNode = PathFinder.getNodeFromPosition(x, y);

            // Check if WallNode is Start or End Nodes
            if (PathFinder.getStartNode() != WallNode && PathFinder.getEndNode() != WallNode) {

                // Add to WallSet
                PathFinder.AddWall(WallNode);

            } else {

                System.err.println("Unable to add wall. Node is either StartNode or EndNode");

            }

            repaint();
        }

    }

    public void MapMouseDrag(MouseEvent e) {

        // Get Hovered Node
        int x = (e.getX() - (e.getX() % 25)) / 25;
        int y = (e.getY() - (e.getY() % 25)) / 25;

        // Get Node
        Node WallNode = PathFinder.getNodeFromPosition(x, y);

        // Check if WallNode is Start or End Nodes
        if (PathFinder.getStartNode() != WallNode && PathFinder.getEndNode() != WallNode) {

            // Check if node is already in wall set
            if (!PathFinder.WallSet.contains(WallNode)) {
                // Add to WallSet
                PathFinder.AddWall(WallNode);
            }

        } else {

            System.err.println("Unable to add wall. Node is either StartNode or EndNode");

        }

        repaint();
    }
}