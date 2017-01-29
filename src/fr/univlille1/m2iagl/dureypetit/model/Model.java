package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Model {

	
	public static Map<String, Model> models;
	public static Model currentModel;
	
	private List<ClassModel> classModels;
	
	public Model(){
		classModels = new ArrayList<>();
	}
	
	public void addClassModel(ClassModel classModel){
		classModels.add(classModel);
	}
	
	public List<ClassModel> getClassModel(){
		return classModels;
	}

	@Override
	public String toString() {
		return "Model [classModels=" + classModels + "]";
	}
	
}
