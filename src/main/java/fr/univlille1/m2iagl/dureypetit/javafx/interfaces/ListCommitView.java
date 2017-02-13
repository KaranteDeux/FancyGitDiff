package fr.univlille1.m2iagl.dureypetit.javafx.interfaces;

import java.util.List;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.CellType;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ElementCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.TextCell;
import fr.univlille1.m2iagl.dureypetit.javafx.layout.Layout;
import fr.univlille1.m2iagl.dureypetit.javafx.layout.OrderedLayout;
import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.CommitModel;
import fr.univlille1.m2iagl.dureypetit.model.ConfigConstants;
import fr.univlille1.m2iagl.dureypetit.model.MethodModel;
import fr.univlille1.m2iagl.dureypetit.model.ParameterModel;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

public class ListCommitView extends ListView<CommitModel>{

	private GitRepository gitRepository;
	private Graph graph;

	public ListCommitView(GitRepository gitRepository){
		this.gitRepository = gitRepository;
		
		setItems(FXCollections.observableList(gitRepository.getCommitsList()));

		setStyle(".list-view .list-cell:even { -fx-background-color: blue; -fx-text-fill: black; } .list-view .list-cell:odd { -fx-background-color: blue; -fx-text-fill: black; }");
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				CommitModel clickedCommit = getSelectionModel().getSelectedItem();

				GraphicModel model = graph.getModel();

				graph.beginUpdate();

				model.removeEverything();

				for(ClassModel classModel : clickedCommit.getClassChanged()){
					String className = classModel.getName();
					model.addCell(className, CellType.CLASS, className, classModel);

					for(MethodModel methodModel : classModel.getMethods()){
						String methodName = methodModel.getName();
						model.addCell(className + ":" + methodName, CellType.METHOD, methodName, methodModel);
						model.addEdge(className, className + ":" + methodName);

						if(ConfigConstants.SHOW_PARAMETERS){
							for(ParameterModel parameterModel : methodModel.getParameters()){
								String parameterName = parameterModel.getName();
								model.addCell(className + ":" + methodName + ":" + parameterName, CellType.PARAMETER, parameterName, parameterModel);
								model.addEdge(className + ":" + methodName, className + ":" + methodName + ":" + parameterName);

							}
						}
					}

				}

				graph.endUpdate();

				Layout layout = new OrderedLayout(graph);
				layout.execute();

			}
		});
	}
	
	public void selectCommitWhichChangedElement(ElementCell element){
		TextCell textCell = element.getTextCell();
		List<CommitModel> commits = gitRepository.getCommitsList();

		MultipleSelectionModel<CommitModel> selectionModel = getSelectionModel();

		selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

		for(CommitModel commitModel : commits){
			
			for(ClassModel classModel : commitModel.getClassChanged()){
				
				if(classModel.equals(element.getElementModel())){
					selectionModel.select(commitModel);
				}
				
				for(MethodModel methodModel : classModel.getMethods()){
					
					if(methodModel.equals(element.getElementModel())){
						selectionModel.select(commitModel);
					}
					
					for(ParameterModel parameterModel : methodModel.getParameters()){
						if(parameterModel.equals(element.getElementModel())){
							selectionModel.select(commitModel);
						}
					}
				}
			}
		}
	}
	
	public void setGraph(Graph graph){
		this.graph = graph;
	}
}
