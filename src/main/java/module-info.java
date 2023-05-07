module co.edu.uniquindio.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.proyectofinal to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal;
    exports co.edu.uniquindio.proyectofinal.controllers;
    opens co.edu.uniquindio.proyectofinal.controllers to javafx.fxml;
}