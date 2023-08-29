import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class FlowNetworkGUI extends JFrame {
    private JPanel canvas;
    private int numNodes, numEdges, source, sink;
    private int[][] graph; // The 2D array to represent the graph

    public FlowNetworkGUI() {
        setTitle("Flow Network Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
            }
        };

        add(canvas, BorderLayout.CENTER);
    }

    private void drawGraph(Graphics g) {
        if (graph != null) {
            int nodeRadius = 20; // Adjust the radius as needed

            for (int u = 0; u < numNodes; u++) {
                for (int v = 0; v < numNodes; v++) {
                    if (u < numNodes && v < numNodes && graph[u][v] > 0) {
                        g.setColor(Color.RED);

                        int x1 = u * 50 + 15;
                        int y1 = 50;
                        int x2 = v * 50 + 15;
                        int y2 = 150;

                        // Draw nodes as circles/ellipses
                        g.fillOval(x1 - nodeRadius / 2, y1 - nodeRadius / 2, nodeRadius, nodeRadius);
                        g.fillOval(x2 - nodeRadius / 2, y2 - nodeRadius / 2, nodeRadius, nodeRadius);

                        // Label nodes with their indices
                        g.setColor(Color.BLACK);
                        g.drawString(Integer.toString(u), x1 - 5, y1 - 5);
                        g.drawString(Integer.toString(v), x2 - 5, y2 - 5);

                        // Draw edges between nodes
                        g.drawLine(x1, y1, x2, y2);
                        g.drawString(Integer.toString(graph[u][v]), (x1 + x2) / 2, (y1 + y2) / 2);
                    }
                }
            }
        }
    }

    private void initializeGraph(int numNodes, int numEdges) {
        this.numNodes = numNodes;
        this.numEdges = numEdges;

        graph = new int[numNodes][numNodes]; // Initialize the graph array

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < numEdges; i++) {
            System.out.print("Enter source node for edge " + (i + 1) + ": ");
            int u = sc.nextInt();
            System.out.print("Enter destination node for edge " + (i + 1) + ": ");
            int v = sc.nextInt();
            System.out.print("Enter capacity for edge " + (i + 1) + ": ");
            int capacity = sc.nextInt();

            // Ensure that the nodes are within valid range
            if (u >= 0 && u < numNodes && v >= 0 && v < numNodes) {
                graph[u][v] = capacity; // Set the capacity in the graph array
            } else {
                System.out.println("Invalid node indices. Edge ignored.");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlowNetworkGUI gui = new FlowNetworkGUI();
            gui.setVisible(true);

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the number of nodes: ");
            int numNodes = sc.nextInt();
            System.out.print("Enter the number of edges: ");
            int numEdges = sc.nextInt();
            gui.initializeGraph(numNodes, numEdges);
            gui.repaint();
            sc.close();
        });
    }
}
