package fr.univlille1.m2iagl.dureypetit.javafx.cell;

import fr.univlille1.m2iagl.dureypetit.model.MethodModel;
import javafx.scene.paint.Color;

public class MethodCell extends ElementCell {

	private static final Color STROKE_COLOR = Color.RED;

	public MethodCell( String id, String text, MethodModel methodModel) {
		super(id, STROKE_COLOR, text, methodModel);
	}

	public boolean isMethod() {
		return true;
	}
	
}
