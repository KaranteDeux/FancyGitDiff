package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.List;

public class ClassModel extends AbstractElementModel{
	
	private String name;
	
	private List<FieldModel> fieldsModel;
	
	private List<ConstructorModel> constructorsModel;
	
	private List<MethodModel> methodsModel;
	
	private String commentText;
	
	public ClassModel(String name, String commentText){
		this.name = name;
		this.commentText = commentText;
		
		fieldsModel = new ArrayList<>();
		constructorsModel = new ArrayList<>();
		methodsModel = new ArrayList<>();
		
	}
	
	public void addFieldModel(FieldModel fieldModel){
		fieldsModel.add(fieldModel);
	}
	
	public void addConstructorModel(ConstructorModel constructorModel){
		constructorsModel.add(constructorModel);
	}
	
	public void addMethodModel(MethodModel methodModel){
		methodsModel.add(methodModel);
	}

	
	@Override
	public String toString() {
		return "ClassModel [name=" + name + ", fieldsModel=" + fieldsModel
				+ ", constructorsModel=" + constructorsModel
				+ ", methodsModel=" + methodsModel + ", commentText="
				+ commentText + "]";
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getCommentText(){
		return commentText;
	}
	
	public List<FieldModel> getFields(){
		return fieldsModel;
	}
	
	public List<ConstructorModel> getConstructors(){
		return constructorsModel;
	}
	
	public List<MethodModel> getMethods(){
		return methodsModel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentText == null) ? 0 : commentText.hashCode());
		result = prime
				* result
				+ ((constructorsModel == null) ? 0 : constructorsModel
						.hashCode());
		result = prime * result
				+ ((fieldsModel == null) ? 0 : fieldsModel.hashCode());
		result = prime * result
				+ ((methodsModel == null) ? 0 : methodsModel.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassModel other = (ClassModel) obj;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (constructorsModel == null) {
			if (other.constructorsModel != null)
				return false;
		} else if (!constructorsModel.equals(other.constructorsModel))
			return false;
		if (fieldsModel == null) {
			if (other.fieldsModel != null)
				return false;
		} else if (!fieldsModel.equals(other.fieldsModel))
			return false;
		if (methodsModel == null) {
			if (other.methodsModel != null)
				return false;
		} else if (!methodsModel.equals(other.methodsModel))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
