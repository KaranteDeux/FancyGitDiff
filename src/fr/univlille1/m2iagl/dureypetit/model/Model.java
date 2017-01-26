package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {

	
	public static Model currentModel;
	
	public static Model masterBranchModel = new Model();
	public static Model pullRequestBranchModel = new Model();
	
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
