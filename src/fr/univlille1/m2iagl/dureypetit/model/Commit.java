package fr.univlille1.m2iagl.dureypetit.model;

import java.util.Date;
import java.util.List;

public class Commit {
	
	private String id;
	private String message;
	private String author;
	private Date date;
	private List<String> elements;
	
	public Commit(String id, String author, Date date, String message, List<String> elements){
		this.id = id;
		this.message = message;
		this.author = author;
		this.date = date;
		this.elements = elements;
	}
	
	public String getId(){
		return id;
	}

	@Override
	public String toString() {
		
		return "Commit [id=" + id + ", message=" + message + ", author=" + author + ", date=" + date + ", elements="+ elements + "]";
	}
	
	
}
