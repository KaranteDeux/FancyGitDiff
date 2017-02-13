package fr.univlille1.m2iagl.dureypetit.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.lib.ObjectId;

public class CommitModel {

	private ObjectId id;
	private String message;
	private String author;
	private Date date;
	private List<ClassModel> classesChanged;
	private List<String> filesChanged;
	
	public CommitModel(ObjectId id, String author, Date date, String message, List<String> elements){
		this.id = id;
		this.message = message;
		this.author = author;
		this.date = date;
		
		this.filesChanged = elements;
		this.classesChanged = new ArrayList<>();
		
	}

	public ObjectId getId(){
		return id;
	}

	public List<String> getFilesChanged(){
		return filesChanged;
	}
	
	public List<ClassModel> getClassChanged(){
		return classesChanged;
	}

	public void addClassModel(ClassModel model){
		
		boolean contains = false;
		for(ClassModel classModel : classesChanged){
			if(classModel.getName().equals(model.getName())){
				contains = true;
			}
		}
		if(!contains){
			classesChanged.add(model);
		}
	}

	@Override
	public String toString() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("EEE d MMM yyyy");
		return author + " - \t" + format.format(date) + " : \t" + message;
	}


}
