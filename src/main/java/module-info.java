module com.example.jdbc_ormapping {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.jdbc_ormapping to javafx.fxml;
    opens com.example.model to javafx.base;
    exports com.example.jdbc_ormapping;
}