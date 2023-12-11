module edu.yu.cs.intro.bank2023 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.yu.cs.intro.bank2023 to javafx.fxml;
    exports edu.yu.cs.intro.bank2023;
}