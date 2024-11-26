package ManageRevenue;

import Database.Database;
import TableQuery.TransactionTable;
import com.example.propertypro.Pojo.TransactionPOJORefined;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import static Database.DatabaseTableConstants.*;
import static Database.DatabaseTableConstants.TRANSACTION_TIMESTAMP;

public class RevenueData extends BorderPane {

    public static Text totalRevenueText;
    public static Text totalTransactions;
    public static Text worstText = new Text();
    public static Text bestText = new Text();
    public static Text bestRevenue = new Text();
    public static Text worstRevenue = new Text();
    public static Text percentage = new Text();
    public static Label bestLabel;
    public static Label worstLabel;
    public static double revenue;
    public static int transactionCount;

    private static Database db = Database.getNewDatabase();


    public RevenueData(){


        Button allTime = new Button("All Time Stats");
        allTime.getStylesheets().add(getClass().getResource("/buttons.css").toExternalForm());
        allTime.getStyleClass().add("tabButton");

        TransactionTable transactionTable = new TransactionTable();

        VBox allContents = new VBox(40);
        VBox revenueBox = new VBox(6);
        VBox transactionBox = new VBox(6);
        HBox titleBox = new HBox(6);
        HBox buttonBox = new HBox();

        VBox bestBox = new VBox(6);
        VBox worstBox = new VBox(6);
        VBox chartBox = new VBox(0);

        HBox bestAndWorstContainer = new HBox(30);

        Label revenueLabel = new Label("Total Revenue");

        Label transactionLabel = new Label("Total Transactions");

        bestLabel = new Label("Best Month");
        worstLabel = new Label("Worst Month");


        String style = "-fx-fill: #1a1b2e; -fx-font-size: 25px; -fx-font-weight: bold;";

        Text title = new Text("Revenue Statistics");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        worstText.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        bestText.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        worstRevenue.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: black;");
        bestRevenue.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: black;");
        percentage.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: green; -fx-font-family: Arial;");

        totalRevenueText = new Text();
        totalRevenueText.setStyle("-fx-font-size: 30px; -fx-fill: green; -fx-font-weight: bold;");

        totalTransactions = new Text();
        totalTransactions.setStyle(style);

        Text totalClients = new Text();
        totalClients.setStyle(style);

        Text totalProperties = new Text();
        totalProperties.setStyle(style);

        Image chart = new Image(getClass().getResourceAsStream("/chart.png"));
        ImageView chartImage = new ImageView(chart);
        chartImage.setFitWidth(50);
        chartImage.setFitHeight(50);


        allTime.setOnAction(e -> {

            double totalRevenue = 0;
            setTransactionCount(transactionTable.getAllTransactions().size());

            for(TransactionPOJORefined transaction : transactionTable.getAllTransactions()){
                totalRevenue += transaction.getAmount();
            }

            getRevenue(totalRevenue);

            String formattedTransactionCount = String.format("%,d", getTransactionCount());
            totalTransactions.setText(formattedTransactionCount + "+");

            bestLabel.setText("Best Year");
            worstLabel.setText("Worst Year");

            getYearlyTransactions();

        });


        chartBox.getChildren().addAll(chartImage, percentage);
        chartBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(chartBox, Priority.ALWAYS);

        bestBox.getChildren().addAll(bestLabel, bestText, bestRevenue);
        worstBox.getChildren().addAll(worstLabel, worstText, worstRevenue);


        bestAndWorstContainer.getChildren().addAll(bestBox, worstBox, chartBox);
        bestAndWorstContainer.setAlignment(Pos.CENTER_LEFT);


        revenueBox.getChildren().addAll(revenueLabel, totalRevenueText);
        transactionBox.getChildren().addAll(transactionLabel, totalTransactions);


        buttonBox.getChildren().add(allTime);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        titleBox.getChildren().addAll(title, buttonBox);
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        allContents.getChildren().addAll(titleBox, revenueBox, transactionBox, bestAndWorstContainer);
        allContents.setAlignment(Pos.CENTER_LEFT);
        allContents.setStyle("-fx-padding: 40px 50px 10px 50px");

        this.setTop(allContents);
    }

    public static void getRevenue(double totalRevenue){

        revenue = totalRevenue;
        String formattedRevenue = String.format("%,.2f", getTotalRevenue());

        totalRevenueText.setText("$" + formattedRevenue + RevenueFormatter(totalRevenue));
    }

