package fr.univlille1.m2iagl.dureypetit.javafx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphicModel {

    Cell graphParent;

    List<Cell> allCells;
    List<Cell> addedCells;
    List<Cell> removedCells;
    
    List<Edge> allEdges;
    List<Edge> addedEdges;
    List<Edge> removedEdges;
    
    int nbClasses;
    int nbMethods;
    int nbParameters;

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
    
    public void addCell(String id, CellType type, String text) {
        ElementCell elementCell = null;
        switch (type) {

        case CLASS:
            elementCell = new ClassCell(id, text);
            nbClasses++;
            break;

        case METHOD:
            elementCell = new MethodCell(id, text);
            nbMethods++;
            break;

        case PARAMETER:
            elementCell = new ParameterCell(id, text);
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