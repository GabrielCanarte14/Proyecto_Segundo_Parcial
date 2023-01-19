/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_grupo2;

import Class.Directorio;
import TDAS.Tree;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class FiltroController implements Initializable {

    @FXML
    private TableView<Directorio> tblFiltros;
    @FXML
    private TableColumn<Directorio, String> colNombre;
    @FXML
    private TableColumn<Directorio, Integer> colPeso;
    @FXML
    private TableColumn<Directorio, String> colRuta;
    @FXML
    private ComboBox<String> cbFiltros;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCampos();
    }    
    
    @FXML
    private void ordenarTabla(ActionEvent event) {
    }
    
    private void llenarCampos() {
        cbFiltros.getItems().add("Más pesados");
        cbFiltros.getItems().add("Menos pesados");
        cbFiltros.getItems().add("Alfabetico");
    }
    
    public void cargarTabla(Tree<Directorio> arbol) throws Exception{
        ArrayList<Directorio> archivos = arbol.recorrer();
        for(Directorio d: archivos){
            System.out.println(d);
        }
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("tamanio"));
        colRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        for(Directorio d: archivos) {             
            tblFiltros.getItems().add(d);
        }
    }
}
