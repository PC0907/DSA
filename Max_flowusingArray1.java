import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Max_flowusingArray1
{
    // public class FlowNetworkGUI extends JFrame
    // {
    //     private JPanel canvas;
    //     private int numNodes, numEdges, source, sink;
    //     private ArrayList<Point> nodePositions;
    //     private ArrayList<Edge> edges;
    
    //     public FlowNetworkGUI()
    //     {
    //         setTitle("Flow Network Visualization");
    //         setSize(800, 600);
    //         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //         canvas = new JPanel()
    //         {
    //             @Override
    //             protected void paintComponent(Graphics g)
    //             {
    //                 super.paintComponent(g);
    //                 drawGraph(g);
    //             }
    //         };
    
    //         add(canvas, BorderLayout.CENTER);
    //     }
    
    //     private void drawGraph(Graphics g) {
    //         if (nodePositions != null) {
    //             for (Point pos : nodePositions) {
    //                 g.setColor(Color.BLUE);
    //                 g.fillOval(pos.x, pos.y, 30, 30);
    //             }
    
    //             if (edges != null) {
    //                 for (Edge edge : edges) {
    //                     g.setColor(Color.RED);
    //                     Point u = nodePositions.get(edge.src);
    //                     Point v = nodePositions.get(edge.dest);
    //                     g.drawLine(u.x + 15, u.y + 15, v.x + 15, v.y + 15);
    //                     g.drawString(Integer.toString(edge.capacity), (u.x + v.x) / 2, (u.y + v.y) / 2);
    //                 }
    //             }
    //         }
    //     }}
    public static class Edge
    {
        int src;
        int dest;
        int capacity;
        int flow;

        public Edge(int src, int dest, int capacity) //constructor
        {
            this.src = src;
            this.dest = dest;
            this.capacity = capacity;
        }
    }
    
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
    public static void printAdjacencyMatrix(int[][] graph,int n) {
        
    
        // Print the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // public static void bfs(int graph[][], int visited[], int source, int n, int sink) {
    //     System.out.print(source + " ");
    //     visited[source] = 1;

    //     Queue queue = new Queue(n * n); // Adjust the queue size accordingly
    //     queue.enqueue(source);

    //     while (!queue.isEmpty()) {
    //         int u = queue.dequeue();
    //         if(u == sink) {
    //             break;
    //         }

    //         for (int v = 0; v < n; v++) {
    //             if (graph[u][v] != 0 && visited[v] == 0) {
    //                 System.out.print(v + " ");
    //                 visited[v] = 1;
    //                 queue.enqueue(v);
    //             }
    //         }
    //     }
    // }
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
    
    
    public static int maxFlow(int graph[][], int source, int sink,int n) {
        int parent[] = new int[n];
        int maxFlow = 0;
        
        while (true)
        {
            // Run BFS to find an augmenting path
            for (int i = 0; i < parent.length; i++)
            {
                parent[i] = -1;
            }
            Queue queue=new Queue(n);
            queue.enqueue(source);
            parent[source] = source;
            
            while (!queue.isEmpty())
            {
                int u = queue.dequeue();
                for (int v = 0; v < n; v++)
                {
                    if (parent[v] == -1 && graph[u][v] > 0)
                    {
                        parent[v] = u;
                        queue.enqueue(v);
                    }
                }
            }
            
            // If no augmenting path is found, break - there is no path from source to sink
            if (parent[sink] == -1)
            {
                break;
            }
            
            // Find the minimum capacity along the augmenting path
            int pathCapacity = Integer.MAX_VALUE;
            int current = sink;
            while (current != source)
            { 
                int u = parent[current];
                pathCapacity = min(pathCapacity, graph[u][current]);
                current = u;
            }
            
            // Update the residual graph and add the flow
            current = sink;
            while (current != source)
            {
                int u = parent[current];
                graph[u][current] -= pathCapacity;
                graph[current][u] += pathCapacity;
                current = u;
            }
            
            
            System.out.print("Augmented Path: ");
            current = sink;
            while (current != source)
            {
                int u = parent[current];
                System.out.print(" -> " + current);
                current = u;
            }
            
            System.out.print("-> "+source);
            
            System.out.println();
            
            maxFlow += pathCapacity;
        }
        
        return maxFlow;
    }
    
    public static void main(String args[])
    {
        int n, source, sink, a, b, c, e;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        n = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        e = sc.nextInt();
    
        int graph[][] = new int[n][n];
        for (int i = 0; i < e; i++) {
            System.out.print("Enter the source node: ");
            a = sc.nextInt();
            System.out.print("Enter the destination node: ");
            b = sc.nextInt();
            System.out.print("Enter the capacity of the edge: ");
            c = sc.nextInt();
            graph[a][b] = c;
        }

        System.out.print("The Adjacency matrix of the graph is : ");
        printAdjacencyMatrix(graph,n);
        System.out.print("Enter the source node to start BFS: ");
        source = sc.nextInt();
        System.out.print("Enter the sink node: ");
        sink = sc.nextInt();
        
        int maxFlow = maxFlow(graph, source,sink,n);
        System.out.println("Max Flow: " + maxFlow);

        
        sc.close();
    }
}
