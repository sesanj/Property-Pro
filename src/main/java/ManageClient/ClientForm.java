package ManageClient;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ClientForm extends BorderPane {
    public ClientForm(){
        Text title = new Text("Client Form");


        this.setCenter(title);
    }
}
