package com.example.jdbc_ormapping;

import com.example.model.ConnectionHandler;
import com.example.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.catalog.Catalog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public TableColumn<Person,Integer> tablecol_id;
    public TableView tableview;
    public Button btn_cancel;
    public Button btn_send;
    public HBox hbox_buttons;
    public TextField tf_wohnort;
    public TextField tf_name;
    public TextField tf_id;
    public VBox vbox_textfield;
    public VBox vbox_labels;
    public TableColumn tablecol_wohnort;
    public TableColumn tablecol_name;
    public Button btn_refresh;

    public void cancel(ActionEvent actionEvent) {
        clear();
    }

    public void clear(){
        tf_id.setText("");
        tf_name.setText("");
        tf_wohnort.setText("");
    }

    public void send(ActionEvent actionEvent) throws SQLException {

        try {
            if (tf_id.getText().length() > 0) {
                if (tf_name.getText().length() > 0) {
                    if (tf_wohnort.getText().length() > 0) {
                        String values = tf_id.getText() + "," + tf_name.getText() + "," + tf_wohnort.getText();
                        ConnectionHandler.getInstance().insertInto(values);
                        success();
                        clear();
                    } else {
                        throw new Exception("Befüllen Sie bitte alle Textfelder");
                    }
                } else {
                    throw new Exception("Befüllen Sie bitte alle Textfelder");
                }
            } else {
                throw new Exception("Befüllen Sie bitte alle Textfelder");
            }
        } catch (Exception e) {
            error(e.getMessage());
        }

    }

    @FXML
    public void initialize() {
        tablecol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tablecol_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tablecol_wohnort.setCellValueFactory(new PropertyValueFactory<>("wohnort"));

    }



    public void error(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.show();
    }

    public void success(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Information sent!");
        alert.show();
    }

    public void refresh(ActionEvent actionEvent) throws SQLException {
        ArrayList<Person> list = ConnectionHandler.getInstance().refresh();

        ObservableList<Person> observableList = FXCollections.observableList(list);

        tableview.setItems(observableList);
    }
}