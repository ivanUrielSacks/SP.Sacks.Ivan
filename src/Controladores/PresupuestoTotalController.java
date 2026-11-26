package Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PresupuestoTotalController {

    @FXML private Label lblTotal;

    public void setPresupuestoTotal(double total) {
        lblTotal.setText(String.valueOf(total));
    }

    @FXML
    public void cerrar() {
        Stage stage = (Stage) lblTotal.getScene().getWindow();
        stage.close();
    }
}