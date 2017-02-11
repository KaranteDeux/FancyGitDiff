package fr.univlille1.m2iagl.dureypetit.javafx;

import javafx.scene.paint.Color;

public class ClassCell extends ElementCell {
	
	private static Color STROKE_COLOR = Color.DODGERBLUE;

    public ClassCell( String id, String text) {
        super(id, STROKE_COLOR, text);
        
    }

	@Override
	public boolean isClass() {
		return true;
	}
}

