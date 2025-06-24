module eproma {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eproma.presentation to javafx.fxml;
    exports eproma.presentation;
    exports eproma.domain.model;
}
