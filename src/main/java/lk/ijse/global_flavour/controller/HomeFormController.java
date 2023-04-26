package lk.ijse.global_flavour.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.model.CashierCustomerModel;
import lk.ijse.global_flavour.model.OrderDetailModel;
import lk.ijse.global_flavour.model.OrderModel;
import lk.ijse.global_flavour.util.TimeAndDateController;

import java.sql.SQLException;

public class HomeFormController {

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    private Label lblTotalCustomer;

    @FXML
    private Label lblTotalSales;

    @FXML
    private Label lblTotalEmployee;

    private void countTotalCust(){
        try {
            int count = CashierCustomerModel.getTotCustomers();
            lblTotalCustomer.setText(String.valueOf(count));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void countTotalEmp(){
        try {
            int count = CashierCustomerModel.getTotEmployee();
            lblTotalEmployee.setText(String.valueOf(count));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void countTotalSales(){
        try {
            int count = OrderModel.getTotalSales();
            lblTotalSales.setText(String.valueOf(count));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        countTotalCust();
        countTotalEmp();
        countTotalSales();

        setDataToPieChart();
        lineChart();
    }

    public void setDataToPieChart() {
        ObservableList<PieChart.Data> pieChartData = null;
        try {
            pieChartData = OrderDetailModel.getDataToPieChart();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        pieChart.setData(pieChartData);
    }

    void lineChart() {
        lineChart.setTitle("Income Chart 2023");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");


        try {
            XYChart.Series series = OrderModel.lineChartData();
            series.setName("Income Chart");
            lineChart.getData().add(series);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
