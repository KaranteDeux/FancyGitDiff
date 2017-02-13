package fr.univlille1.m2iagl.dureypetit.javafx.cell;

import fr.univlille1.m2iagl.dureypetit.model.ParameterModel;
import javafx.scene.paint.Color;

public class ParameterCell extends ElementCell{

	private static final Color STROKE_COLOR = Color.GREEN;
	
	public ParameterCell( String id, String text, ParameterModel parameterModel) {
		super(id, STROKE_COLOR, text, parameterModel);
	}

	public boolean isParameter() {
		return true;
	}
	
}
