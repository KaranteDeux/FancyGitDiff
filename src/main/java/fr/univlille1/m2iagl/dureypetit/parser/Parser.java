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

		ClassModel classModel = new ClassModel(classOrInterfaceDeclaration.getNameAsString(), classOrInterfaceDeclaration.getComment().getContent());

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
			fieldsModel.add(new FieldModel(variableDeclarator.getNameAsString(), variableDeclarator.getType().toString(), variableDeclarator.getComment().getContent()));
		}

		return fieldsModel;
	}

	public static MethodModel parseMethod(MethodDeclaration methodDeclaration){

		MethodModel methodModel = new MethodModel(methodDeclaration.getModifiers().toString(), methodDeclaration.getType().toString(), methodDeclaration.getNameAsString(), methodDeclaration.getComment().toString());

		for(Parameter parameter : methodDeclaration.getParameters()){
			methodModel.addParameterModel(parseParameter(parameter));
		}
		return methodModel;
	}

}
