package fr.univlille1.m2iagl.dureypetit.FancyGitDiff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;
import fr.univlille1.m2iagl.dureypetit.model.Commit;
import fr.univlille1.m2iagl.dureypetit.parser.ClassVisitor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
    	GitRepository gitRepo = new GitRepository("../CauseEffectChain/");
		
    	/*

		File file = new File(".git");
		File[] files = file.listFiles();
		System.out.println(Arrays.toString(files));
		
		*/

		
		gitRepo.constructModelForEachCommit();
    	
    /*	
    	  // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("src/main/java/fr/univlille1/m2iagl/dureypetit/model/ClassModel.java");

        // parse the file
        CompilationUnit cu = JavaParser.parse(in);

        // prints the resulting compilation unit to default system output
        new ClassVisitor().visit(cu, null);
        */
    }

    
}
