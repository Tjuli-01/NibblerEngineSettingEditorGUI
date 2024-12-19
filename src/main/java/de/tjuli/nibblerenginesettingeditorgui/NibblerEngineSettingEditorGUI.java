package de.tjuli.nibblerenginesettingeditorgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//For manual setup of Nibbler use https://matthewsadler.me.uk/engine-chess/setting-up-wdl-contempt-for-leela-in-nibbler/
public class NibblerEngineSettingEditorGUI extends Application {
    public static final NibblerEngineSettingEditorGUI INSTANCE = new NibblerEngineSettingEditorGUI();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NibblerEngineSettingEditorGUI.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 480, 360);
        stage.setTitle("NibblerEngineSettingsEditorGUI");
        stage.setScene(scene);
        stage.show();

    }
    public void launchApp() {
        launch();
    }
}