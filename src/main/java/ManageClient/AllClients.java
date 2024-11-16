package ManageClient;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class AllClients extends BorderPane {
    public AllClients(){
        Text title = new Text("All Clients");


        this.setCenter(title);
    }
}
