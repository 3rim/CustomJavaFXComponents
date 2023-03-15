module org.erim.components {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.erim.components to javafx.fxml;
    exports org.erim.components;
}
