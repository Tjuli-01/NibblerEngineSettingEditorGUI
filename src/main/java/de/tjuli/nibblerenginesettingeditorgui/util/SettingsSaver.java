package de.tjuli.nibblerenginesettingeditorgui.util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsSaver {


    public String saveSettings(int yourElo, int opponentElo, boolean isWhite) throws IOException {
        if (isNibblerRunning()) {
            return "⚠Nibbler is currently running. Please close it before saving settings.";
        }

        int eloDifference = yourElo - opponentElo;
        String contemptMode = isWhite ? "white_side_analysis" : "black_side_analysis";
        String enginesPath = System.getenv("APPDATA") + "\\Nibbler\\engines.json";
        String configPath = System.getenv("APPDATA") + "\\Nibbler\\config.json";
        String currentEnginePath;
        try {
            String configContent = new String(Files.readAllBytes(Paths.get(configPath)));
            JSONObject config = new JSONObject(configContent);
            currentEnginePath = config.getString("path");
        } catch (Exception e) {
            return "⚠Could not read Nibbler's config.json: " + e.getMessage();
        }


        String enginesContent = new String(Files.readAllBytes(Paths.get(enginesPath)));
        JSONObject json = new JSONObject(enginesContent);

        if (!json.has(currentEnginePath)) {
            return "⚠Could not find the currently selected engine: " + currentEnginePath;
        }

        JSONObject engine = json.getJSONObject(currentEnginePath);
        JSONObject options = engine.getJSONObject("options");
        options.put("WDLCalibrationElo", yourElo);
        options.put("Contempt", eloDifference);
        options.put("ContemptMode", contemptMode);
        options.put("WDLEvalObjectivity", 0);

        try (FileWriter file = new FileWriter(enginesPath)) {
            file.write(json.toString(4));
        }

        return null; // No error
    }

    private boolean isNibblerRunning() {
        return ProcessHandle.allProcesses()
                .anyMatch(process -> process.info().command().orElse("").toLowerCase().contains("nibbler.exe"));
    }
}