
import java.util.ArrayList;

public class AStar {

    final static int SIZE = 25;

    public Node[][] Map;
    public ArrayList<Node> OpenSet, ClosedSet, WallSet;
    public Node StartNode, EndNode;

    public static void main(String[] args) {
        new AStar();
    }

    public AStar() {
        System.out.println("AStar");

        // Initialize Storage Lists
        Map = new Node[SIZE][SIZE];
        OpenSet = new ArrayList<Node>();
        ClosedSet = new ArrayList<Node>();
        WallSet = new ArrayList<Node>();

        Setup();

        // Set Default Start and End Nodes
        SetStartNode(Map[0][0]);
        SetEndNode(Map[SIZE - 1][SIZE - 1]);
    }

    public void SetStartNode(Node N) {
        this.StartNode = N;
    }

    public void SetEndNode(Node N) {
        this.EndNode = N;
    }

    public void Setup() {

        // Create Node Map
        CreateNodeMap();

        // Set Node neighbors
        SetNeighbors();

    }

    public void CreateNodeMap() {

        // Create a Map of nodes with a given size

        for (int col = 0; col < SIZE; col++) {

            for (int row = 0; row < SIZE; row++) {

                Map[col][row] = new Node(row, col);

            }

        }
    }

    public void SetNeighbors() {
        // Set neighbor nodes for given node

        for (int col = 0; col < SIZE; col++) {

            for (int row = 0; row < SIZE; row++) {

                Map[col][row].SetNeighbors(GetNeighbors(row, col));

            }
        }

    }

    // Node can have at most 8 neighbors

    // +----------+----------+----------+
    // | A[-1,-1] | B[+0,-1] | C[+1,-1] |
    // +----------+----------+----------+
    // | D[-1,+0] | X[+0,+0] | E[+1,+0] |
    // +----------+----------+----------+
    // | F[-1,+1] | G[+0,+1] | H[+1,+1] |
    // +----------+----------+----------+

    public ArrayList<Node> GetNeighbors(int x, int y) {

        // Get neighbor nodes from given node position

        // Perform edge and corner checks

        ArrayList<Node> Neighbors = new ArrayList<Node>();

        // Neighbor A[-1,-1]
        if (x > 0 && y > 0) {
            Neighbors.add(Map[x - 1][y - 1]);
        }

        // Neighbor B[+0,-1]
        if (y > 0) {
            Neighbors.add(Map[x][y - 1]);
        }

        // Neighbor C[+1,-1]
        if (x < SIZE - 1 && y > 0) {
            Neighbors.add(Map[x + 1][y - 1]);
        }

        // Neighbor D[-1,+0]
        if (x > 0) {
            Neighbors.add(Map[x - 1][y]);
        }

        // Neighbor E[+1,+0]
        if (x < SIZE - 1) {
            Neighbors.add(Map[x + 1][y]);
        }

        // Neighbor F[-1,+1]
        if (x > 0 && y < SIZE - 1) {
            Neighbors.add(Map[x - 1][y + 1]);
        }

        // Neighbor G[+0,+1]
        if (y < SIZE - 1) {
            Neighbors.add(Map[x][y + 1]);
        }

        // Neighbor H[+1,+1]
        if (x < SIZE - 1 && y < SIZE - 1) {
            Neighbors.add(Map[x + 1][y + 1]);
        }

        return Neighbors;
    }

    /* ===== Walls ===== */

    public void AddWall(Node N) {
        // Add Wall to Wall list
        WallSet.add(N);

        // Toggle Node Wall to True
        N.toggleWall();
    }

    public void RemoveWall(Node N) {
        // Remove Wall from Wall list
        WallSet.remove(N);

        // Toggle Node Wall to False
        N.toggleWall();
    }

    /* ===== OpenSet & ClosedSet ===== */

    public void AddToOpen(Node N) {
        // Add Node to OpenSet
        OpenSet.add(N);
    }

    public void RemoveFromOpen(Node N) {
        // Remove Node from OpenSet
        OpenSet.remove(N);
    }

    public void AddToClosed(Node N) {
        // Add Node to ClosedSet
        ClosedSet.add(N);
    }

    public void RemoveFromClosed(Node N) {
        // Remove Node from ClosedSet
        ClosedSet.remove(N);
    }

    public void MoveFromOpenToClosed(Node N) {
        // Move Node from OpenSet to ClosedSet
        RemoveFromOpen(N);
        AddToClosed(N);
    }

    /* ===== Getters ====== */

    public ArrayList<Node> getOpenSet() {
        return OpenSet;
    }

    public ArrayList<Node> getClosedSet() {
        return ClosedSet;
    }

    public ArrayList<Node> getWalls() {
        return WallSet;
    }

    public Node getStartNode() {
        return this.StartNode;
    }

    public Node getEndNode() {
        return this.EndNode;
    }

    public Node[][] getMap() {
        return Map;
    }

    public Node getNodeFromPosition(int x, int y) {
        return Map[x][y];
    }
}