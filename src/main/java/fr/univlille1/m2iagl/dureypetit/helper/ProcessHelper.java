package fr.univlille1.m2iagl.dureypetit.helper;

import java.io.IOException;

public class ProcessHelper {

	
	public static void resetHard(String folderpath, String commitId) throws IOException{
		ProcessBuilder processBuilder = new ProcessBuilder("git reset --hard " + commitId);
		processBuilder.start();
	}
}
