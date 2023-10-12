package graph_utils;

import graphs.Graph;
import graphs.WeightedGraph;

import java.util.Random;

public class GraphGenerator {

    public static void generateRandomGraph(Graph graph, int countVertex){


        for (int i = 0; i < countVertex-1; i++) {
            for (int j = i+1; j < countVertex; j++) {
                if (!isNewEdge())
                    continue;
                graph.addEdge(i, j);
            }
        }
    }

    private static boolean isNewEdge(){
        return new Random().nextInt(10) <= 2;
    }

    public static void generateRandomWeightedGraph(WeightedGraph graph, int countVertex){
        for (int i = 0; i < countVertex-1; i++) {
            for (int j = i+1; j < countVertex; j++) {
                if (!isNewEdge())
                    continue;
                graph.addEdge(i, j, new Random().nextInt(9)+1);
            }
        }
    }
}
