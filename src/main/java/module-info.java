module co.edu.uniquindio.armeniagames.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.logging;

    opens co.edu.uniquindio.armeniagames.main to javafx.fxml, javafx.base;
    opens co.edu.uniquindio.armeniagames.controller to javafx.fxml;
    opens co.edu.uniquindio.armeniagames.model to javafx.base, javafx.fxml;

    exports co.edu.uniquindio.armeniagames.main;
    exports co.edu.uniquindio.armeniagames.controller;
    exports co.edu.uniquindio.armeniagames.subcontroller;
    exports co.edu.uniquindio.armeniagames.factory;
    exports co.edu.uniquindio.armeniagames.model;
    exports co.edu.uniquindio.armeniagames.constant;
    exports co.edu.uniquindio.armeniagames.persistence;
    exports co.edu.uniquindio.armeniagames.exception;
    exports co.edu.uniquindio.armeniagames.enumm;
}