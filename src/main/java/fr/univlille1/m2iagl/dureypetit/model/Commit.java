package fr.univlille1.m2iagl.dureypetit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jgit.lib.ObjectId;

public class Commit {

	private ObjectId id;
	private String message;
	private String author;
	private Date date;
	private Map<String, List<ClassModel>> elementsChanged;

	public Commit(ObjectId id, String author, Date date, String message, List<String> elements){
		this.id = id;
		this.message = message;
		this.author = author;
		this.date = date;
		this.elementsChanged = new HashMap<>();
		for(String element : elements){
			elementsChanged.put(element, new ArrayList<>());
		}
	}

	public ObjectId getId(){
		return id;
	}
	
	public Set<String> getFilesChanged(){
		return elementsChanged.keySet();
	}
	
	public void addClassModel(String fileChanged, ClassModel model){
		elementsChanged.get(fileChanged).add(model);
	}

	@Override
	public String toString() {
		return "Commit [id=" + id + ", message=" + message + ", author=" + author + ", date=" + date + ", elements="+ elementsChanged + "]";
	}


}
