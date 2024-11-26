package ManageClient;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ClientTransactions extends BorderPane {
    public ClientTransactions(){
        Text title = new Text("Client Transactions");


        this.setCenter(title);
    }
}
