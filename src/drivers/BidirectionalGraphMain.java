package drivers;

import graph_utils.GraphGenerator;
import graphs.BidirectionalGraph;
import graphs.Graph;
import graphs.UnidirectionalGraph;

public class BidirectionalGraphMain {
    public static void main(String[] args) {
        Graph graph = new BidirectionalGraph();

        GraphGenerator.generateRandomGraph(graph, 5);

        System.out.println(graph.toDot());
        System.out.println();
        System.out.println(graph.dfs());

        System.out.println(graph.can_visit(0, 5));

    }
}
