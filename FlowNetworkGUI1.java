import java.util.*;
import javax.swing.*;
import java.awt.*;

public class FlowNetworkGUI1
{
    //for graphically displaying the graph in gui
    //In this class, we are building the GUI
    public static class FlowNetworkGUI extends JFrame
    {
        private JPanel canvas;
        
    
        public FlowNetworkGUI(int graph[][],int n)
        {
            //Building the frame
            setTitle("Graphical Representation of Flow Network");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            canvas = new JPanel()
            {
                @Override
                protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    drawGraph(g,graph,n);
                }
            };
    
            add(canvas, BorderLayout.CENTER);
        }
    
        //function for drawing the graph
        private void drawGraph(Graphics g,int graph[][],int n) {
            if (graph != null) {
                int nodeRadius = 20; // Adjust the radius as needed
    
                for (int u = 0; u < n; u++) {
                    for (int v = 0; v < n; v++) {
                        if (u < n && v < n && graph[u][v] > 0) {
                            g.setColor(Color.RED);
    
                            int x1 = u * 50 ; //first point's x coordinate
                            int y1 = 500;     //first point's y coordinate
                            int x2 = v * 50 + 150;  //second point' x coordinate
                            int y2 = 150;           //second point's y coordinate
    
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
    }
    
    
    public static class Edge
    {
        int src;
        int dest;
        int capacity;
        

        public Edge(int src, int dest, int capacity) //constructor
        {
            this.src = src;
            this.dest = dest;
            this.capacity = capacity;
        }
    }
    
    //class for queue operations
    public static class Queue {
        int size;
        int queue[];
        int front = -1, rear = -1;

        public Queue(int size) {
            this.size = size;
            this.queue = new int[size];
        }

        public boolean isFull() {
            return front == 0 && rear == size - 1;
        }

        public boolean isEmpty() {
            return front == -1;
        }

        public void enqueue(int item) {
            if (isFull()) {
                System.out.print("Queue is full");
                return;
            } else if (front == -1) {
                front = 0;
            }
            rear++;
            queue[rear] = item;
        }

        public int dequeue() {
            int item;
            if (isEmpty()) {
                System.out.println("Queue is Empty!");
                return (-1);
            } else {
                item = queue[front];
            }
            if (front >= rear) {
                front = -1;
                rear = -1;
            } else {
                front++;
            }
            return item;
        }
    }

    //for printing the adjaceny matrix
    public static void printAdjacencyMatrix(int[][] graph,int n) {
        
    
        // Print the adjacency matrix
        System.out.println("The adjaceny matrix is: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    
    public static int min(int a,int b)
    {
        if(a>b)
        {
            return b;
        }
        else
        {
            return a;
        }
    }
    public static void printGraph(int graph[][],int n) {
        System.out.println("Graph Formed is:\n");
        for (int i=0;i<n;i++)
        { 
            for(int j=0;j<n;j++)
            {
                if(graph[i][j]!=0)
                {
                System.out.println("Source: "+i+" Destination: "+j+" Capacity: "+graph[i][j]);
                }
            }
            
        }
    }
    
    
    public static int maxFlow(int graph[][], int source, int sink, int n) {
        int maxFlow = 0;
    
        while (true)
        {
            int parent[] = new int[n];
            int flow[] = new int[n];
            for (int i = 0; i <n; i++)
            {
                parent[i] = -1;
            }
            for (int i = 0; i < n; i++)
            {
                flow[i] = 0;
            }
            Queue queue = new Queue(n);
            queue.enqueue(source);
            parent[source] = source;
            flow[source] = Integer.MAX_VALUE;
            while (!queue.isEmpty()) {
                int u = queue.dequeue();
                for (int v = 0; v < n; v++) {
                    if (parent[v] == -1 && graph[u][v] > 0) {
                        parent[v] = u;
                        flow[v] = min(flow[u], graph[u][v]);
                        queue.enqueue(v);
                    }
                }
            }
    
            // If no augmenting path is found, break - there is no path from source to sink, then the loop (while(true) breaks and the process stops)
            if (parent[sink] == -1) {
                break;
            }
    
            // Update the residual graph and add the flow along the augmenting path- Augmenting the flow
            int u = sink;
            while (u != source) {
                int v = parent[u];
                graph[v][u] -= flow[sink]; //we are subtracting the capacity with the flow
                graph[u][v] += flow[sink]; //to block the edge where bottleneck capacity = 0
                u = v;
            }
            System.out.println();
            printGraph(graph, n);
            // Print the flow and bottleneck capacity for each edge in this iteration
            System.out.println("Flow in this iteration:");
            u = sink;
            //We are printing only the paths in the augmented path.
            while (u != source) {
                int v = parent[u];
                int flowofedge = flow[sink];  
                int capacity = graph[v][u] + flowofedge;
                System.out.println("Edge from " + v + " to " + u + ": Flow=" + flowofedge + ", Capacity=" + capacity);
                u = v;
            }
    
            System.out.println("Bottleneck capacity in this iteration: " + flow[sink]);
            
            //printing the augmented path
            System.out.print("Augmented Path: ");
            u = sink;
            while (u != source)
            {
                int v = parent[u];
                System.out.print(" -> " + u);
                u = v;
            }
            
            System.out.print("-> "+source);
            
            System.out.println();

            
            
            maxFlow += flow[sink]; 
        }
    
        return maxFlow;
    }
    
    
    public static void main(String args[])
    {
        int n, source, sink, a, b, c, e;
        
        
        int graph[][] = new int[][] {{0, 16, 0 ,0, 13, 0},
                    {0, 0, 12, 0, 0, 0,},
                    {0, 0, 0, 20, 9, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 4, 0, 0, 0, 14},
                    {0, 0, 7, 4, 0, 0}};

// create a flow network for this using java gui}

        // for (int i = 0; i < e; i++)
        // {
        //     System.out.print("Enter the source node: ");
        //     a = sc.nextInt();
        //     System.out.print("Enter the destination node: ");
        //     b = sc.nextInt();
        //     System.out.print("Enter the capacity of the edge: ");
        //     c = sc.nextInt();
        //     graph[a][b] = c;
        // }
        // System.out.print("Enter the source node to start the algorithm: ");
        // source = sc.nextInt();
        // System.out.print("Enter the sink node for the algorithm : ");
        // sink = sc.nextInt();

        printAdjacencyMatrix(graph, 6);
        //Create an instance of FlowNetworkGUI and make it visible
        SwingUtilities.invokeLater(() ->
        {
            FlowNetworkGUI frame = new FlowNetworkGUI(graph, 6);
            frame.setVisible(true);
        });
        
        
        int maxFlow = maxFlow(graph, 0, 3, 6);
        System.out.println("Max Flow: " + maxFlow);
}}
