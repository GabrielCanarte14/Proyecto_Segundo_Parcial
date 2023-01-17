/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_grupo2;

import Class.Directorio;
import TDAS.NodeTree;
import TDAS.Tree;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class PrincipalController implements Initializable {
    
    private File directorio;
    private Tree<Directorio> arbolPrincipal;
    
    
    @FXML
    private Button btnSeleccion;
    @FXML
    private TextField tfDirectorio;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPeso;
    @FXML
    private Button btbCrear;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLista;
    @FXML
    private Button btnCrear;
    @FXML
    private Pane paneTree;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //Se selecciona el directorio y se crea el arbol multicaminos
    @FXML
    private void seleccionarDirectorio(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Selector de directorio");
        File defaultDirectory = new File("C:\\Users\\gabri\\OneDrive\\Documentos\\Gabriel\\Universidad");
        chooser.setInitialDirectory(defaultDirectory);
        directorio = chooser.showDialog(null);
        if (directorio == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se ha seleccionado un directorio");
        } else {
            tfDirectorio.setText(directorio.getPath());
            Directorio principal = new Directorio(directorio.getName(), directorio.getPath());
            principal.llenarArchivos();
            principal.setTamanio(principal.calcularPeso(directorio));
            arbolPrincipal = new Tree<>(principal);
            if(directorio != null) {
               construirArbol(arbolPrincipal, directorio); 
            }
            
        }
    }

    //Alerta que se muestra si no se escoge un directorio
    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo); 
        alert.setTitle("Alerta Emergente");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void crear(ActionEvent event) {
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

    @FXML
    private void listar(ActionEvent event) {
    }
    

    //Metodo para crear el treemap
    @FXML
    private void crearTree(ActionEvent event) {
        if (arbolPrincipal != null) {
            limpiarLbls();
            paneTree.getChildren().clear();
            VBox container = new VBox();
            Pane SizeTotal = new Pane();

            HBox graphics = new HBox();
            graphics.setMaxWidth(1188);
            graphics.setMaxHeight(731);

            //Es el rectangulo mostrado como cabecera con el peso total del directorio
            Rectangle graphicSizeTotal = new Rectangle();
            graphicSizeTotal.setWidth(1188);
            graphicSizeTotal.setHeight(25);
            graphicSizeTotal.setFill(Color.CORAL);
            graphicSizeTotal.setStroke(Color.WHITE);

            Label extensionSize = new Label();
            System.out.println(arbolPrincipal);
            setLabelSize(extensionSize, arbolPrincipal.getRoot().getContent().calcularPeso(directorio));
            SizeTotal.getChildren().addAll(graphicSizeTotal, extensionSize);
            container.getChildren().addAll(SizeTotal, graphics);
            crearRecuadros(arbolPrincipal.getRoot().getContent(), graphics, 1188.0, 731.0, "h");
            paneTree.getChildren().addAll(container);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "No ha seleccionado un directorio");
        }
    }

    public void crearRecuadros(Directorio directory, Pane pane, double width, double height, String type){
        LinkedList<Directorio> archivos = directory.getArchivos();
        double size = directory.getTamanio();
        archivos.forEach((f) -> {
            if (!f.isDirectory() && type.equals("h")) {
                System.out.println(f);
                double fact1 = width * (f.getTamanio() / size);
                double fact2 = height;
                Rectangle shape = new Rectangle(fact1, fact2);       
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                VBox temp = new VBox();
                temp.getChildren().addAll(shape);
                pane.getChildren().add(temp);
                temp.setOnMouseClicked(evento -> {
                    llenarDatos(f);
                });
            }  else if (!f.isDirectory() && type.equals("v")) {
                System.out.println("2");
                double fact1 = width;
                double fact2 = height * (f.getTamanio() / size);
                Rectangle shape = new Rectangle(fact1, fact2);
                shape.setFill(getRandomColor());
                shape.setStrokeType(StrokeType.INSIDE);
                shape.setStroke(Color.WHITE);
                HBox temp = new HBox();
                temp.getChildren().addAll(shape);
                pane.getChildren().add(temp);
                temp.setOnMouseClicked(evento -> {
                    llenarDatos(f);
                });
            } else if (f.isDirectory() && type.equals("h")) {
                System.out.println("3");
                System.out.println(f.getArchivos());
                double size2 = f.getTamanio();
                VBox box = new VBox();
                box.setMaxWidth(width * (size2 / size));
                box.setMaxHeight(height);               
                crearRecuadros(f, box, box.getMaxWidth(), box.getMaxHeight(), "v");
                pane.getChildren().add(box);
            } else if (f.isDirectory() && type.equals("v")) {
                System.out.println("4");
                double size2 = f.getTamanio();
                HBox box = new HBox();
                box.setMaxWidth(width);
                box.setMaxHeight(height * (size2 / size));
                crearRecuadros(f, box, box.getMaxWidth(), box.getMaxHeight(), "h");
                pane.getChildren().add(box);               
            }
        });
                
    }
    
    public Color getRandomColor() {
        Random rd = new Random();
        float r = rd.nextFloat();
        float g = rd.nextFloat();
        float b = rd.nextFloat();
        Color randomColor = new Color(r, g, b, 1);
        return randomColor;
    }
    
    //Para que se muestre en un formato adecuado de tamanio
    public void setLabelSize(Label lb, long amount) {
        lb.setStyle("-fx-font-weight: bold; -fx-font-size: 15");
        if (amount < 1000*1000) {
            lb.setText("(" + amount/1000 + " KB" + ")");
        } else if (amount >= 1000*1000 && amount < (1000*1000*1000)) {
            double tmp = amount / (1000*1000);
            lb.setText("(" + tmp  + " MB" + ")");
        } else {
            double tmp = amount / (1000 * 1000 * 1000);
            lb.setText("(" + tmp + " GB" + ")");
        }
    }
    
    public static void construirArbol(Tree<Directorio> arbol, File directorio) {
        File[] fileList = directorio.listFiles();
        if (fileList != null) {
            for (File f : fileList) {  
                Tree<Directorio> tmp = new Tree<>();
                if (f.isFile() && !f.getPath().contains("ini")) {
                    Directorio direc = new Directorio(f.getName(), (int)f.length(), f.getPath());
                    tmp.setRoot(new NodeTree<>(direc));
                    arbol.getRoot().getChildren().add(tmp);
                } else if (f.isDirectory()) {
                    Directorio direc = new Directorio(f.getName(), f.getPath());
                    direc.llenarArchivos();
                    tmp.setRoot(new NodeTree<>(direc));
                    arbol.getRoot().getChildren().add(tmp);
                    construirArbol(tmp, f);             
                }
            }
        }
    }

    private void llenarDatos(Directorio f) {
        lblNombre.setText(f.getNombre());
        setLabelSize(lblPeso, f.getTamanio());
        
    }

    private void limpiarLbls() {
        lblNombre.setStyle("-fx-font-weight: bold; -fx-font-size: 15");
        lblNombre.setText("");
        lblPeso.setText("");
    }
    

}
