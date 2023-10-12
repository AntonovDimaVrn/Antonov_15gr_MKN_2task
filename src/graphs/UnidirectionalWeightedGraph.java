package graphs;



import java.util.*;


public class UnidirectionalWeightedGraph implements WeightedGraph {

    private List<Set<Edje>> links = new ArrayList<>();

    private Integer edges = 0;
    private Integer vertexes = 0;

    public UnidirectionalWeightedGraph() {
    }


    @Override
    public List<Edje> getNeighbors(int vertex) {
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

    @Override
    public void addEdge(int v1, int v2, int weight) {
        int maxV = Math.max(v1, v2);
        for (; vertexes <= maxV; vertexes++) {
            links.add(null);
        }
        if (!hasEdge(v1, v2)) {
            if (links.get(v1) == null) {
                links.set(v1, new TreeSet<>((o1, o2) -> o1.getTo() - o2.getTo()));
            }
            links.get(v1).add(new Edje(v2, weight));
            edges++;
        }
    }


    @Override
    public void removeEdge(int v1, int v2) {
        edges -= countingRemove(links.get(v1), v2);
    }


    private int countingRemove(Collection<Edje> list, int v) {
        int count = 0;
        if (list != null) {
            for (Iterator<Edje> it = list.iterator(); it.hasNext(); ) {
                if (it.next().getTo().equals(v)) {
                    it.remove();
                    count++;
                }
            }
        }
        return count;
    }

}
