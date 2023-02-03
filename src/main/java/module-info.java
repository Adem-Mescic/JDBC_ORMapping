module com.example.jdbc_ormapping {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.jdbc_ormapping to javafx.fxml;
    exports com.example.jdbc_ormapping;
}