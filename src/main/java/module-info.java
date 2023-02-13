module com.villoh.api_rest_videojuegos {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.base;
    requires javafx.swing;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    
    opens com.villoh.api_rest_videojuegos to javafx.fxml;
    exports com.villoh.api_rest_videojuegos;
    opens com.villoh.api_rest_videojuegos.controladores to javafx.fxml;
}
