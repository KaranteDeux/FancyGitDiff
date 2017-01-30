package fr.univlille1.m2iagl.dureypetit.doclet;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.Model;

public class MyDoclet {
	public static boolean start(RootDoc root) {
		
		Model.currentModel = new Model();
		
		ClassDoc[] classes = root.classes();
		
		long timeBefore = System.currentTimeMillis();
		
		System.out.println("Length : " + classes.length);
		
		for (int i = 0; i < classes.length; ++i) {
			ClassDoc classDoc = classes[i];
			
			ClassModel classModel = DocletParser.parseClass(classDoc);
			
			Model.currentModel.addClassModel(classModel);
		}
		
		System.out.println("Time : " + (System.currentTimeMillis() -  timeBefore) +  " ms");
		System.out.println("Model : " + Model.currentModel);

		return true;
	}
}