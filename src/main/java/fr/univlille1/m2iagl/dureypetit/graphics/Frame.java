package fr.univlille1.m2iagl.dureypetit.graphics;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
<<<<<<< HEAD
*/
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.swing.mxGraphComponent;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;
import fr.univlille1.m2iagl.dureypetit.model.Commit;

public class Frame extends JFrame {

	public Frame() {
		createAndShowGui();
	}

	private void createAndShowGui() {
		JFrame frame = new JFrame("DemoGraph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Properties p = new Properties();
		try {
			p.load(new FileReader("./src/config.cfg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GitRepository gitRepo = new GitRepository(p.getProperty("JAVA_FOLDER"));

		// Liste des commits
		JList<Commit> list = new JList(gitRepo.getCommitsList().toArray());

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);

		// Graphe
		ListenableGraph<String, DefaultWeightedEdge> g = buildGraph();
		JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(g);

		mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
		layout.execute(graphAdapter.getDefaultParent());
		
//		JGraph myGraph = new JGraph();
//		JScrollPane left = new JScrollPane(myGraph);
		
		JScrollPane left = new mxGraphComponent(graphAdapter);
		JScrollPane right = new JScrollPane(list);
		left.setPreferredSize(new Dimension(250, 80));
		right.setPreferredSize(new Dimension(250, 80));

		JSplitPane topSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		topSplitPane.setOneTouchExpandable(true);
		topSplitPane.setDividerLocation(150);

		frame.add(topSplitPane);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

		//
		// frame.add(new mxGraphComponent(graphAdapter));
		// frame.pack();
		// frame.setLocationByPlatform(true);
		// frame.setVisible(true);

	}

	public ListenableGraph<String, DefaultWeightedEdge> buildGraph() {
		ListenableDirectedWeightedGraph<String, DefaultWeightedEdge> g = new ListenableDirectedWeightedGraph<String, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);

		String x1 = "x1reareraerzeazeazeerze";
		String x2 = "x2";
		String x3 = "x3";

		g.addVertex(x1);
		g.addVertex(x2);
		g.addVertex(x3);
		//
		// DefaultWeightedEdge e = g.addEdge(x1, x2);
		// g.setEdgeWeight(e, 1);
		// e = g.addEdge(x2, x3);
		// g.setEdgeWeight(e, 2);
		//
		// e = g.addEdge(x3, x1);
		// g.setEdgeWeight(e, 3);

		return g;
	}
}
