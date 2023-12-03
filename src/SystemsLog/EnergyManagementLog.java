package SystemsLog;

import java.io.IOException;

public class EnergyManagementLog {

    private static final String LOG_DIRECTORY = "logs/";
    private static final String ARCHIVE_DIRECTORY = "archive/";
    private static final String ENERGY_MANAGEMENT_LOG_FILE = LOG_DIRECTORY + "energy_management.log";
    private static final String ARCHIVED_ENERGY_MANAGEMENT_LOG_FILE = ARCHIVE_DIRECTORY + "energy_management_%s.zip"; // %s will be replaced with a timestamp
    private LogManager logManager;

    public EnergyManagementLog() {
        this.logManager = new LogManager();
    }

    public void initEnergyManagementLogFile() {
        try {
            logManager.createLogFile(ENERGY_MANAGEMENT_LOG_FILE);
        } catch (IOException e) {
            System.err.println("Error initializing energy management log file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void archiveEnergyManagementLogFile() {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String archiveFileName = String.format(ARCHIVED_ENERGY_MANAGEMENT_LOG_FILE, timestamp);

        try {
            logManager.archiveLogFile(ENERGY_MANAGEMENT_LOG_FILE, archiveFileName);
            System.out.println("Energy management log archived: " + archiveFileName);
        } catch (IOException e) {
            System.err.println("Error archiving energy management log file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteEnergyManagementLogFile() {
        try {
            logManager.deleteLogFile(ENERGY_MANAGEMENT_LOG_FILE);
            System.out.println("Energy management log file deleted");
        } catch (IOException e) {
            System.err.println("Error deleting energy management log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Additional methods specific to energy management can be added as needed
}