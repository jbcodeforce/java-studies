package jbcodeforce.fun.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    String name;
    List<Graph> vertices;
    
    public Graph(String n) {
        this.name = n;
        this.vertices = new ArrayList<>();
    }

    public void addVertice(Graph g) {
        vertices.add(g);
    }

    public static List<Graph> dfs(Graph start){
        List<Graph> visited = new ArrayList<Graph>();
        return dfsRecursive(start,visited);
    }

    public  static List<Graph> dfsRecursive(Graph start,  List<Graph> visited){
        if (! visited.contains(start)) {
            visited.add(start);
            for (Graph g : start.vertices) {
                dfsRecursive(g, visited);
            }
        }
        return visited;
    }

    public String toString() {
        return name;
    }

}
