package ManageProperties;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PropertyNav extends HBox {

    public PropertyNav(){

        Text province = new Text("Province");

        Text city = new Text("City");

        this.getChildren().addAll(province, city);
    }
}
