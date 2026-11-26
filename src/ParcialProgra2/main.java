
package ParcialProgra2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class main extends Application 
{
    @Override
    public void start(Stage stage)throws Exception
    {
        Label titulo = new Label("Hola mundo");
        StackPane layout = new StackPane(titulo);
        Scene scene = new Scene(layout, 200, 200);
        
        stage.setScene(scene);
        
        stage.show();
    }
 
    public static void main(String[] args)
    {
        Application.launch(args);
    }
    
}
