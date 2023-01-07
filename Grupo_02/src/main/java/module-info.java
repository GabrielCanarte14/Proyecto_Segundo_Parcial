module com.mycompany.grupo_02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.grupo_02 to javafx.fxml;
    exports com.mycompany.grupo_02;
}
