package fr.univlille1.m2iagl.dureypetit.javafx.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univlille1.m2iagl.dureypetit.javafx.cell.Cell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.CellType;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ClassCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ElementCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.MethodCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ParameterCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.TextCell;
import fr.univlille1.m2iagl.dureypetit.javafx.edge.Edge;
import fr.univlille1.m2iagl.dureypetit.model.ClassModel;
import fr.univlille1.m2iagl.dureypetit.model.ElementModel;
import fr.univlille1.m2iagl.dureypetit.model.MethodModel;
import fr.univlille1.m2iagl.dureypetit.model.ParameterModel;

public class GraphicModel {

    private Cell graphParent;

    private List<Cell> allCells;
    private List<Cell> addedCells;
    private List<Cell> removedCells;
    
    private List<Edge> allEdges;
    private List<Edge> addedEdges;
    private List<Edge> removedEdges;
    
    private int nbClasses;
    private int nbMethods;
    private int nbParameters;

    Map<String,Cell> cellMap; // <id,cell>
    
    public GraphicModel() {

         graphParent = new Cell( "_ROOT_");

         // clear model, create lists
         clear();
    }

    public void clear() {

        allCells = new ArrayList<>();
        addedCells = new ArrayList<>();
        removedCells = new ArrayList<>();

        allEdges = new ArrayList<>();
        addedEdges = new ArrayList<>();
        removedEdges = new ArrayList<>();

        cellMap = new HashMap<>(); // <id,cell>
        
        nbClasses = 0;
        nbMethods = 0;
        nbParameters = 0;
        
        graphParent.getCellChildren().clear();

    }

    public void clearAddedLists() {
        addedCells.clear();
        addedEdges.clear();
    }

    public List<Cell> getAddedCells() {
        return addedCells;
    }

    public List<Cell> getRemovedCells() {
        return removedCells;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public List<Edge> getAddedEdges() {
        return addedEdges;
    }

    public List<Edge> getRemovedEdges() {
        return removedEdges;
    }

    public List<Edge> getAllEdges() {
        return allEdges;
    }
    
    public int getNbClasses(){
    	return nbClasses;
    }
    
    public int getNbMethods(){
    	return nbMethods;
    }
    
    public int getNbParameters(){
    	return nbParameters;
    }
    
    public void addCell(String id, CellType type, String text, ElementModel elementModel) {
        ElementCell elementCell = null;
        switch (type) {

        case CLASS:
            elementCell = new ClassCell(id, text, (ClassModel) elementModel);
            nbClasses++;
            break;

        case METHOD:
            elementCell = new MethodCell(id, text, (MethodModel) elementModel);
            nbMethods++;
            break;

        case PARAMETER:
            elementCell = new ParameterCell(id, text, (ParameterModel) elementModel);
            nbParameters++;
            break;

        default:
            throw new UnsupportedOperationException("Unsupported type: " + type);
        }
        
        TextCell textCell = new TextCell(elementCell, id + "txt", text);
        elementCell.setTextCell(textCell);
        addCell(elementCell);
        addCell(textCell);

    }

    private void addCell(Cell cell) {

        addedCells.add(cell);
        
        cellMap.put( cell.getCellId(), cell);

    }

    public void addEdge(String sourceId, String targetId) {

        Cell sourceCell = cellMap.get( sourceId);
        Cell targetCell = cellMap.get( targetId);

        Edge edge = new Edge( sourceCell, targetCell);

        addedEdges.add( edge);
    }

    /**
     * Attach all cells which don't have a parent to graphParent 
     * @param cellList
     */
    public void attachOrphansToGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            if( cell.getCellParents().size() == 0) {
                graphParent.addCellChild( cell);
            }
        }

    }
    
    public void removeEverything(){
        removedCells.addAll(allCells);
        removedEdges.addAll(allEdges);
        
        nbClasses = 0;
        nbMethods = 0;
        nbParameters = 0;

    }

    /**
     * Remove the graphParent reference if it is set
     * @param cellList
     */
    public void disconnectFromGraphParent( List<Cell> cellList) {

        for( Cell cell: cellList) {
            graphParent.removeCellChild( cell);
        }
    }

    public void merge() {

        // cells
        allCells.addAll( addedCells);
        allCells.removeAll( removedCells);

        addedCells.clear();
        removedCells.clear();

        // edges
        allEdges.addAll( addedEdges);
        allEdges.removeAll( removedEdges);

        addedEdges.clear();
        removedEdges.clear();

    }
}