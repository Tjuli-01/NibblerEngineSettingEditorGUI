package de.tjuli.nibblerenginesettingeditorgui.util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SettingsSaver {

    public static void saveSettings(int yourElo, int opponentElo, boolean isWhite) throws IOException {

        int whiteElo = isWhite ? yourElo : opponentElo;
        int blackElo = isWhite ? opponentElo : yourElo;
        int eloDifference = whiteElo - blackElo;
        String contemptMode = isWhite ? "white_side_analysis" : "black_side_analysis";
        String filePath = System.getenv("APPDATA") + "\\Nibbler\\engines.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject json = new JSONObject(content);
        for (String key : json.keySet()) {
            JSONObject engine = json.getJSONObject(key);
            JSONObject options = engine.getJSONObject("options");
            if (!options.has("WDLDrawRateReference")) {
                options.put("WDLDrawRateReference", 0.58);
            }
            options.put("WDLCalibrationElo", whiteElo);
            options.put("Contempt", eloDifference);
            options.put("ContemptMode", contemptMode);
            options.put("WLDEvalObjectivity", 1);

        }


        try (FileWriter file = new FileWriter(filePath)) {
            file.write(json.toString(4)); // Pretty print with an indent of 4 spaces
        }
    }
}