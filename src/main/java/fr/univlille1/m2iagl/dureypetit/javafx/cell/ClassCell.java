package fr.univlille1.m2iagl.dureypetit.javafx.cell;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import javafx.scene.paint.Color;

public class ClassCell extends ElementCell {
	
	private static Color STROKE_COLOR = Color.DODGERBLUE;

    public ClassCell( String id, String text, ClassModel classModel) {
        super(id, STROKE_COLOR, text, classModel);
    }

	@Override
	public boolean isClass() {
		return true;
	}
	
}

