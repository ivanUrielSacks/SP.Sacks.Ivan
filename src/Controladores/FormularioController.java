package Controladores;

import Excepciones.IdMisionDuplicadaException;
import Modelo.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class FormularioController {

    @FXML private TextField txtIdMision;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDestino;
    @FXML private TextField txtPresupuesto;
    @FXML private TextField txtDuracion;
    @FXML private ComboBox<Agencia> comboAgencia;
    @FXML private RadioButton rbTripulada;
    @FXML private RadioButton rbNoTripulada;
    @FXML private TextField txtAstronautas;
    @FXML private ComboBox<TipoDispositivo> comboDispositivo;
    @FXML private TableView<Mision> tablaMisiones;
    @FXML private TableColumn<Mision, Integer> colId;
    @FXML private TableColumn<Mision, String> colNombre;
    @FXML private TableColumn<Mision, String> colDestino;
    @FXML private TableColumn<Mision, Double> colPresupuesto;
    @FXML private TableColumn<Mision, Integer> colDuracion;
    @FXML private TableColumn<Mision, String> colAgencia;
    @FXML private TableColumn<Mision, String> colTipo;

    private GestorDatos gestor = new GestorDatos();
    private List<Agencia> agencias;
    private List<Mision> misiones;
    private ObservableList<Mision> obsMisiones;
    private ToggleGroup grupoTipo;

    @FXML
    public void initialize() {
        agencias = gestor.cargarAgencias();
        misiones = gestor.cargarMisiones();

        comboAgencia.getItems().setAll(agencias);
        comboDispositivo.getItems().setAll(TipoDispositivo.values());

        grupoTipo = new ToggleGroup();
        rbTripulada.setToggleGroup(grupoTipo);
        rbNoTripulada.setToggleGroup(grupoTipo);
        rbTripulada.setSelected(true);
        cambiarTipo();

        obsMisiones = FXCollections.observableArrayList(misiones);
        tablaMisiones.setItems(obsMisiones);

        colId.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getIdMision()).asObject());
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNombre()));
        colDestino.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDestino()));
        colPresupuesto.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getPresupuesto()).asObject());
        colDuracion.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getDuracionDias()).asObject());
        colAgencia.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getAgencia().getNombre()));
        colTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTipo()));
    }

    @FXML
    public void cambiarTipo() {
        boolean tripulada = rbTripulada.isSelected();
        txtAstronautas.setDisable(!tripulada);
        comboDispositivo.setDisable(tripulada);
    }

    @FXML
    public void agregarMision() {
        try {
            int id = Integer.parseInt(txtIdMision.getText().trim());
            for (Mision m : misiones) {
                if (m.getIdMision() == id) {
                    throw new IdMisionDuplicadaException("Ya existe una mision con el ID " + id);
                }
            }
            Agencia agencia = comboAgencia.getSelectionModel().getSelectedItem();
            if(agencia == null)
            {
                mostrarAlerta("Deve seleccionar agencia");
                return;
            }
            Mision nueva = construirMisionDesdeFormulario(id);
            misiones.add(nueva);
            obsMisiones.add(nueva);
            gestor.guardarMisiones(misiones);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Los campos numericos deben ser válidos");
        } catch (IdMisionDuplicadaException e) 
        {
            mostrarAlerta(e.getMessage());
        }
    }

    @FXML
    public void modificarMision() 
    {
        Mision seleccionada = tablaMisiones.getSelectionModel().getSelectedItem();
        if (seleccionada == null)
        {
            mostrarAlerta("Seleccione una misión para modificar");
            return;
        }
        try 
        {
            int id = seleccionada.getIdMision(); 
            Mision actualizada = construirMisionDesdeFormulario(id);
            
            int indice = misiones.indexOf(seleccionada);
            misiones.set(indice, actualizada);
            obsMisiones.set(indice, actualizada);
            gestor.guardarMisiones(misiones);
            limpiarCampos();
            
        } 
        catch (NumberFormatException e) 
        {
            mostrarAlerta("Los campos numéricos deben ser válidos");
        }
    }

    @FXML
    public void eliminarMision() 
    {
        Mision seleccionada = tablaMisiones.getSelectionModel().getSelectedItem();
        if (seleccionada == null)
        {
            mostrarAlerta("Seleccione una mision para eliminar");
            return;
        }
        misiones.remove(seleccionada);
        obsMisiones.remove(seleccionada);
        gestor.guardarMisiones(misiones);
    }

    @FXML
    public void limpiarCampos() {
        txtIdMision.clear();
        txtNombre.clear();
        txtDestino.clear();
        txtPresupuesto.clear();
        txtDuracion.clear();
        txtAstronautas.clear();
        comboAgencia.getSelectionModel().clearSelection();
        comboDispositivo.getSelectionModel().clearSelection();
        rbTripulada.setSelected(true);
        cambiarTipo();
    }

    private Mision construirMisionDesdeFormulario(int id)
    {
        String nombre = txtNombre.getText().trim();
        String destino = txtDestino.getText().trim();
        double presupuesto = Double.parseDouble(txtPresupuesto.getText().trim());
        int duracion = Integer.parseInt(txtDuracion.getText().trim());
        Agencia agencia = comboAgencia.getSelectionModel().getSelectedItem();

        if (rbTripulada.isSelected())
        {
            int astronautas = Integer.parseInt(txtAstronautas.getText().trim());
            return new MisionTripulada(id, nombre, destino, presupuesto, duracion, agencia, astronautas);
        } 
        else 
        {
            TipoDispositivo dispositivo = comboDispositivo.getSelectionModel().getSelectedItem();
            return new MisionNoTripulada(id, nombre, destino, presupuesto, duracion, agencia, dispositivo);
        }
    }

    @FXML
    public void verAltoPresupuesto() {
        gestor.exportarAltoPresupuesto(misiones);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/AltoPresupuesto.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Misiones de Alto Presupuesto");
            stage.setScene(new Scene(root, 500, 400));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta("Error al abrir ventana: " + e.getMessage());
        }
    }

    @FXML
    public void verPresupuestoTotal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/PresupuestoTotal.fxml"));
            Parent root = loader.load();
            PresupuestoTotalController controller = loader.getController();
            controller.setPresupuestoTotal(calcularPresupuestoTotal());
            Stage stage = new Stage();
            stage.setTitle("Presupuesto Total");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (Exception e) {
            mostrarAlerta("Error al abrir ventana: " + e.getMessage());
        }
    }

    private double calcularPresupuestoTotal() {
        double total = 0;
        for (Mision m : misiones) {
            total += m.getPresupuesto();
        }
        return total;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}