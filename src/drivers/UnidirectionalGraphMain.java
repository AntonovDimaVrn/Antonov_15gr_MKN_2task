package drivers;

import graph_utils.GraphGenerator;
import graphs.Graph;
import graphs.UnidirectionalGraph;

public class UnidirectionalGraphMain {
    public static void main(String[] args) {
        Graph graph = new UnidirectionalGraph();

        GraphGenerator.generateRandomGraph(graph, 15);

        System.out.println(graph.toDot());
        System.out.println();
        System.out.println(graph.dfs());
        System.out.println(graph.can_visit(0, 5));
    }
}
