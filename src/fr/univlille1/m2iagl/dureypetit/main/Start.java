package fr.univlille1.m2iagl.dureypetit.main;

import fr.univlille1.m2iagl.dureypetit.doclet.DocletLauncher;
import fr.univlille1.m2iagl.dureypetit.model.Constants;
import fr.univlille1.m2iagl.dureypetit.model.Model;

public class Start {
	
	private static String masterBranch = "../Test/src";


	public static void main(String [] args){
		System.out.println("azerty");
		Constants.init(args[0]);

		DocletLauncher docletLauncher = new DocletLauncher();
		System.out.println("-----");
		
		docletLauncher.start(masterBranch, new Model());
		System.out.println("-----");

		System.out.println(Model.currentModel);
		
	}
}
