package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// ================= CLASS =================
public class BackupHandler {

    // ================= VARIABLES =================
    private static final String MAIN_FILE = "students.txt";
    private static final String BACKUP_FILE = "backup.txt";

    // ================= METHOD: TAKE BACKUP =================
    public void takeBackup() {
        try {
            FileReader fileReader = new FileReader(MAIN_FILE);
            BufferedReader reader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(BACKUP_FILE);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("Backup created successfully.");
        } catch (IOException e) {
            System.out.println("Error while taking backup: " + e.getMessage());
        }
    }

    // ================= METHOD: RESTORE BACKUP =================
    public void restoreBackup() {
        try {
            FileReader fileReader = new FileReader(BACKUP_FILE);
            BufferedReader reader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter(MAIN_FILE);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();
            System.out.println("Data restored successfully from backup.");
        } catch (IOException e) {
            System.out.println("Error while restoring backup: " + e.getMessage());
        }
    }
}