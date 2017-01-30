package fr.univlille1.m2iagl.dureypetit.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Void arg) {
    	System.out.println(classOrInterfaceDeclaration.getName());
    	
    	//Lecture de tout les PREMIERS fields (0)
    	for (FieldDeclaration field : classOrInterfaceDeclaration.getFields()) {
        	System.out.println("\tField : " + field.getVariable(0).getNameAsString());
        }
    	
    	//Lecture de toutes les methodes
    	for(MethodDeclaration methodDeclaration : classOrInterfaceDeclaration.getMethods()){
    		System.out.println("\tMethod : " + methodDeclaration.getName());
    		for (Parameter param : methodDeclaration.getParameters()) {
            	System.out.println("\t\tParameter : " + param.getNameAsString());
            }
    	}
    	
        super.visit(classOrInterfaceDeclaration, arg);
    }
}