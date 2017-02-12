package fr.univlille1.m2iagl.dureypetit.javafx.layout;

import java.util.List;
import java.util.Random;

import fr.univlille1.m2iagl.dureypetit.javafx.cell.Cell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ElementCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.TextCell;
import fr.univlille1.m2iagl.dureypetit.javafx.interfaces.Graph;

public class OrderedLayout extends Layout {

	Graph graph;

	Random rnd = new Random();

	int nbClasses;
	int nbMethods;
	int nbParameters;

	public OrderedLayout(Graph graph) {

		this.graph = graph;
		nbClasses = 0;
		nbMethods = 0;
		nbParameters = 0;
	}

	public void execute() {

		List<Cell> cells = graph.getModel().getAllCells();

		for (Cell cell : cells) {
			double x;
			double y;
			if(!cell.isText()){
				ElementCell elementCell = (ElementCell) cell;
				if(elementCell.isClass()){
					nbClasses++;
					y = 100;
					x = 1200 / graph.getModel().nbClasses * nbClasses;
				} else if(elementCell.isMethod()){
					nbMethods++;
					y = 400 + 90 / 3 * (nbMethods % 3);
					x = 1200 / graph.getModel().nbMethods * nbMethods;
				} else if(elementCell.isParameter()){
					nbParameters++;
					y = 700 + 120 / 4 * (nbParameters % 4);
					x = 1200 / graph.getModel().nbParameters * nbParameters;
				} else {
					throw new RuntimeException("Unknown cell type");
				}
				TextCell textCell = elementCell.getTextCell();
				
				textCell.relocate(x + 52, y + 15);
				cell.relocate(x + 50, y);
			}

			

		}

	}

}
