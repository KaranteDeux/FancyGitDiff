package fr.univlille1.m2iagl.dureypetit.model;

public class PackageModel extends AbstractElementModel{
	
	
	String name;
	
	public PackageModel(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getCommentText() {
		return "";
	}
	
	
	

}
