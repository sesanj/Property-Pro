package ManageRevenue;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class RevenueChart extends BorderPane {
    public RevenueChart(){
        Text title = new Text("Revenue Charts");


        this.setCenter(title);
    }
}
