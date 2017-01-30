package fr.univlille1.m2iagl.dureypetit.model;

public class ParameterModel extends AbstractElementModel{
	
	String name;
	String type;
	
	String commentText;
	
	public ParameterModel(String type, String name, String commentText){
		this.name = name;
		this.type = type;
		this.commentText = commentText;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setCommentText(String commentText){
		this.commentText = commentText;
	}
	
	public String getCommentText(){
		return commentText;
	}

	@Override
	public String toString() {
		return "ParameterModel [name=" + name + ", type=" + type
				+ ", commentText=" + commentText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((commentText == null) ? 0 : commentText.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ParameterModel other = (ParameterModel) obj;
		if (commentText == null) {
			if (other.commentText != null)
				return false;
		} else if (!commentText.equals(other.commentText))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
