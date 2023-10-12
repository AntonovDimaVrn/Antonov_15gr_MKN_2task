package drivers;

import graph_utils.GraphGenerator;
import graphs.Graph;
import graphs.UnidirectionalGraph;
import graphs.UnidirectionalWeightedGraph;
import graphs.WeightedGraph;

public class UnidirectionalWeightedGraphMain {
    public static void main(String[] args) {
        WeightedGraph graph = new UnidirectionalWeightedGraph();

        GraphGenerator.generateRandomWeightedGraph(graph, 10);

        System.out.println(graph.toDot());
        System.out.println();
        System.out.println(graph.dfs());
        System.out.println(graph.can_visit(0, 5));
    }
}
