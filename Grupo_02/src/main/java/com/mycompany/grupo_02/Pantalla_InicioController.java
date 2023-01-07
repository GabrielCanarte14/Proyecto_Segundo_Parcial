/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.grupo_02;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class Pantalla_InicioController implements Initializable {

    @FXML
    private Button button;

    public static File directorio;
    private ArrayList<File> archivos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        archivos = new ArrayList<>();
    }    

    @FXML
    private void action(ActionEvent event) {
        if(directorio != null) {
            File[] fileList = directorio.listFiles();
            for(File f: fileList) {
                System.out.println(f);
                Long tama = f.length();
                System.out.println(tama);
            }
        }

    }
    
}
