package fr.univlille1.m2iagl.dureypetit.doclet;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.Model;

public class GetJavadocDoclet {
	public static boolean start(RootDoc root) {
		
		ClassDoc[] classes = root.classes();
		
		for (int i = 0; i < classes.length; ++i) {
			ClassDoc classDoc = classes[i];
			
			ClassModel classModel = DocletParser.parseClass(classDoc);
			Model.currentModel.addClassModel(classModel);

		}

		return true;
	}
}