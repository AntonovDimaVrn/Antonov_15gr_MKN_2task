package graphs;

import java.util.*;

public interface WeightedGraph {

    void addEdge(int source, int destination, int weight);

    void removeEdge(int source, int destination);

    List<Edje> getNeighbors(int vertex);

    int getVertexCount();

    int getEdgeCount();

    default boolean hasEdge(int vertex1, int vertex2) {
        for (Edje edje : getNeighbors(vertex1)) {
            if (edje.getTo() == vertex2) {
                return true;
            }
        }
        return false;
    }


    default String toDot(){
        StringBuilder sb = new StringBuilder();
        String nl = System.getProperty("line.separator");
        System.out.println(this.getClass().getName());
        boolean isDigraph = (this.getClass().getName().equals("BidirectionalWeightedGraph"));

        sb.append(isDigraph ? "digraph" : "strict graph").append(" {").append(nl);
        for (int v1 = 0; v1 < getVertexCount(); v1++) {
            int count = 0;
            for (Edje v2 : getNeighbors(v1)) {
                sb.append(String.format("  %d %s %d [label=\"%s\"]", v1, (isDigraph ? "->" : "--"), v2.getTo(), v2.getWeight())).append(nl);
                count++;
            }
            if (count == 0) {
                sb.append(v1).append(nl);
            }
        }
        sb.append("}").append(nl);

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
                for (Edje e:getNeighbors(v)) {
                    q.add(e.getTo());
                }

                visited.add(v);
            }
        }
        return false;

    }

    private void dfs(StringBuilder sb, String prefix, int prev, String weight, boolean[] visited){
        visited[prev] = true;
        sb.append(prefix);
        sb.append(String.format("+---%s--- ", weight));
        sb.append(String.format("(%d)", prev));
        sb.append(System.lineSeparator());
        List<Edje> neighbors = getNotVisited(visited, getNeighbors(prev));
        for (int i = 0; i < neighbors.size(); i++) {
            boolean isLastChild = (i == neighbors.size() - 1);
            if (isLastChild) {
                dfs(sb, prefix + "           ", neighbors.get(i).getTo(),  Integer.toString(neighbors.get(i).getWeight()), visited);
            } else {
                dfs(sb, prefix + "          |", neighbors.get(i).getTo(), Integer.toString(neighbors.get(i).getWeight()), visited);
            }
        }
    }


    default String dfs(int from){
        boolean[] visited = new boolean[getVertexCount()];
        StringBuilder sb = new StringBuilder();
        dfs(sb, "", from, "", visited);
        return sb.toString();
    }
    default String dfs(){
        boolean[] visited = new boolean[getVertexCount()];
        StringBuilder sb = new StringBuilder();
        dfs(sb, "", 0, "", visited);
        return sb.toString();
    }



    private List<Edje> getNotVisited(boolean[] visited, List<Edje> neighbors){
        List<Edje> res = new ArrayList<>();
        for (Edje n:neighbors) {
            if(!visited[n.getTo()])
                res.add(n);
        }
        return res;

    }
}

