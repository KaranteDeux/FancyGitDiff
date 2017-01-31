package fr.univlille1.m2iagl.dureypetit.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;

public class Start {
	
	public static void main(String [] args) throws FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileReader(args[0]));
		GitRepository gitRepo = new GitRepository(p.getProperty("JAVA_FOLDER"));
		
		gitRepo.constructModelForEachCommit();
	}
}
