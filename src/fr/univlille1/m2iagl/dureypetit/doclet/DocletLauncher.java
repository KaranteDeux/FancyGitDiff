package fr.univlille1.m2iagl.dureypetit.doclet;

import java.io.IOException;
import java.util.List;

import com.sun.tools.javadoc.Main;

import fr.univlille1.m2iagl.dureypetit.model.Constants;
import fr.univlille1.m2iagl.dureypetit.model.Model;

public class DocletLauncher {

	private String pathToCheck;


	public boolean start(String pathToCheck, Model model){
		Model.currentModel = model;
		this.pathToCheck = pathToCheck;
		if(!compile())
			return false;

		boolean execResult = execute();

		return execResult;
	}

	private boolean compile(){

		String command = "javac -classpath " + Constants.JAR_PATH + " " + Constants.DOCLET_SRC_FILE;

		ProcessBuilder processBuilder = new ProcessBuilder(command);
		try {
			processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		return true;
	}

	private boolean execute(){

		Main.execute(new String[]{"-docletpath", Constants.DOCLET_PATH_OPTION, "-doclet", Constants.DOCLET_OPTION, "-subpackages", "*"});
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
