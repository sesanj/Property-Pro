package ManageClient;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ClientChart extends BorderPane {
    public ClientChart(){
        Text title = new Text("Client Chart");


        this.setCenter(title);
    }
}
