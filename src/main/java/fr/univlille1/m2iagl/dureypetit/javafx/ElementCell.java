package fr.univlille1.m2iagl.dureypetit.javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ElementCell extends Cell{

	TextCell textCell;
	
	public ElementCell(String cellId, Color color, String text) {
		super(cellId);
		

		Rectangle view = new Rectangle(text.length() * 8,20);
        view.setStroke(color);
        view.setFill(Color.WHITE);
        
        setView(view);
	}
	
	public void setTextCell(TextCell textCell){
		this.textCell = textCell;
	}
	
	public TextCell getTextCell(){
		return textCell;
	}

}
