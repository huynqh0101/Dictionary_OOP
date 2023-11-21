module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;

    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires freetts;

    opens com.example.demo.game to javafx.fxml;
    exports com.example.demo.game;
    exports com.example.demo.game.game1;
    opens com.example.demo.game.game1 to javafx.fxml;
    exports com.example.demo.game.game2;
    opens com.example.demo.game.game2 to javafx.fxml;

    exports com.example.demo.Controllers;
    opens com.example.demo.Controllers to javafx.fxml;
    exports com.example.demo.App;
    opens com.example.demo.App to javafx.fxml;
    exports com.example.demo.Alerts;
    opens com.example.demo.Alerts to javafx.fxml;
}