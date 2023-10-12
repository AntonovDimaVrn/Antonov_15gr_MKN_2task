package graphs;

import java.util.*;

public interface Graph {

    void addEdge(int source, int destination);


    void removeEdge(int source, int destination);


    List<Integer> getNeighbors(int vertex);

    int getVertexCount();

    int getEdgeCount();


    default boolean hasEdge(int vertex1, int vertex2) {
        for (Integer v : getNeighbors(vertex1)) {
            if (v == vertex2) {
                return true;
            }
        }
        return false;
    }


    default String toDot() {
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        System.out.println(this.getClass());
        boolean isDigraph =  (!this.getClass().getName().equals("graphs.BidirectionalGraph"));

        sb.append(isDigraph ? "digraph" : "strict graph").append(" {").append(nl);
        for (int v1 = 0; v1 < getVertexCount(); v1++) {
            int count = 0;
            for (Integer v2 : getNeighbors(v1)) {
                sb.append(String.format("  %d %s %d ", v1, (isDigraph ? "->" : "--"), v2)).append(nl);
                count++;
            }
            if (count == 0) {
                sb.append(v1).append(nl);
            }
        }
        sb.append("}").append(nl);

        return sb.toString();
    }

    default String dfs(int from){
        boolean[] visited = new boolean[getVertexCount()];
        StringBuilder sb = new StringBuilder();
        dfs(sb, "", from, visited);
        return sb.toString();
    }
    default String dfs(){
        boolean[] visited = new boolean[getVertexCount()];
        StringBuilder sb = new StringBuilder();
        dfs(sb, "", 0, visited);
        return sb.toString();
    }

    default boolean can_visit(int from, int to){
        Queue<Integer> q = new LinkedList<>();
        q.add(from);
        Set<Integer> visited = new TreeSet<>();
        while (!q.isEmpty()){
            int v = q.poll();
            if (v == to)
                return true;
            if(!visited.contains(v)) {
                q.addAll(getNeighbors(v));
                visited.add(v);
            }
        }
        return false;

    }
    private void dfs(StringBuilder sb, String prefix, int prev, boolean[] visited){
        visited[prev] = true;
        sb.append(prefix);
        sb.append("+------- ");
        sb.append(String.format("(%d)", prev));
        sb.append(System.lineSeparator());

        List<Integer> neighbors = getNotVisited(visited, getNeighbors(prev));
        for (int i = 0; i < neighbors.size(); i++) {
            boolean isLastChild = (i == neighbors.size() - 1);

            if (isLastChild) {
                dfs(sb, prefix + "           ", neighbors.get(i), visited);
            } else {
                dfs(sb, prefix + "          |", neighbors.get(i), visited);
            }
        }
    }

    private List<Integer> getNotVisited(boolean[] visited, List<Integer> neighbors){
        List<Integer> res = new ArrayList<>();
        for (Integer n:neighbors) {
            if(!visited[n])
                res.add(n);
        }
        return res;

    }

}
