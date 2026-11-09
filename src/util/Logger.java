package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

// ================= CLASS =================
public class Logger {

    // ================= VARIABLES =================
    private static final String LOG_FILE = "activity_log.txt";

    // ================= METHOD: LOG ACTIVITY =================
    public void logActivity(String message) {
        try {
            Date currentDate = new Date();

            FileWriter fileWriter = new FileWriter(LOG_FILE, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write(currentDate + " - " + message);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing log: " + e.getMessage());
        }
    }
}