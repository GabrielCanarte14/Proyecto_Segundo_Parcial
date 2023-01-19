/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_grupo2;

import Class.Directorio;
import TDAS.Tree;
import java.net.URL;
import TDAS.ArrayList;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    
    Tree<Directorio> arbolPrincipal;
    
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
    @FXML
    private Button btnRegresar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCampos();
        try {
            cargarTabla(PrincipalController.arbolPrincipal);
        } catch (Exception ex) {
            Logger.getLogger(FiltroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void ordenarTabla(ActionEvent event) throws Exception {
        ArrayList<Directorio> archivos = arbolPrincipal.recorrer();
        String s = cbFiltros.getSelectionModel().getSelectedItem();
        if(s.equals("Más pesados")){
            tblFiltros.getItems().clear();
            ArrayList<Directorio> nuevo = ordenarPesoMas(archivos);
            cargarDirectorios(nuevo);
        } else if(s.equals("Menos pesados")) {
            tblFiltros.getItems().clear();
            ArrayList<Directorio> nuevo = ordenarPesoMenos(archivos);
            cargarDirectorios(nuevo);
        } else if(s.equals("Alfabetico")) {
            tblFiltros.getItems().clear();
            ArrayList<Directorio> nuevo = ordenarAlfabetico(archivos);
            cargarDirectorios(nuevo);
        }
    }
    
    private void cargarDirectorios(ArrayList<Directorio> nuevo) throws Exception {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("tamanio"));
        colRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        for(int n = 0; n < nuevo.size(); n++) {
            Directorio r = nuevo.get(n);
            tblFiltros.getItems().add(r);
        }
    }
    
    private ArrayList<Directorio> ordenarPesoMas(ArrayList<Directorio> dir) {
        ArrayList<Directorio> ordenada = new ArrayList<>();
  
        PriorityQueue<Directorio> cola = new PriorityQueue<>((d1, d2) -> {
            return d2.getTamanio() - d1.getTamanio();
        });
        
        for(Directorio direc: dir){
            cola.offer(direc);
        }
        while(!cola.isEmpty()) {
            ordenada.addLast(cola.poll());
        }
        return ordenada;
    }
    
    private ArrayList<Directorio> ordenarPesoMenos(ArrayList<Directorio> dir) {
        ArrayList<Directorio> ordenada = new ArrayList<>();
  
        PriorityQueue<Directorio> cola = new PriorityQueue<>((d1, d2) -> {
            return d1.getTamanio() - d2.getTamanio();
        });
        
        for(Directorio direc: dir){
            cola.offer(direc);
        }
        while(!cola.isEmpty()) {
            ordenada.addLast(cola.poll());
        }
        return ordenada;
    }
    
    private ArrayList<Directorio> ordenarAlfabetico(ArrayList<Directorio> dir) {
        ArrayList<Directorio> ordenada = new ArrayList<>();

        
        PriorityQueue<Directorio> cola = new PriorityQueue<>((d1, d2) -> {
            return d1.getNombre().compareTo(d2.getNombre());
        });
        for(Directorio direc: dir){
            cola.offer(direc);
        }
        while(!cola.isEmpty()) {
            ordenada.addLast(cola.poll());
        }
        return ordenada;
    }
    
    private void llenarCampos() {
        cbFiltros.getItems().add("Más pesados");
        cbFiltros.getItems().add("Menos pesados");
        cbFiltros.getItems().add("Alfabetico");
    }
    
    public void cargarTabla(Tree<Directorio> arbol) throws Exception{
        arbolPrincipal = arbol; 
        ArrayList<Directorio> archivos = arbolPrincipal.recorrer();
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

    @FXML
    private void regresar(ActionEvent event) throws IOException {
        App.setRoot("principal");
    }

    
}
