package ManageRevenue;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.security.PublicKey;

public class AllTransaction extends BorderPane {
    public AllTransaction(){
        Text title = new Text("All Revenue");

        this.setCenter(title);
    }
}
