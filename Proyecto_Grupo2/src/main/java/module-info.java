module com.mycompany.proyecto_grupo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;
    
    
    opens com.mycompany.proyecto_grupo2 to javafx.fxml;
    opens Class to javafx.base;
    exports com.mycompany.proyecto_grupo2;
}
