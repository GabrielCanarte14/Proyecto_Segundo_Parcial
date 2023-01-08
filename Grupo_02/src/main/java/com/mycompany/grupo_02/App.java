package com.mycompany.grupo_02;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       

        HBox root = new HBox();
        Scene scene = new Scene(root, 300, 300);

        Button btn = new Button("load");
        Button btn2 = new Button("Crear");
 
        btn.setOnAction(e -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Selector de directorio");
            File defaultDirectory = new File("C:\\Users\\gabri\\OneDrive\\Documentos\\Gabriel\\Universidad");
            chooser.setInitialDirectory(defaultDirectory);

            Pantalla_InicioController.directorio = chooser.showDialog(stage);
        });

        btn2.setOnAction(e -> {
            try {
                Scene scene2 = new Scene(loadFXML("Pantalla_Inicio"), 1400, 900);
                stage.setScene(scene2);
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        root.getChildren().addAll(btn, btn2);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
}