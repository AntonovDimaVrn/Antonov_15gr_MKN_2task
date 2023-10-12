package graphs;

public class BidirectionalGraph extends UnidirectionalGraph{
    @Override
    public void removeEdge(int v1, int v2) {
        super.removeEdge(v1, v2);
        super.removeEdge(v2, v1);
    }

    @Override
    public void addEdge(int v1, int v2) {
        super.addEdge(v1, v2);
        super.addEdge(v2, v1);
    }
}
