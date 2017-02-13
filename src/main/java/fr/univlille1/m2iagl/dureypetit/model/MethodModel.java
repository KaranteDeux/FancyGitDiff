package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.List;

public class MethodModel extends AbstractElementModel{
	
	private String returnType;
	
	private String name;
	
	private String visibility;
	
	private List<ParameterModel> parametersModel;
	
	private String commentText;
	
	private String commentTextReturn;
	
	public MethodModel(String visibility, String returnType, String name, String commentText){
		this.visibility = visibility;
		this.returnType = returnType;
		this.name = name;
		
		this.commentText = commentText;

		this.parametersModel = new ArrayList<>();
	}

	public void addParameterModel(ParameterModel parameterModel){
		parametersModel.add(parameterModel);
	}
	
	public void addParametersModel(List<ParameterModel> parametersModel){
		for(ParameterModel parameterModel : parametersModel){
			addParameterModel(parameterModel);
		}
	}
	
	@Override
	public String toString() {
		return "MethodModel [returnType=" + returnType + ", name=" + name
				+ ", visibility=" + visibility + ", parametersModel="
				+ parametersModel + ", commentText=" + commentText
				+ ", commentTextReturn=" + commentTextReturn + "]";
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getVisibility(){
		return visibility;
	}
	
	public String getReturnType(){
		return returnType;
	}
	
	public List<ParameterModel> getParameters(){
		return parametersModel;
	}
	
	public void setCommentTextReturn(String commentTextReturn){
		this.commentTextReturn = commentTextReturn;
	}
	
	public String getCommentTextReturn(){
		return commentTextReturn;
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
		result = prime
				* result
				+ ((commentTextReturn == null) ? 0 : commentTextReturn
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parametersModel == null) ? 0 : parametersModel.hashCode());
		result = prime * result
				+ ((returnType == null) ? 0 : returnType.hashCode());
		result = prime * result
				+ ((visibility == null) ? 0 : visibility.hashCode());
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
		MethodModel other = (MethodModel) obj;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (commentTextReturn == null) {
			if (other.commentTextReturn != null)
				return false;
		} else if (!commentTextReturn.equals(other.commentTextReturn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parametersModel == null) {
			if (other.parametersModel != null)
				return false;
		} else if (!parametersModel.equals(other.parametersModel))
			return false;
		if (returnType == null) {
			if (other.returnType != null)
				return false;
		} else if (!returnType.equals(other.returnType))
			return false;
		if (visibility == null) {
			if (other.visibility != null)
				return false;
		} else if (!visibility.equals(other.visibility))
			return false;
		return true;
	}
	
	public String getStringToPrint(){
		return name;
	}
	
	

}
