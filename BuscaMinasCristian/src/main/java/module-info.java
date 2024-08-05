module com.mycompany.buscaminascristian {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.buscaminascristian to javafx.fxml;
    exports com.mycompany.buscaminascristian;
}
