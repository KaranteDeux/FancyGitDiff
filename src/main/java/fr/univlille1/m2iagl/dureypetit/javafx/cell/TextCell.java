package fr.univlille1.m2iagl.dureypetit.javafx.cell;

import javafx.scene.text.Text;

public class TextCell extends Cell {

	ElementCell associated;
	String text;
	
	
	public TextCell(ElementCell associated, String id, String text) {
		super(id);
		this.associated = associated;
		this.text = text;
		Text view = new Text(text);
		setView(view);
	}
	
	public ElementCell getElementCell(){
		return associated;
	}
	
	@Override
	public boolean isText(){
		return true;
	}
	
	public String getText(){
		return text;
	}
}
