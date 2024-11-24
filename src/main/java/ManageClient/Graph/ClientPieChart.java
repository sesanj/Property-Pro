//package ManageClient.Graph;
//
//import javafx.scene.layout.BorderPane;
//
//package ManageClient.Graph;
//
//import TableQuery.ClientTable;
//import TableQuery.PropertyTypeTable;
//import TableQuery.TransactionTable;
//import com.example.propertypro.Pojo.ClientPOJO;
//import com.example.propertypro.Pojo.PropertyTypePOJO;
//import com.example.propertypro.Pojo.TransactionPOJO;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.chart.PieChart;
//import javafx.scene.layout.BorderPane;
//
//import java.util.ArrayList;
//
//public class ClientPieChart extends BorderPane {}
//    private final PieChart pieChart;
//
//    public ClientPieChart() {
//        pieChart = new PieChart();
//        generatePieChart();
//        this.setCenter(pieChart);
//    }
//
//    public void generatePieChart() {
//
//        PropertyTypeTable propertyTypeTable = new PropertyTypeTable();
//        ClientTable clientTable = new ClientTable();
//        TransactionTable transactionTable = new TransactionTable();
//
//        ArrayList<PropertyTypePOJO> allPropertyTypes = propertyTypeTable.getAllPropertyTypes();
//        ArrayList<ClientPOJO> allClients = clientTable.getAllClient();
//        ArrayList<TransactionPOJO> allTransactions = transactionTable.getAllTransactions2();  // Get all transactions
//
//        ArrayList<PieChart.Data> data = new ArrayList<>();
//
//
//        for (ClientPOJO client : allClients) {
//
//            ArrayList<String> clientPropertyTypes = new ArrayList<>();
//
//
//            for (TransactionPOJO transaction : allTransactions) {
//                if (transaction.getClient_id() == client.getClient_id()) {
//
//                    int propertyID = transaction.getProperty_id();
//
//
//                    int propertyTypeID = getPropertyTypeIDFromProperty(propertyID, allPropertyTypes);
//
//
//                    String propertyTypeName = getPropertyTypeName(propertyTypeID, propertyTypeTable);
//
//
//                    if (!clientPropertyTypes.contains(propertyTypeName)) {
//                        clientPropertyTypes.add(propertyTypeName);
//                    }
//                }
//            }
//
//
//            int distinctPropertyCount = clientPropertyTypes.size();
//
//
//            if (distinctPropertyCount > 0) {
//                data.add(new PieChart.Data(client.getFirst_name() + " (" + distinctPropertyCount + " types)", distinctPropertyCount));
//            }
//        }
//
//
//        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
//
//
//        pieChart.setData(pieChartData);
//        pieChart.setLabelsVisible(true);
//        pieChart.setAnimated(true);
//    }
//
//
//    private int getPropertyTypeIDFromProperty(int propertyID, ArrayList<PropertyTypePOJO> allPropertyTypes) {
//        for (PropertyTypePOJO propertyType : allPropertyTypes) {
//            if (propertyType.getPropertyType_id() == propertyID) {
//                return propertyType.getPropertyType_id();
//            }
//        }
//        return -1;
//    }
//
//
//    private String getPropertyTypeName(int propertyTypeID, PropertyTypeTable propertyTypeTable) {
//        PropertyTypePOJO propertyType = propertyTypeTable.getPropertyType(propertyTypeID);
//        return propertyType != null ? propertyType.getProperty_type() : "Unknown";
//    }
//}
//
