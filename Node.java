import java.util.ArrayList;

public class Node {
    // Node Position
    private int x, y;

    // f: total cost of node
    // g: distance between current node and start node
    // h: heuristic - estimated distance from the current node to the end node
    private int f, g, h;

    public ArrayList<Node> Neighbors;

    private boolean wall;

    public Node() {
        this.x = 0;
        this.y = 0;
        this.wall = false;

        this.f = 0;
        this.g = 0;
        this.h = 0;

        Neighbors = new ArrayList<Node>();
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.wall = false;

        this.f = 0;
        this.g = 0;
        this.h = 0;

        Neighbors = new ArrayList<Node>();
    }

    // Parent Node
    private Node parent;

    // Get Position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Get Cost, Distance, and Heuristic
    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    // Get Parent Node
    public Node getParent() {
        return parent;
    }

    // Set Position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Set Cost, Distance, and Heuristic
    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    // Set Parent
    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void toggleWall() {
        this.wall = !this.wall;
    }

    public boolean isWall() {
        return this.wall;
    }

    // Set Neighbors

    public void SetNeighbors(ArrayList<Node> Neighbors) {
        this.Neighbors = Neighbors;
    }
}