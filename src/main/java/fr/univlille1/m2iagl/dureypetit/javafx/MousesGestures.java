package fr.univlille1.m2iagl.dureypetit.javafx;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MousesGestures {

    final DragContext dragContext = new DragContext();

    Graph graph;

    public MousesGestures( Graph graph) {
        this.graph = graph;
    }

    public void makeDraggable( final Node node) {
        node.setOnMouseEntered(onMouseEnteredEventHandler);
    }

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

            Cell cell = (Cell) event.getSource();
            
            ElementCell elementCell = null;
            if(cell.isText()){
            	TextCell textCell = (TextCell) cell;
            	elementCell = textCell.getElementCell();
            } else {
            	elementCell = (ElementCell) cell;
            }
            
            
            
        }
    };

    EventHandler<MouseEvent> onMouseEnteredEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
/*
            Node node = (Node) event.getSource();

            double offsetX = event.getScreenX() + dragContext.x;
            double offsetY = event.getScreenY() + dragContext.y;

            // adjust the offset in case we are zoomed
            double scale = graph.getScale();

            offsetX /= scale;
            offsetY /= scale;

            node.relocate(offsetX, offsetY);
*/
        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {

        }
    };

    class DragContext {

        double x;
        double y;

    }
}