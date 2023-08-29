import java.util.Scanner;
import java.util.*;


public class allPairShortestPath {
    
    
    //Defining a function to find out the minimum distance from a node and returning the index.
    public int minDistance(int dist[], Boolean b[],int size) {
        int min = Integer.MAX_VALUE, index = -1;
        //let min be the largest possible value
        for (int x = 0; x < size; x++) {
        //here we have taken 'b' to check whether the node is visited or not
        //comparing distance of vertex x with each of the vertices in the graph
            if (b[x] == false && dist[x] <= min) {
                min = dist[x];
                index = x;
            }
        }
        return index;

    }

    //displaying the graph
    public static void printGraph(int dist[][],int size) {
        System.out.println("Distance from all source to all destination is");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(dist[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //Algorithm
    public int[] dijkstra(int graph[][], int src,int size) {
        int dist[] = new int[size];
        Boolean b[] = new Boolean[size];
        //Initialising all the vertices
        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            b[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < size; count++) {
            int u = minDistance(dist, b,size);
            b[u] = true;
            for (int x = 0; x < size; x++) {
                //we check 3 conditions; if the vertex is not visited, if the distance from u to x or edge ux is more than 0 and if the distance of u to source is not infinity or here- the max value
                if ((!b[x] && graph[u][x] != 0 && dist[u] != Integer.MAX_VALUE) && (dist[u] + graph[u][x] < dist[x])) {
                    dist[x] = dist[u] + graph[u][x];
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println("Enter the Graph in Adjacency Matrix way\n");
        System.out.println("Enter the number of vertices: \n");
        Scanner sc = new Scanner(System.in);
        int size;
        size=sc.nextInt();
        int graph[][] = new int[size][size];

        for (int i = 0; i < size; i++) {
            System.out.println("enter the weights of all edges connected to " + i + " edge");
            for (int j = 0; j < size; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("Adjacency Matrix Formed is:\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(graph[i][j] + "  ");
                System.out.println();
        }

        System.out.println();

        int allpair[][] = new int[size][size];

        allPairShortestPath p = new allPairShortestPath();
        for(int k=0;k<size;k++)
        {
            printGraph(allpair,size);
        for (int i = 0; i < size; i++) {
            int[] arr = p.dijkstra(graph, i,size);
            for (int j = 0; j < size; j++) {
                allpair[i][j] = arr[j];
            }}
        }
        System.out.println();
        System.out.println("All Pair Shortest Path using Dijkstras Result\n");
        printGraph(allpair,size);
    }
}
