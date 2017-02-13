package fr.univlille1.m2iagl.dureypetit.javafx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import fr.univlille1.m2iagl.dureypetit.git.GitRepository;
import fr.univlille1.m2iagl.dureypetit.javafx.interfaces.Graph;
import fr.univlille1.m2iagl.dureypetit.javafx.interfaces.ListCommitView;
import fr.univlille1.m2iagl.dureypetit.javafx.layout.Layout;
import fr.univlille1.m2iagl.dureypetit.javafx.layout.OrderedLayout;
import fr.univlille1.m2iagl.dureypetit.model.CommitModel;
import fr.univlille1.m2iagl.dureypetit.model.ConfigConstants;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    Graph graph;
    ListCommitView listCommitView;
    
    static GitRepository gitRepo;

    @Override
    public void start(Stage primaryStage) {
    	
        BorderPane root = new BorderPane();

        
      
        primaryStage.setTitle("FancyGitDiff");
        Scene scene = new Scene(root, 1920, 1060);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        listCommitView = new ListCommitView(gitRepo);
        
        graph = new Graph(listCommitView);
        
        listCommitView.setGraph(graph);
        
        StackPane leftPane = new StackPane(graph.getScrollPane());
        StackPane rightPane = new StackPane(listCommitView);
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.75);

        //Constrain max size of left component:
       // rightPane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.30));

        primaryStage.setScene(new Scene(new BorderPane(splitPane), 1920, 1060));

        primaryStage.show();

       // addGraphComponents();

        Layout layout = new OrderedLayout(graph);
        layout.execute();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	
    	Properties p = new Properties();
		p.load(new FileReader(args[0]));
		
		ConfigConstants.SHOW_PARAMETERS = Boolean.parseBoolean(p.getProperty("SHOW_PARAMETERS"));
		gitRepo = new GitRepository(p.getProperty("JAVA_FOLDER"));

		
        launch(args);
    }
}