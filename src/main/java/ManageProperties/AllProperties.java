package ManageProperties;

import Overview.TopClients;
import TableQuery.PropertyTable;
import com.example.propertypro.Pojo.PropertyPOJO;
import com.example.propertypro.Pojo.PropertyPOJORefined;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class AllProperties extends BorderPane {


    public AllProperties() {

        PropertyTable propertyTable = new PropertyTable();

        TableView allProperties = new TableView();
        allProperties.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<PropertyPOJORefined, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getName()));

        TableColumn<PropertyPOJORefined, String> type = new TableColumn<>("Type");
        type.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProperty_type()));

        TableColumn<PropertyPOJORefined, String> province = new TableColumn<>("Province");
        province.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getProvince()));

        TableColumn<PropertyPOJORefined, String> city = new TableColumn<>("City");
        city.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCity()));

        TableColumn<PropertyPOJORefined, String> street = new TableColumn<>("Street");
        street.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getStreet()));

        TableColumn<PropertyPOJORefined, String> postalCode = new TableColumn<>("Postal Code");
        postalCode.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getPostal_code()));

        TableColumn<PropertyPOJORefined, String> availability = new TableColumn<>("Availability");
        availability.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getAvailability() + ""));

        allProperties.getStylesheets().add(getClass().getResource("/tableView.css").toExternalForm());

        allProperties.getColumns().addAll(name, type, province, city, street, postalCode, availability);
        allProperties.getItems().addAll(propertyTable.getAllProperty());

        this.setCenter(allProperties);



    }

}
