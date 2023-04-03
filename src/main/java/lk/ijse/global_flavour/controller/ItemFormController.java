package lk.ijse.global_flavour.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.global_flavour.dto.Item;
import lk.ijse.global_flavour.dto.tm.ItemTM;
import lk.ijse.global_flavour.dto.tm.SuppliersTM;
import lk.ijse.global_flavour.model.ItemModel;
import lk.ijse.global_flavour.model.SuppliersModel;
import lk.ijse.global_flavour.util.AlertController;

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
    void buttonSaveOnACT(ActionEvent event) {

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
                    new Alert(Alert.AlertType.CONFIRMATION, "Item saved!").show();
                    onActionGetAllItem();
                }
            } catch (SQLException e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }



    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Delete item");

        if(ok){
            String code = txtItemId.getText();
            try {
                boolean isDeleted = ItemModel.delete(code);
                if (isDeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "deleted Success!").show();
                    onActionGetAllItem();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
            }

        }

    }

    @FXML

    void btnUpdateOnAction(ActionEvent event) {

        boolean ok = AlertController.okconfirmmessage("Are you Sure. Do you wont Update item");

        if(ok){
            String itemId = txtItemId.getText();
            String itemName = txtItemName.getText();
            String itemPri = txtItemPrice.getText();
            String itemCate = txtItemCatogory.getText();
            String itemQTY = txtItemQTY.getText();

            Item itemAll = new Item(itemId, itemName, itemPri,itemCate,itemQTY);

            try {
                boolean isUpdated = ItemModel.update(itemAll);
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!").show();
                onActionGetAllItem();
            } catch (SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
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
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
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
            new Alert(Alert.AlertType.ERROR, "something happened!").show();
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

    }

    void onActionGetAllItem() {
        try {
            ObservableList<ItemTM> supList = ItemModel.getAll();
            mainCOMItem.setItems(supList);


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
        }

    }

    void setCellValuefactory(){
        COMItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        COMItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        COMItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        COMItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        COMItemQTY.setCellValueFactory(new PropertyValueFactory<>("Qty"));

    }

}
