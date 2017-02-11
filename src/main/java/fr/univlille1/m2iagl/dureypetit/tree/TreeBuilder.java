package fr.univlille1.m2iagl.dureypetit.tree;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultEdge;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.CommitModel;
import fr.univlille1.m2iagl.dureypetit.model.FieldModel;
import fr.univlille1.m2iagl.dureypetit.model.MethodModel;
import fr.univlille1.m2iagl.dureypetit.model.ParameterModel;

public class TreeBuilder {

	private CommitModel commit;
	private ListenableGraph<String, DefaultEdge> graph;

	public TreeBuilder(CommitModel commit, ListenableGraph<String, DefaultEdge> g){
		this.commit = commit;
		this.graph = g;
	}

	public ListenableGraph<String, DefaultEdge> build(){

		for(ClassModel classModel : commit.getClassChanged()){
			graph.addVertex(classModel.getStringToPrint());

			for(FieldModel fieldModel : classModel.getFields()){
				graph.addVertex(fieldModel.getStringToPrint());
				graph.addEdge(classModel.getStringToPrint(), fieldModel.getStringToPrint());
			}

			for(MethodModel methodModel : classModel.getMethods()){
				graph.addVertex(methodModel.getStringToPrint());
				graph.addEdge(classModel.getStringToPrint(), methodModel.getStringToPrint());
				
				for(ParameterModel parameterModel : methodModel.getParameters()){
					graph.addVertex(parameterModel.getStringToPrint());
					graph.addEdge(methodModel.getStringToPrint(), parameterModel.getStringToPrint());
				}
			}

		}
		return graph;
	}

}
