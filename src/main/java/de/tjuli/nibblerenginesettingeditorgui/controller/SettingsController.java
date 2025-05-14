package de.tjuli.nibblerenginesettingeditorgui.controller;

import de.tjuli.nibblerenginesettingeditorgui.util.SettingsSaver;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class SettingsController {

    @FXML
    private TextField yourEloField;

    @FXML
    private TextField opponentEloField;

    @FXML
    private CheckBox whiteCheckBox;

    @FXML
    private Label errorLabel;

    private final SettingsSaver settingsSaver = new SettingsSaver();

    @FXML
    protected void onSaveButtonClick() throws IOException {
        if (validateInputs()) {
            int yourElo = Integer.parseInt(yourEloField.getText());
            int opponentElo = Integer.parseInt(opponentEloField.getText());
            boolean isWhite = whiteCheckBox.isSelected();

            String error = settingsSaver.saveSettings(yourElo, opponentElo, isWhite);
            if (error != null) {
                displayText(error, "red");
            } else {
                displayText("Settings saved successfully!", "green");
            }
        }
    }
    private void displayText(String text, String color) {
        errorLabel.setStyle("-fx-text-fill: " + color + ";");
        errorLabel.setText(text);
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(_ -> errorLabel.setText(""));
        pause.play();
    }

    private boolean validateInputs() {
        StringBuilder errors = new StringBuilder();

        try {
            Integer.parseInt(yourEloField.getText());
        } catch (NumberFormatException e) {
            errors.append("⚠Invalid Your ELO\n");
        }

        try {
            Integer.parseInt(opponentEloField.getText());
        } catch (NumberFormatException e) {
            errors.append("⚠Invalid Opponent's ELO\n");
        }

        if (!errors.isEmpty()) {
            displayText(errors.toString().trim(), "red");
            return false;
        }

        errorLabel.setText("");
        return true;
    }
}