package graphs;

import java.util.*;

public class UnidirectionalGraph implements Graph{

    private List<Set<Integer>> links = new ArrayList<>();

    private Integer edges = 0;
    private Integer vertexes = 0;
    @Override
    public void addEdge(int source, int destination) {
        int maxV = Math.max(source, destination);
        for (; vertexes <= maxV; vertexes++) {
            links.add(null);
        }
        if (!hasEdge(source, destination)) {
            if (links.get(source) == null) {
                links.set(source, new TreeSet<>());
            }
            links.get(source).add(destination);
            edges++;
        }
    }

    @Override
    public void removeEdge(int source, int destination) {
        edges -= countingRemove(links.get(source), destination);
    }
    private int countingRemove(Collection<Integer> list, int v) {
        int count = 0;
        if (list != null) {
            for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
                if (it.next().equals(v)) {
                    it.remove();
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        return links.get(vertex) == null ? new ArrayList() : new ArrayList<>(links.get(vertex));

    }

    @Override
    public int getVertexCount() {
        return vertexes;
    }

    @Override
    public int getEdgeCount() {
        return edges;
    }
}
