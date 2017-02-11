package fr.univlille1.m2iagl.dureypetit.javafx;

import javafx.scene.paint.Color;

public class ParameterCell extends ElementCell{

	private static Color STROKE_COLOR = Color.GREEN;
	
	public ParameterCell( String id, String text) {
		super(id, STROKE_COLOR, text);

	}

	public boolean isParameter() {
		return true;
	}

}
