package fr.univlille1.m2iagl.dureypetit.graphics;
/*
<<<<<<< HEAD
*/
import javax.swing.JFrame;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

public class Frame extends JFrame{

	public Frame(){
		createAndShowGui();
	}
	private void createAndShowGui() {
		JFrame frame = new JFrame("DemoGraph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ListenableGraph<String, DefaultWeightedEdge> g = buildGraph();
		JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(g);

		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());

		frame.add(new mxGraphComponent(graphAdapter));

		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	public ListenableGraph<String, DefaultWeightedEdge> buildGraph() {
		ListenableDirectedWeightedGraph<String, DefaultWeightedEdge> g = 
				new ListenableDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		String x1 = "x1";
		String x2 = "x2";
		String x3 = "x3";

		g.addVertex(x1);
		g.addVertex(x2);
		g.addVertex(x3);

		DefaultWeightedEdge e = g.addEdge(x1, x2);
		g.setEdgeWeight(e, 1);
		e = g.addEdge(x2, x3);
		g.setEdgeWeight(e, 2);

		e = g.addEdge(x3, x1);
		g.setEdgeWeight(e, 3);

		return g;
	}
}
