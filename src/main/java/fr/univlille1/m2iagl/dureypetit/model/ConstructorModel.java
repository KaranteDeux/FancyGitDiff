package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.List;

public class ConstructorModel extends AbstractElementModel{
	
	private String commentText;
	
	private String constructorName;
	
	private List<ParameterModel> parametersModel;
	
	public ConstructorModel(String constructorName, String commentText){
		this.constructorName = constructorName;
		this.commentText = commentText;
		parametersModel = new ArrayList<>();
	}
	
	public void addParameterModel(ParameterModel parameterModel){
		parametersModel.add(parameterModel);
	}
	
	public List<ParameterModel> getParameters(){
		return parametersModel;
	}

	@Override
	public String toString() {
		return "ConstructorModel [commentText=" + commentText
				+ ", parametersModel=" + parametersModel + "]";
	}

	@Override
	public String getName() {
		return constructorName;
	}
	
	@Override
	public String getCommentText(){
		return commentText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentText == null) ? 0 : commentText.hashCode());
		result = prime * result
				+ ((parametersModel == null) ? 0 : parametersModel.hashCode());
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
		ConstructorModel other = (ConstructorModel) obj;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (parametersModel == null) {
			if (other.parametersModel != null)
				return false;
		} else if (!parametersModel.equals(other.parametersModel))
			return false;
		return true;
	}
	
	

}
