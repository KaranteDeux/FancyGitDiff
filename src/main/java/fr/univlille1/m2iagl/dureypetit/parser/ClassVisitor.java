package fr.univlille1.m2iagl.dureypetit.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;

public class ClassVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Void arg) {
    	
    	GitRepository.classModel = Parser.parseClass(classOrInterfaceDeclaration);
    }
}