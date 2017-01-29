package fr.univlille1.m2iagl.dureypetit.model;

import java.io.FileReader;
import java.util.Properties;

public class Constants {
	
	public static String JAR_PATH;
	public static String DOCLET_SRC_FILE;
	public static String DOCLET_OPTION;

	public static String DOCLET_PATH_OPTION;
	
	public static String FOLDER_TO_CHECK;
	
	public static void init(String configFile){
		Properties properties = new Properties();

		try {
			properties.load(new FileReader(configFile));
		} catch(Exception e){
			System.exit(1);
		}
		
		JAR_PATH = properties.getProperty("JAR_PATH");
		DOCLET_SRC_FILE = properties.getProperty("DOCLET_SRC_FILE");
		DOCLET_OPTION = properties.getProperty("DOCLET_OPTION");
		DOCLET_PATH_OPTION = properties.getProperty("DOCLET_PATH_OPTION");
		
		FOLDER_TO_CHECK = properties.getProperty("FOLDER_TO_CHECK");


	}

}
