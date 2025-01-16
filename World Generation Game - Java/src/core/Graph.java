package core;
import java.util.ArrayList;
import java.util.HashMap;

/*Functions
        - addEdge
        - addNode
        - traverse
        - size

 */
public class Graph {
    //adjList -> keys: room id, values: arraylist of int ids of connected rooms
    private HashMap<Integer, ArrayList<Integer>> adjList;
    private int size;
    public Graph() {
        adjList = new HashMap<>();
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addEdge(int firstRoom, int connectedRoom) {
        adjList.get(firstRoom).add(connectedRoom);
    }

    public void addNode(int newRoom) {
        adjList.put(newRoom, new ArrayList<>());
        size++;
    }

    //Recursively does deep first search to find connected room ids
    private void dFSRec(HashMap<Integer, Boolean> visited, int node) {
        visited.put(node, true);
        if (adjList.get(node) != null) {
            for (int i : adjList.get(node)) {
                if (!visited.containsKey(i)) {
                    dFSRec(visited, i);
                }
            }
        }
    }

    //Calls dfs to traverse through the graph
    public HashMap<Integer, Boolean> traverse(int start) {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        dFSRec(visited, start);
        return visited;
    }

    public ArrayList<Integer> printNodes(int start) {
        HashMap<Integer, Boolean> vis = traverse(start);
        ArrayList<Integer> nodes = new ArrayList<>();
        for (int i : vis.keySet()) {
            nodes.add(i);
        }
        return nodes;
    }
}

