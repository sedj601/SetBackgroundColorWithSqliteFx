package com.mycompany.setbackgroundcolorwithsqlitefx;

import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {   
    @Override
    public void start(Stage stage) {
        Model model = new Model();//Use update properties as they change!
        Map<String, String> properties = model.getAllProperties();//Use to set initial properties only!
        
        CheckBox checkBox = new CheckBox("Set Full Screen");
        checkBox.setSelected(properties.get("FULL_SCREEN").equals("YES"));//Set initial CheckBox value.
        checkBox.selectedProperty().addListener((obs, oldBoolean, newBoolean) -> {
            if(newBoolean != null)
            {
                stage.setFullScreen(newBoolean);
            }
        });
        
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Utility.DecodeColor(properties.get("BACKGROUND_COLOR")));//Set initial ColorPicker color.
        
        VBox vBox = new VBox(checkBox, colorPicker);
        vBox.setAlignment(Pos.CENTER);
        
        StackPane root = new StackPane(vBox);       
        root.setBackground(new Background(new BackgroundFill(Utility.DecodeColor(properties.get("BACKGROUND_COLOR")), null, null)));//Set initial background color.
        
        Scene scene = new Scene(root, 640, 480);        
        
        //Listen to ColorPicker changes and update the database property.
        colorPicker.valueProperty().addListener((obs, oldColor, newColor) -> {            
            if(model.updateBackgroundColorProperty(newColor))
            {
                root.setBackground(new Background(new BackgroundFill(newColor, null, null)));
            }
        });
        
        stage.setFullScreen(properties.get("FULL_SCREEN").equals("YES"));//Set initial full screen mode.
        //Listen to full screen mode changes and update the database property.
        stage.fullScreenProperty().addListener((obs, oldBoolean, newBoolean) -> {
            if(newBoolean != null)
            {
                model.updateFullScreenProperty(newBoolean);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}