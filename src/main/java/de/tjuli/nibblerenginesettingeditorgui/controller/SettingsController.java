package de.tjuli.nibblerenginesettingeditorgui.controller;

import de.tjuli.nibblerenginesettingeditorgui.util.SettingsSaver;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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

    @FXML
    protected void onSaveButtonClick() throws IOException {
        if (validateInputs()) {
            int yourElo = Integer.parseInt(yourEloField.getText());
            int opponentElo = Integer.parseInt(opponentEloField.getText());
            boolean isWhite = whiteCheckBox.isSelected();

            SettingsSaver.saveSettings(yourElo, opponentElo, isWhite);
        }
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
            errorLabel.setText(errors.toString().trim());
            return false;
        }

        errorLabel.setText("");
        return true;
    }
}