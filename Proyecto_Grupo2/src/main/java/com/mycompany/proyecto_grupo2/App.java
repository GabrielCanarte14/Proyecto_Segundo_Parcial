package com.mycompany.proyecto_grupo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"), 1400, 900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("TreeMap - Proyecto Estructuras de Datos");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

    public static void main(String[] args) {
        launch();
    }

}