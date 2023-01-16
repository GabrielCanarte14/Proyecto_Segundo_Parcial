module com.mycompany.proyecto_grupo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyecto_grupo2 to javafx.fxml;
    exports com.mycompany.proyecto_grupo2;
}
