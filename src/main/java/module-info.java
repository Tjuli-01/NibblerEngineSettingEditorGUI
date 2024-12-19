module de.tjuli.nibblerenginesettingeditorgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens de.tjuli.nibblerenginesettingeditorgui to javafx.fxml;
    exports de.tjuli.nibblerenginesettingeditorgui;
    exports de.tjuli.nibblerenginesettingeditorgui.controller;
    opens de.tjuli.nibblerenginesettingeditorgui.controller to javafx.fxml;
}