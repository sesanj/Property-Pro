package ManageClient;

import Database.Database;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class ClientTransactions extends BorderPane {
    public ClientTransactions(){
        Text title = new Text("Client Transactions");
        Database db = Database.getNewDatabase();


        this.setCenter(title);
    }
}
