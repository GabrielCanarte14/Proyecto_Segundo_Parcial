/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.grupo_02;

import TDAS.NodeTree;
import TDAS.Tree;
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
    private Tree<File> arbolPrincipal;
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void generarDatos(ActionEvent event) {
        if (directorio != null) {
            arbolPrincipal = new Tree<>(directorio); 
            construirArbol(arbolPrincipal, directorio);
            System.out.println(arbolPrincipal.cantidad());
            arbolPrincipal.recorrer();
//            for (File f : fileList) {
//                if (f.isDirectory()) {
//                    System.out.println(f + "Sus archivos son");
//                    File[] filess = f.listFiles();
//                    for (File d : filess) {
//                        System.out.println(d);
//                        Long tama = d.length();
//                        System.out.println(tama);
//                    }
//                } else {
//                    System.out.println(f);
//                    Long tama = f.length();
//                    System.out.println(tama);
//                }
//
//            }
        }
    }

    public static void construirArbol(Tree<File> arbol, File directorio) {
        File[] fileList = directorio.listFiles();
        if (fileList != null) {
            for (File f : fileList) {
                Tree<File> tmp = new Tree<>(f);
                if (f.isFile() && !f.getPath().contains("ini")) {
                    arbol.getRoot().getChildren().add(tmp);
                } else if (f.isDirectory()) {
                    construirArbol(tmp, f);
                    arbol.getRoot().getChildren().add(tmp);
                }
            }
        }
    }
}
