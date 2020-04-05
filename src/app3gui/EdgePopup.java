
package app3gui;

import java.util.Set;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class EdgePopup {
    static Pair<Pair<String, String>, Integer> ret;
    
    public static Pair<Pair<String, String>, Integer> edgePopup(Set<String> labels) {
        Stage edgeStage = new Stage();
        edgeStage.initModality(Modality.APPLICATION_MODAL);
        GridPane root = new GridPane();
        Label vertex1Label = new Label("Vertex1 Label: ");
        Label vertex2Label = new Label("Vertex2 Label: ");
        Label weightLabel = new Label("Edge Weight");
        
        ComboBox vertex1List = new ComboBox();
        ComboBox vertex2List = new ComboBox();
        
        for (String label : labels) {
            vertex1List.getItems().add(label);
            vertex2List.getItems().add(label);
        }
        
        TextField weightField = new TextField("");
        
        Button submit = new Button("Submit");
        
        submit.setOnAction((ActionEvent ae) -> {
            if (root.getChildren().size() == 9)
                    root.getChildren().remove(root.getChildren().size() - 1);
            if (root.getChildren().size() == 8)
                    root.getChildren().remove(root.getChildren().size() - 1);
            try {
                
                if (vertex1List.getValue() != null && vertex2List.getValue() != null) {
                    Integer weight = Integer.valueOf(weightField.getText());
                    ret = new Pair<>(new Pair<>((String) vertex1List.getValue(), (String) vertex2List.getValue()), weight);
                    edgeStage.close();
                }
                else {
                    Label errorLabel1 = new Label("Both vertices must be selected");
                    root.addRow(root.getChildren().size(), errorLabel1);
                    Integer weight = Integer.valueOf(weightField.getText());
                }
            }
            catch(NumberFormatException e) {
                Label errorLabel2 = new Label("Weight must be a valid integer");
                root.addRow(root.getChildren().size(), errorLabel2);
                weightField.setText("");
            }
        });
        
        root.addRow(0, vertex1Label, vertex1List);
        root.addRow(1, vertex2Label, vertex2List);
        root.addRow(2, weightField, weightLabel);
        root.addRow(3, submit);
        
        Scene vrtxScene = new Scene(root, 350, 200);
        edgeStage.setTitle("Choose a Label and a Weight");
        edgeStage.setScene(vrtxScene);
        edgeStage.showAndWait();
        
        return ret;
    }
}