    public static void getBestAndWorst(double highestRevenue, double lowestRevenue, String best, String worst){

        bestRevenue.setText("$" + String.format("%,.2f", highestRevenue) + RevenueFormatter(highestRevenue));
        worstRevenue.setText("$" + String.format("%,.2f", lowestRevenue) + RevenueFormatter(lowestRevenue));

        if(best.length() < 3){

            bestLabel.setText("Best Day");
            worstLabel.setText("Worst Day");

            bestText.setText(dayFormatter(best));
            worstText.setText(dayFormatter(worst));
        }
        else{
            bestText.setText(best);
            worstText.setText(worst);
            bestLabel.setText("Best Month");
            worstLabel.setText("Worst Month");
        }

        percentage.setText(String.format("%,.0f", getPercentage(highestRevenue, lowestRevenue)) + "% ");
    }

    public static double getPercentage(double highest, double lowest){
        double difference = 0;

        if (lowest != 0) {
            difference = ((highest - lowest) / lowest) * 100;
        } else {
            System.out.println("Cannot calculate percentage increase because the lowest revenue is zero.");
        }

        return difference;
    }

    public static String dayFormatter(String input){

        String day;

        int number = Integer.parseInt(input); // Convert to integer

        if (number % 100 >= 11 && number % 100 <= 13) {

            day = number + "th"; // Special case for 11th, 12th, and 13th
        }
        else {
            switch (number % 10) {
                case 1:
                    day = number + "st";
                    break;
                case 2:
                    day = number + "nd";
                    break;
                case 3:
                    day = number + "rd";
                    break;
                default:
                    day = number + "th";
                    break;
            }
        }

        return day;
    }

    public void getYearlyTransactions(){

        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<Double> amount = new ArrayList<>();

        String query = "SELECT EXTRACT(YEAR FROM " + TRANSACTION_TIMESTAMP + ") AS year, " +
                "SUM(" + TRANSACTION_AMOUNT + ") AS amount " +
                "FROM " + TRANSACTION_TABLE + " " +
                "GROUP BY year " +
                "ORDER BY YEAR(" + TRANSACTION_TIMESTAMP + ")";

        try{

            Statement statement = db.getConnection().createStatement();
            ResultSet data = statement.executeQuery(query);

            while(data.next()){
                years.add(data.getInt("year"));
                amount.add(data.getDouble("amount"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        double highestRevenue = Collections.max(amount);
        double lowestRevenue = Collections.min(amount);

        bestText.setText(years.get(amount.indexOf(highestRevenue)) + "");
        worstText.setText(years.get(amount.indexOf(lowestRevenue)) + "");

        bestRevenue.setText("$" + String.format("%,.2f", highestRevenue) + RevenueFormatter(highestRevenue));
        worstRevenue.setText("$" + String.format("%,.2f", lowestRevenue) + RevenueFormatter(lowestRevenue));

        percentage.setText(String.format("%,.0f", getPercentage(highestRevenue, lowestRevenue)) + "% ");
    }


    public static void updateRevenue(double amount){

        revenue = getTotalRevenue() - amount;

        String formattedRevenue = String.format("%,.2f", getTotalRevenue());

        totalRevenueText.setText("$" + formattedRevenue + RevenueFormatter(revenue));
    }

    public static void addRevenue(double amount){

        revenue = getTotalRevenue() + amount;

        String formattedRevenue = String.format("%,.2f", getTotalRevenue());

        totalRevenueText.setText("$" + formattedRevenue + RevenueFormatter(revenue));
    }


    public static String RevenueFormatter(Double revenue){

        String figure = "";

        if (revenue > 999999 && revenue < 1000000000){
            figure = "M+";
        } else if (revenue > 999 && revenue < 1000000) {
            figure = "K+";
        } else if (revenue > 999999999) {
            figure = "B+";
        }

        return figure;
    }

    public static void updateTotalTransaction(){

        totalTransactions.setText(String.format("%,d", getTransactionCount() - 1) + "+");

        setTransactionCount(getTransactionCount() - 1);
    }

    public static void addTransactionCount(){

        totalTransactions.setText(String.format("%,d", getTransactionCount() + 1) + "+");

        setTransactionCount(getTransactionCount() + 1);
    }

    public static double getTotalRevenue() {
        return revenue;
    }

    public static void setTotalTransactions(String totalTransactions, int count) {

        RevenueData.totalTransactions.setText(totalTransactions + "+");

        setTransactionCount(count);
    }

    public static int getTransactionCount() {
        return transactionCount;
    }

    public static void setTransactionCount(int transactionCount) {
        RevenueData.transactionCount = transactionCount;
    }
}