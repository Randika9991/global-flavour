//category change description

package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.global_flavour.dto.CashierCustomer;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.OrderCartDTO;
import lk.ijse.global_flavour.dto.tm.CartTM;
import lk.ijse.global_flavour.dto.tm.OrderTM;
import lk.ijse.global_flavour.model.CashierCustomerModel;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.model.OrderModel;
import lk.ijse.global_flavour.model.PlaceOrderModel;
import lk.ijse.global_flavour.util.ButtonColourController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {

    public static String generateNextOrderIdShireDeliveryController;

    public static String getGenerateNextOrderIdShireDeliveryController() {
        return generateNextOrderIdShireDeliveryController;
    }

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton btnNewCustom;

    @FXML
    private TableView<OrderTM> mainCOMItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> COlItemName;

    @FXML
    private TableColumn<?, ?> colItemCategory;

    @FXML
    private TableColumn<?, ?> colItemQty;

    @FXML
    private TableColumn<?, ?> colItemUnitPrice;

    @FXML
    private TableColumn<?, ?> colItemTotal;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private Label lblOrderId;

    @FXML
    private JFXComboBox cmbCustomerId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private JFXRadioButton radioButton ;

    @FXML
    private JFXComboBox cmbItemCode;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblNetTotal;

    private ObservableList<OrderTM> obList = FXCollections.observableArrayList();


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateNextOrderId();
        setOrderDate();
        loadCustomerIds();
        loadItemCodes();
        setCellValueFactory();
    }
    void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        COlItemName.setCellValueFactory(new PropertyValueFactory<>("description"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();

            lblOrderId.setText(id);
            generateNextOrderIdShireDeliveryController=id;

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CashierCustomerModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = String.valueOf(cmbCustomerId.getValue());

        try {
            CashierCustomer customer = CashierCustomerModel.search(id);
            lblCustomerName.setText(customer.getCustomerName());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void loadItemCodes() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = ItemModel.loadCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }
    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        try {
            Item item = ItemModel.search(code);
            fillItemFields(item);

            txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
    private void fillItemFields(Item item) {
        lblItemName.setText(item.getItemName());
        lblUnitPrice.setText(item.getUnitPrice());
        lblCategory.setText(item.getCategory());
        lblQtyOnHand.setText(item.getQty());
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = String.valueOf(cmbItemCode.getValue());
        String itemName = lblItemName.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        String category = lblCategory.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double total = qty * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (!obList.isEmpty()) {
            for (int i = 0; i < mainCOMItem.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colItemQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    mainCOMItem.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }
        OrderTM tm = new OrderTM(code,itemName, category, qty, unitPrice, total, btnRemove);

        obList.add(tm);
        mainCOMItem.setItems(obList);
        calculateNetTotal();

        txtQty.setText("");

    }
    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = mainCOMItem.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                mainCOMItem.refresh();
                //calculateNetTotal();
            }

        });
    }
    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < mainCOMItem.getItems().size(); i++) {
            double total = (double) colItemTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/cashiercustomer_form.fxml"));
        adminAncPane.getChildren().add(load);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String oId = lblOrderId.getText();
        String cId = String.valueOf(cmbCustomerId.getValue());
        double payment = Double.parseDouble(lblNetTotal.getText());

        List<OrderCartDTO> orderDTOList = new ArrayList<>();
        OrderTM orderTM = null;
        for (int i = 0; i < mainCOMItem.getItems().size(); i++) {
            orderTM = obList.get(i);
            OrderCartDTO cartDTO = new OrderCartDTO(
                    orderTM.getCode(),
                    orderTM.getQty(),
                    orderTM.getUnitPrice()
            );
            orderDTOList.add(cartDTO);
        }

        try {
            boolean isSaved = PlaceOrderModel.placeOrder(oId,cId,payment,orderDTOList,orderTM);
            if(isSaved) {
                generateNextOrderId();
                new Alert(Alert.AlertType.ERROR, "selected...").show();
           }
       }
        catch(Exception e) {
           e.printStackTrace();
            System.out.println(e);
           new Alert(Alert.AlertType.ERROR, "SQL Error").show();
       }
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);

    }

    @FXML
    void radioButtonOnAction(ActionEvent event) throws IOException {
        if (radioButton.isSelected()) {
            Stage stage = new Stage();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk.ijse.global_flavour.view/delivery.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("lk.ijse.global_flavour.assets/icons8-deliver-food-101.png"));
            stage.setScene(scene);
            stage.show();

            System.out.println("Option 1 selected");
        }
    }
}
