import java.util.*;

public class EdmondsKarp {

    static final int V = 6; // Number of vertices in the graph

    // Returns true if there is a path from source 's' to sink 't' in residual graph.
    // Also fills parent[] to store the path
    static boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not visited
        boolean visited[] = new boolean[V];
        Arrays.fill(visited, false);

        // Create a queue, enqueue source vertex and mark source vertex as visited
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        // If we reached the sink in BFS starting from source, then return true, else false
        return visited[t];
    }

    // Returns the maximum flow from s to t in the given graph
    static int edmondsKarp(int graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph with given capacities in the original graph as residual capacities
        // Residual graph where rGraph[i][j] indicates residual capacity of edge from i to j (if there is an edge. If rGraph[i][j] is 0, then there is not)
        int rGraph[][] = new int[V][V];
        for (u = 0; u < V; u++) {
            for (v = 0; v < V; v++) {
                rGraph[u][v] = graph[u][v];
            }
        }

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        int max_flow = 0; // There is no flow initially

        // Augment the flow while there is a path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges along the path filled by BFS
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // Update residual capacities of the edges and reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;

            // Print the augmented path
            System.out.print("Augmented path: ");
            for (v = t; v != s; v = parent[v]) {
                System.out.print(v + " <- ");
            }
            System.out.println(s);
        }

        return max_flow;
    }

    public static void main(String[] args) {
        // Example flow network represented as an adjacency matrix
        int graph[][] = new int[][]{
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        int source = 0, sink = 5;

        int maxFlow = edmondsKarp(graph, source, sink);
        System.out.println("Maximum flow: " + maxFlow);
    }
}
