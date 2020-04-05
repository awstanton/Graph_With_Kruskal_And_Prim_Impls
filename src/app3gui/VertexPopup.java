
package app3gui;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class VertexPopup {
    static Pair<String, Integer> ret;
    
    public static Pair<String, Integer> vertexPopup() {
        Stage vrtxStage = new Stage();
        vrtxStage.initModality(Modality.APPLICATION_MODAL);
        GridPane root = new GridPane();
        
        Label labelLabel = new Label("Vertex Label");
        Label weightLabel = new Label("Vertex Weight");
        
        TextField labelField = new TextField("");
        
        labelField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (labelField.getLength() > 5)
                labelField.setText(labelField.getText(0, 5));
        });
        
        TextField weightField = new TextField("");
        Button submit = new Button("Submit");
        
        submit.setOnAction((ActionEvent ae) -> {
            //System.out.println("root.getChildren().size() = " + root.getChildren().size());
            if (root.getChildren().size() == 7)
                root.getChildren().remove(root.getChildren().size() - 1);
            if (root.getChildren().size() == 6)
                root.getChildren().remove(root.getChildren().size() - 1);
            
            try {
                if (labelField.getLength() == 0) {
                    //System.out.println("labelField.getLength() == 0");
                    Label errorLabel1 = new Label("Label must have length greater than 0");
                    root.addRow(root.getChildren().size(), errorLabel1);
                    String label = labelField.getText();
                    Integer weight = Integer.valueOf(weightField.getText());
                }
                else {
                    String label = labelField.getText();
                    Integer weight = Integer.valueOf(weightField.getText());
                    ret = new Pair<>(label, weight);
                    vrtxStage.close();
                }
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
                //System.out.println("root.getChildren().size() == " + root.getChildren().size());
                Label errorLabel2 = new Label("Weight must be a valid integer");
                root.addRow(root.getChildren().size(), errorLabel2);
            }
            labelField.setText("");
            weightField.setText("");
        });
        root.addRow(0, labelField, labelLabel);
        root.addRow(1, weightField, weightLabel);
        root.addRow(2, submit);
        
        
        root.getChildren().addAll();
        
        Scene vrtxScene = new Scene(root, 350, 200);
        vrtxStage.setTitle("Choose a Label and a Weight");
        vrtxStage.setScene(vrtxScene);
        vrtxStage.showAndWait();
        return ret;
    }
}
