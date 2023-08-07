package lk.ijse.orm.thogakade.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.orm.thogakade.entity.Customer;
import lk.ijse.orm.thogakade.repository.CustomerRepository;

public class CustomerController {

    @FXML
    private JFXTextField txtCustId;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtCustAddress;

    @FXML
    private JFXTextField txtCustSalary;

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void saveOnAction(ActionEvent event) {
        Customer customer = getCustomer();
        CustomerRepository studentRepository = new CustomerRepository();     // save eka
        int id  = studentRepository.saveCustomer(customer);
        System.out.println(id);
    }

    @FXML
    void updateOnAction(ActionEvent event) {
        Customer customer = getCustomer();
        CustomerRepository studentRepository = new CustomerRepository();     //update eka
        boolean bool  = studentRepository.updateCustomer(customer);
        System.out.println(bool);
    }

    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setCustId(Integer.parseInt(txtCustId.getText()));
        customer.setCustName(txtCustName.getText());
        customer.setCustAddress(txtCustAddress.getText());
        customer.setCustSalary(txtCustSalary.getText());
        return customer;
    }

}
