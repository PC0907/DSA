import java.util.*;
public class Max_flow
{
    public static class Edge
    {
        int src;
        int dest;
        int capacity;
        int flow;
        public Edge residual;

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

    
    public static void bfs(ArrayList<Edge> edges, int visited[], int source, int n,int sink) {
        System.out.print(source + " ");
        visited[source] = 1;
    
        Queue queue = new Queue(n * n); // Adjust the queue size accordingly
        queue.enqueue(source);
    
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>(); //2 d array list
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    
        for (Edge edge : edges) {
            adjacencyList.get(edge.src).add(edge.dest);
        }
    
        while (!queue.isEmpty()) {
            int u = queue.dequeue();
            if(u==sink)
            {
                break;  
            }
    
            for (int v : adjacencyList.get(u)) 
            {
                
                if (visited[v] == 0) {
                    System.out.print(v + " ");
                    visited[v] = 1;
                    queue.enqueue(v);
                }
            }
        }
    }
    
    public static void printGraph(ArrayList<Edge> edges) {
        System.out.println("Graph Formed is:\n");
        for (Edge edge : edges) {
            System.out.println("Source: " + edge.src + " -> Destination: " + edge.dest + " (Capacity: " + edge.capacity + ")");
        }
    }
    
    public static void printAdjacencyMatrix(ArrayList<Edge> edges, int n) {
        int[][] adjacencyMatrix = new int[n][n];
    
        // Initialize the matrix with all zeros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    
        // Populate the matrix based on the edges
        for (Edge edge : edges) {
            adjacencyMatrix[edge.src][edge.dest] = edge.capacity;
            adjacencyMatrix[edge.dest][edge.src] = edge.capacity; //provided its not a directed graph
        }
    
        
        // Print the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    

    public static void main(String args[]) {
        int n,source,sink,a,b,c,e;
        int visited[] = new int[100];
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        n = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        e=sc.nextInt();
        
        
    
        
        
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for(int i=0;i<e;i++)
        {
            System.out.print("Enter the source node: ");
            a=sc.nextInt();
            System.out.print("Enter the destination node: ");
            b=sc.nextInt();
            System.out.print("Enter the capacity of the edge: ");
            c=sc.nextInt();
            edges.add(new Edge(a,b,c));
        }
        //display the graph in GUI/JavaFX 
        printGraph(edges);

        System.out.print("The Adjaceny matrix of the graph is : ");
        printAdjacencyMatrix(edges, n);

        System.out.print("Enter the source node: ");
        source = sc.nextInt();
        System.out.print("Enter the sink node: ");
        sink=sc.nextInt();
        
        System.out.print("BFS OF THE GRAPH IS: ");
        bfs(edges, visited, source, n,sink);
    }
}