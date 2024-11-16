package ManageClient;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ClientData extends BorderPane {
    public ClientData(){
        Text title = new Text("Client Data");


        this.setCenter(title);
    }
}
