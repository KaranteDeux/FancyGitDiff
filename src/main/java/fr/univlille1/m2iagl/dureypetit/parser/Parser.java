package fr.univlille1.m2iagl.dureypetit.parser;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;

import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.FieldModel;
import fr.univlille1.m2iagl.dureypetit.model.MethodModel;
import fr.univlille1.m2iagl.dureypetit.model.ParameterModel;

public class Parser {

	public static ClassModel parseClass(ClassOrInterfaceDeclaration classOrInterfaceDeclaration){

		String comment = "";
		if(classOrInterfaceDeclaration.getComment() != null)
			comment = classOrInterfaceDeclaration.getComment().getContent();
		
		ClassModel classModel = new ClassModel(classOrInterfaceDeclaration.getNameAsString(), comment);

		List<FieldDeclaration> fieldsDeclaration = classOrInterfaceDeclaration.getFields();

		for(FieldDeclaration fieldDeclaration : fieldsDeclaration){
			classModel.addAllFieldsModel(Parser.parseField(fieldDeclaration));
		}

		List<MethodDeclaration> methodsDeclaration = classOrInterfaceDeclaration.getMethods();

		for(MethodDeclaration methodDeclaration : methodsDeclaration){
			classModel.addMethodModel(Parser.parseMethod(methodDeclaration));
		}

		return classModel;
	}

	public static ParameterModel parseParameter(Parameter parameter){
		return new ParameterModel(parameter.getType().toString(), parameter.getNameAsString(), "");
	}

	public static List<FieldModel> parseField(FieldDeclaration fieldDeclaration){
		List<FieldModel> fieldsModel = new ArrayList<FieldModel>();
		for(VariableDeclarator variableDeclarator : fieldDeclaration.getVariables()){
			
			String comment = "";
			if(variableDeclarator.getComment() != null)
				comment = variableDeclarator.getComment().getContent();
			
			
			fieldsModel.add(new FieldModel(variableDeclarator.getNameAsString(), variableDeclarator.getType().toString(), comment));
		}

		return fieldsModel;
	}

	public static MethodModel parseMethod(MethodDeclaration methodDeclaration){

		String comment = "";
		if(methodDeclaration.getComment() != null)
			comment = methodDeclaration.getComment().getContent();
		
		MethodModel methodModel = new MethodModel(methodDeclaration.getModifiers().toString(), methodDeclaration.getType().toString(), methodDeclaration.getNameAsString(), comment);

		for(Parameter parameter : methodDeclaration.getParameters()){
			methodModel.addParameterModel(parseParameter(parameter));
		}
		return methodModel;
	}

}
