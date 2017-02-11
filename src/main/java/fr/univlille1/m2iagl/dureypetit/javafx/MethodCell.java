package fr.univlille1.m2iagl.dureypetit.javafx;

import javafx.scene.paint.Color;

public class MethodCell extends ElementCell {

	private static Color STROKE_COLOR = Color.RED;

	
	public MethodCell( String id, String text) {
		super(id, STROKE_COLOR, text);

	}

	public boolean isMethod() {
		return true;
	}
}
