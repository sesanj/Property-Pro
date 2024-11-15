package ManageProperties;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PropertyForm extends BorderPane {

    public PropertyForm(){

        Text title = new Text("Property Form");

        this.setCenter(title);
    }
}
