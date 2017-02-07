package fr.univlille1.m2iagl.dureypetit.tree;

import java.util.List;
import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableDirectedGraph;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.Commit;
import fr.univlille1.m2iagl.dureypetit.model.FieldModel;
import fr.univlille1.m2iagl.dureypetit.model.MethodModel;

public class TreeBuilder {
	
	private Commit commit;
	
	public TreeBuilder(Commit commit){
		this.commit = commit;
	}
	
	public ListenableGraph<String, DefaultEdge> build(){
		Set<String> filenames = commit.getFilesChanged();
		
		ListenableGraph<String, DefaultEdge> graph = new ListenableDirectedGraph<>(DefaultEdge.class);
		
		for(String filename : filenames){
			List<ClassModel> classModels = commit.get(filename);
			for(ClassModel classModel : classModels){
				graph.addVertex(classModel.getStringToPrint());
				
				for(FieldModel fieldModel : classModel.getFields()){
					graph.addVertex(fieldModel.getStringToPrint());
					graph.addEdge(classModel.getStringToPrint(), fieldModel.getStringToPrint());
				}
				
				for(MethodModel methodModel : classModel.getMethods()){
					graph.addVertex(methodModel.getStringToPrint());
					graph.addEdge(classModel.getStringToPrint(), methodModel.getStringToPrint());
				}
			}
			
		}
		return graph;
	}

}
