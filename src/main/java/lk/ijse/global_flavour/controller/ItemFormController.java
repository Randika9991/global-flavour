//all added

package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.util.AlertController;
import lk.ijse.global_flavour.util.ValidateField;

import java.awt.*;
import java.sql.SQLException;
import java.util.function.Predicate;

public class ItemFormController {

    @FXML
    private AnchorPane adminAncPane;

    @FXML
    private JFXTextField txtItemId;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtItemPrice;

    @FXML
    private JFXTextField txtItemCatogory;

    @FXML
    private JFXTextField txtItemQTY;

    @FXML
    private TableView<ItemTM> mainCOMItem;

    @FXML
    private TableColumn<?, ?> COMItemId;

    @FXML
    private TableColumn<?, ?> COMItemName;

    @FXML
    private TableColumn<?, ?> COMItemPrice;

    @FXML
    private TableColumn<?, ?> COMItemCategory;

    @FXML
    private TableColumn<?, ?> COMItemQTY;

    @FXML
    private TextField txtsearchItem;

    @FXML
    private Label lblInvalidItemCode;

    @FXML
    void buttonSaveOnACT(ActionEvent event) {

            if(txtItemId.getText().isEmpty()||txtItemName.getText().isEmpty()||txtItemPrice.getText().isEmpty()||txtItemCatogory.getText().isEmpty()||txtItemQTY.getText().isEmpty()){
                AlertController.animationMesseagewrong("Error","Item details not saved. \nPlease make sure to fill the request fields.");
            }else {
                if(ValidateField.ItemCodeCheck(txtItemId.getText())){
                    lblInvalidItemCode.setVisible(false);
                    String itemId = txtItemId.getText();
                    String itemName = txtItemName.getText();
                    String itemPri = txtItemPrice.getText();
                    String itemCate = txtItemCatogory.getText();
                    String itemQTY = txtItemQTY.getText();

                    Item itemAll = new Item(itemId, itemName, itemPri,itemCate,itemQTY);

                    try {
//            boolean isSaved = ItemModel.save(code, description, unitPrice, qtyOnHand);
                        boolean isSaved = ItemModel.save(itemAll);
                        if (isSaved) {

                            AlertController.animationMesseageCorect("CONFIRMATION","Item Save Success!");
                            onActionGetAllItem();
                        }
                    } catch (SQLException e) {
                        System.out.println(e);
                        AlertController.animationMesseagewrong("Error","something went wrong!");

                    }

                }else {
                    lblInvalidItemCode.setVisible(true);
                }


            }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws AWTException {


        if(txtItemId.getText().isEmpty()||txtItemName.getText().isEmpty()||txtItemPrice.getText().isEmpty()||txtItemCatogory.getText().isEmpty()||txtItemQTY.getText().isEmpty()){

        }else {
            boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

            if(ok) {
                String code = txtItemId.getText();

                try {
                    boolean isDeleted = ItemModel.delete(code);
                    if (isDeleted) {
                        AlertController.animationMesseageCorect("CONFIRMATION","Delete Success!");

                        onActionGetAllItem();
                    }
                } catch (SQLException e) {
                    AlertController.animationMesseagewrong("Error","something went wrong!");
                }

            }
        }

    }

    @FXML

    void btnUpdateOnAction(ActionEvent event) {

        if(txtItemId.getText().isEmpty()||txtItemName.getText().isEmpty()||txtItemPrice.getText().isEmpty()||txtItemCatogory.getText().isEmpty()||txtItemQTY.getText().isEmpty()){

        }else {
            String itemId = txtItemId.getText();
            String itemName = txtItemName.getText();
            String itemPri = txtItemPrice.getText();
            String itemCate = txtItemCatogory.getText();
            String itemQTY = txtItemQTY.getText();

            Item itemAll = new Item(itemId, itemName, itemPri,itemCate,itemQTY);

            try {
                boolean isUpdated = ItemModel.update(itemAll);
                AlertController.animationMesseageCorect("CONFIRMATION","Item updated!");
                onActionGetAllItem();
            } catch (SQLException e) {

                AlertController.animationMesseagewrong("Error","something went wrong!");
            }
        }
    }

    @FXML
    void itemIdOnAction(ActionEvent event) {
        String id = txtsearchItem.getText();

        try {
            Item cust = ItemModel.search(id);
            if (cust != null) {
                txtItemId.setText(cust.getItemCode());
                txtItemName.setText(cust.getItemName());
                txtItemPrice.setText(cust.getUnitPrice());
                txtItemCatogory.setText(cust.getCategory());
                txtItemQTY.setText(cust.getQty());
            }
        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }

    }
    @FXML
    void itemIdOnActionSerch(ActionEvent event) {
        String id = txtsearchItem.getText();

        try {
            Item cust = ItemModel.search(id);
            if (cust != null) {
                txtItemId.setText(cust.getItemCode());
                txtItemName.setText(cust.getItemName());
                txtItemPrice.setText(cust.getUnitPrice());
                txtItemCatogory.setText(cust.getCategory());
                txtItemQTY.setText(cust.getQty());
            }
        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }

    }

    @FXML
    void itemOnMouse(MouseEvent event) {
        TablePosition pos=mainCOMItem.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<ItemTM,?>> columns=mainCOMItem.getColumns();

        txtItemId.setText(columns.get(0).getCellData(row).toString());
        txtItemName.setText(columns.get(1).getCellData(row).toString());
        txtItemPrice.setText(columns.get(2).getCellData(row).toString());
        txtItemCatogory.setText(columns.get(3).getCellData(row).toString());
        txtItemQTY.setText(columns.get(4).getCellData(row).toString());


    }

    @FXML
    void searchItemOnKey(KeyEvent event) throws SQLException {
        String searchValue=txtsearchItem.getText().trim();
        ObservableList<ItemTM>obList= ItemModel.getAll();

        if (!searchValue.isEmpty()) {
            ObservableList<ItemTM> filteredData = obList.filtered(new Predicate<ItemTM>(){
                @Override
                public boolean test(ItemTM itemTM) {
                    return String.valueOf(itemTM.getItemCode()).toLowerCase().contains(searchValue.toLowerCase());        }
            });
            mainCOMItem.setItems(filteredData);} else {
            mainCOMItem.setItems(obList);
        }

    }


    @FXML
    void initialize() {
        onActionGetAllItem();
        setCellValuefactory();
        lblInvalidItemCode.setVisible(false);

    }

    void onActionGetAllItem() {
        try {
            ObservableList<ItemTM> supList = ItemModel.getAll();
            mainCOMItem.setItems(supList);


        } catch (SQLException e) {
            AlertController.animationMesseagewrong("Error","something went wrong!");
        }

    }

    void setCellValuefactory(){
        COMItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        COMItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        COMItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        COMItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        COMItemQTY.setCellValueFactory(new PropertyValueFactory<>("Qty"));

    }

    public void lblClearAllOnAction(ActionEvent actionEvent) {
        txtItemId.setText("");
        txtItemName.setText("");
        txtItemPrice.setText("");
        txtItemCatogory.setText("");
        txtItemQTY.setText("");
    }
}
