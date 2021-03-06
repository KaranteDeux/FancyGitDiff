package fr.univlille1.m2iagl.dureypetit.javafx.interfaces;

import fr.univlille1.m2iagl.dureypetit.javafx.cell.Cell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.ElementCell;
import fr.univlille1.m2iagl.dureypetit.javafx.cell.TextCell;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MousesGestures {

    final private DragContext dragContext = new DragContext();

    private Graph graph;
    private ListCommitView listCommitView;

    public MousesGestures(ListCommitView listCommitView, Graph graph) {
        this.graph = graph;
        this.listCommitView = listCommitView;
    }

    public void makeDraggable( final Node node) {
        node.setOnMouseEntered(onMouseEnteredEventHandler);
        node.setOnMouseExited(onMouseExitedEventHandler);
    }

    EventHandler<MouseEvent> onMouseEnteredEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            Cell cell = (Cell) event.getSource();
            
            ElementCell elementCell = null;
            if(!cell.isText()){
            	return;
            }
            
            TextCell textCell = (TextCell) cell;
        	elementCell = textCell.getElementCell();
            
            listCommitView.selectCommitWhichChangedElement(elementCell);
        }
    };

    EventHandler<MouseEvent> onMouseExitedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
        	listCommitView.getSelectionModel().clearSelection();
        }
    };

    class DragContext {

        double x;
        double y;

    }
}