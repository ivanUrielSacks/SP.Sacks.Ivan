package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AltoPresupuestoController {

    @FXML private TextArea txtContenido;

    @FXML
    public void initialize() {
        GestorDatos gestor = new GestorDatos();
        txtContenido.setText(gestor.leerAltoPresupuesto());
    }

    @FXML
    public void cerrar() {
        Stage stage = (Stage) txtContenido.getScene().getWindow();
        stage.close();
    }
}