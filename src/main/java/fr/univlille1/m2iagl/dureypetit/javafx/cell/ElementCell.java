package fr.univlille1.m2iagl.dureypetit.javafx.cell;

import fr.univlille1.m2iagl.dureypetit.model.ElementModel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ElementCell extends Cell{

	private TextCell textCell;
	
	private ElementModel elementModel;
	
	public ElementCell(String cellId, Color color, String text, ElementModel elementModel) {
		super(cellId);
		
		this.elementModel = elementModel;

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
	
	public ElementModel getElementModel(){
		return elementModel;
	}
	
	

}
