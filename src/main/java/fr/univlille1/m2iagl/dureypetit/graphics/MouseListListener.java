package fr.univlille1.m2iagl.dureypetit.graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;

import org.jgraph.graph.Edge;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;

import fr.univlille1.m2iagl.dureypetit.model.Commit;
import fr.univlille1.m2iagl.dureypetit.tree.TreeBuilder;

public class MouseListListener extends MouseAdapter{

	private JFrame frame;
	private JList<Commit> list;
	private JGraphXAdapter<String, DefaultEdge> graphAdapter;
	private ListenableGraph<String, DefaultEdge> g;

	public MouseListListener(JFrame frame, JList<Commit> list, ListenableGraph<String, DefaultEdge> g, JGraphXAdapter<String, DefaultEdge> graphAdapter) {
		this.frame = frame;
		this.list = list;
		this.graphAdapter = graphAdapter;
		this.g = g;
	}
	public void mouseClicked(MouseEvent e) {

		Commit selectedItem = (Commit) list.getSelectedValue();
		System.out.println(selectedItem.toString());

		Set<DefaultEdge> edges = g.edgeSet();
		Set<String> vertices = g.vertexSet();
		
		g.removeAllEdges(edges);
		g.removeAllVertices(vertices);
		
		new TreeBuilder(selectedItem, g).build();
		
		
		
		frame.repaint();

	}
}
