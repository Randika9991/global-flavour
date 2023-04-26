package lk.ijse.global_flavour.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.db.DBConnection;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;

public class ReportFormController {
    @FXML
    private AnchorPane adminAncPane;

    @FXML
    void SupplyLoadOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.global_flavour.reports/SupplyLoadDetail.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void SalOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.global_flavour.reports/SalaryRepor.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void EmpOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.global_flavour.reports/Employee.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void supOnAction(ActionEvent event) {
        InputStream resource = this.getClass().getResourceAsStream("/lk.ijse.global_flavour.reports/SuplierReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}