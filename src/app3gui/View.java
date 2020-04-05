
package app3gui;

import java.util.ArrayList;
import java.util.HashMap;
import javaapp3.AdjList;
import javaapp3.bfsIterator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


// apply transformations to the Canvas object. Applying a rotation to the Canvas object changes the Canvas object's coordinate system
// so that the canvas object is displayed rotated around a specified point of the Scene object it is attached to for a specified angle

public class View extends Application {
    
    private AdjList<Integer> graph;
    private HashMap<String, AdjList<Integer>.Vertex> labelToVertex;
    Canvas canvas;
    ArrayList<Pair<Double, Double>> coordinates;
    bfsIterator<Integer> edgeIterator;
    HBox root;
    VBox leftPanel;
    
    void addVertexToCanvas() {
//        System.out.println("adding vertex to canvas");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        
        
        final double vertexCount = graph.getVertexCount();
        double originX = canvas.getWidth() / 2.0 + leftPanel.getWidth();
        double originY = canvas.getHeight() / 2.0;
        double origCoordX = canvas.getWidth() / 4.0 + leftPanel.getWidth();
        double origCoordY = canvas.getHeight() / 4.0;
//        System.out.println("origCoordX = " + origCoordX);
//        System.out.println("origCoordY = " + origCoordY);
        
        gc.save();
        double currAngle = 0;
        final double angleIncrement = 360.0 / (double) vertexCount;
        
        for (int index = 0; index < vertexCount; ++index) {
            currAngle += angleIncrement;
//            System.out.println("currAngle = " + currAngle);
            Rotate r = new Rotate(currAngle, originX, originY);
            
            gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
            gc.fillOval(origCoordX, origCoordY, 10.0, 10.0);
            
            double coord1 = origCoordX * Math.cos(Math.toRadians(currAngle)) - origCoordY * Math.sin(Math.toRadians(currAngle)) + originX;
            double coord2 = origCoordX * Math.sin(Math.toRadians(currAngle)) + origCoordY * Math.cos(Math.toRadians(currAngle)) + originY;
            System.out.println("x coord = " + coord1);
            System.out.println("y coord = " + coord2);
            
            //coordinates.get(index).setPair(origCoordX * Math.cos(Math.toRadians(currAngle)) - origCoordY * Math.sin(Math.toRadians(currAngle)),
            //                origCoordX * Math.sin(Math.toRadians(currAngle)) + origCoordY * Math.cos(Math.toRadians(currAngle)));
            coordinates.get(index).setPair(coord1, coord2);
        }
        
        gc.restore();
        
        if (graph.getEdgeCount() > 0) {
            edgeIterator.reset();
            System.out.println("edgeIterator = " + edgeIterator);
            while (edgeIterator.getCurrent() != null) {
                int fromIndex = edgeIterator.getCurrFromVrtxIndex();
                int toIndex = edgeIterator.getCurrToVrtxIndex();
                Double x1 = coordinates.get(fromIndex).value1; // DO I NEED TO SUBTRACT LEFTPANEL.GETWIDTH() FROM THIS?
                Double y1 = coordinates.get(fromIndex).value2;
                Double x2 = coordinates.get(toIndex).value1; // DO I NEED TO SUBTRACT LEFTPANEL.GETWIDTH() FROM THIS?
                Double y2 = coordinates.get(toIndex).value2;
                System.out.println("x1 = " + x1 + ",y1 = " + y1 + ", x2 = " + x2 + ",y2 = " + y2);
                System.out.println("fromIndex = " + fromIndex);
                System.out.println("toIndex = " + toIndex);
                if (fromIndex >= toIndex) {
                    gc.strokeLine(x1, y1, x2, y2);
                }
                edgeIterator.getNext();
            }
        }
        
        /*
        iterate through the list of edges
        add edges whose second vertex is smaller, using pointers to look up the coordinates for the vertices
        for each edge that is determined to be needed to be drawn, draw it
        question: do I need to set the transform back to normal? If so, can I just do this with restore()?
        */
        // question: does AdjList add loops twice? It should only add loops once unless explicitly added more than once
    }
    void addEdgeToCanvas(AdjList<Integer>.Edge currEdge) {
        System.out.println("adding edge to canvas");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        edgeIterator.reset();

        while (edgeIterator.getCurrent() != currEdge) {
            System.out.println("message");
            edgeIterator.getNext();
        }
            
        
        int fromIndex = edgeIterator.getCurrFromVrtxIndex();
        int toIndex = edgeIterator.getCurrToVrtxIndex();
        Double x1 = coordinates.get(fromIndex).value1; // DO I NEED TO SUBTRACT LEFTPANEL.GETWIDTH() FROM THIS?
        Double y1 = coordinates.get(fromIndex).value2;
        Double x2 = coordinates.get(toIndex).value1; // DO I NEED TO SUBTRACT LEFTPANEL.GETWIDTH() FROM THIS?
        Double y2 = coordinates.get(toIndex).value2;
        gc.strokeLine(x1, y1, x2, y2);

    }
    
    
    @Override
    public void start(Stage primaryStage) {
        labelToVertex = new HashMap<>();
        graph = new AdjList<>();
        coordinates = new ArrayList<>(); // indices correspond with those of graph
        edgeIterator = new bfsIterator<>(graph);
        
        root = new HBox();
        root.setFillHeight(true);
        
        leftPanel = new VBox();
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setSpacing(10);
        leftPanel.setStyle("-fx-border-color: black; -fx-border-width: 0.05cm;");
        leftPanel.setMinWidth(100);
        
//        ColorAdjust c = new ColorAdjust();
//        c.setHue(0.14);
//        leftPanel.setEffect(c);
        
        Button vrtxBtn = new Button("Create Vertex");
        Button edgeBtn = new Button("Create Edge");
        
        vrtxBtn.setOnAction((ActionEvent event) -> {
            Pair<String, Integer> vertexData = VertexPopup.vertexPopup();
            //System.out.println("vertexData.value1 = " + vertexData.value1);
            //System.out.println("vertexData.value2 = " + vertexData.value2);
            if (!labelToVertex.containsKey(vertexData.value1)) {
                labelToVertex.put(vertexData.value1, graph.createVertex(vertexData.value2));
                coordinates.add(new Pair<>(0.0, 0.0));
                addVertexToCanvas();
            }
            edgeBtn.setDisable(false);
        });
        
        edgeBtn.setOnAction((ActionEvent event) -> {
            Pair<Pair<String, String>, Integer> edgeData = EdgePopup.edgePopup(labelToVertex.keySet());
            
            addEdgeToCanvas(graph.createEdge(labelToVertex.get(edgeData.value1.value1),
                                            labelToVertex.get(edgeData.value1.value2),
                                            edgeData.value2));
            // this creates the reverse edge, since the graph is undirected
            graph.createEdge(labelToVertex.get(edgeData.value1.value2),
                                            labelToVertex.get(edgeData.value1.value1),
                                            edgeData.value2);
        });
        edgeBtn.setDisable(true);
        
        leftPanel.getChildren().add(vrtxBtn);
        leftPanel.getChildren().add(edgeBtn);
        
        Scene scene = new Scene(root, 600, 450);
        
        canvas = new Canvas(root.getWidth() - leftPanel.getWidth(), root.getHeight());
        //HBox.setHgrow(canvas, Priority.ALWAYS);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //gc.fillOval(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, 10.0, 10.0);
        //gc.fillOval(0.0, 0.0, 10.0, 10.0);
        //graphicsContext.setFill(Color.GREEN);
        //graphicsContext.fillRect(50,50,50,50);
        /*
        gc.fillOval(canvas.getWidth() / 1.0 - 10.0, canvas.getHeight() - 10.0, 10.0, 10.0);
        gc.save();
        double angle = 45, x = 0, y = 0;
        Rotate rotate = new Rotate(angle, 0, 0);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
        
        gc.fillOval(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, 10.0, 10.0);
        gc.fillOval(canvas.getWidth() / 1.0 - 10.0, canvas.getHeight() / 1.0 - 10.0, 10.0, 10.0);
        gc.fillOval(0.0, 0.0, 10.0, 10.0);
        gc.restore();
        //Circle circle = new Circle(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, 5);
        
        rotate = new Rotate(angle + 12, 0, 0);
        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
gc.setFill(Color.GREEN);
        gc.fillOval(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, 10.0, 10.0);
        gc.fillOval(canvas.getWidth() / 1.0 - 10.0, canvas.getHeight() / 1.0 - 10.0, 10.0, 10.0);
        gc.fillOval(0.0, 0.0, 10.0, 10.0);
        */
        
        // angle in degrees * pi / 180
        root.getChildren().addAll(leftPanel, canvas);
        
        primaryStage.setTitle("Graph Creator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

//        gc.fillOval(canvas.getWidth() / 1.0 - 10.0, canvas.getHeight() - 10.0, 10.0, 10.0);
//        gc.save();
//        double angle = 45, x = 0, y = 0;
//        Rotate rotate = new Rotate(angle, 0, 0);
//        gc.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
//        
//        gc.fillOval(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0, 10.0, 10.0);
//        gc.fillOval(canvas.getWidth() / 1.0 - 10.0, canvas.getHeight() / 1.0 - 10.0, 10.0, 10.0);
//        gc.fillOval(0.0, 0.0, 10.0, 10.0);
//        gc.restore();
    