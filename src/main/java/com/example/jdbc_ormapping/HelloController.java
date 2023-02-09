package com.example.jdbc_ormapping;

import com.example.model.ConnectionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.catalog.Catalog;
import java.sql.SQLException;
import java.util.List;

public class HelloController {
    public TableColumn tablecol_id;
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
        setAutosize();
    }

    public void setAutosize() {
        vbox_labels.autosize();
        vbox_textfield.autosize();
        hbox_buttons.autosize();
        tableview.autosize();
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
        List<List<String>> list = ConnectionHandler.getInstance().refresh();

        for (List<?> objects : list) {
            tableview.setItems(FXCollections.observableList(list));
        }

    }
}