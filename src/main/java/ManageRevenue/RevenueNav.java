package ManageRevenue;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class RevenueNav extends HBox {
    public RevenueNav(){
        Text year = new Text("Year");
        Text month = new Text("Month");
        Text week = new Text("Week");

        this.getChildren().addAll(year, month, week);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
    }
}
