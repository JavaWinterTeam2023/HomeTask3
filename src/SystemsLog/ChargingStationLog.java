package SystemsLog;


import java.io.IOException;


public class ChargingStationLog {

    private static    String LOG_DIRECTORY = "logs/";
    private static  String ARCHIVE_DIRECTORY = "archive/";
    private static  String CHARGING_SYSTEM_LOG_FILE = LOG_DIRECTORY + "charging_station.log";
    private static   String ARCHIVED_CHARGING_SYSTEM_LOG_FILE = ARCHIVE_DIRECTORY + "charging_station_%s.zip"; // %s will be replaced with a timestamp
    private LogManager logManager;

    public ChargingStationLog() {
        this.logManager = new LogManager();
    }

    
    public void initChargingSystemLogFile() {
        try {
            logManager.createLogFile(CHARGING_SYSTEM_LOG_FILE);
        } catch (IOException e) {
            System.err.println("Error initializing charging system log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void archiveChargingSystemLogFile() {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String archiveFileName = String.format(ARCHIVED_CHARGING_SYSTEM_LOG_FILE, timestamp);

        try {
            logManager.archiveLogFile(CHARGING_SYSTEM_LOG_FILE, archiveFileName);
            System.out.println("Charging system log archived: " + archiveFileName);
        } catch (IOException e) {
            System.err.println("Error archiving charging system log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

  
    public void deleteChargingSystemLogFile() {
        try {
            logManager.deleteLogFile(CHARGING_SYSTEM_LOG_FILE);
            System.out.println("Charging system log file deleted");
        } catch (IOException e) {
            System.err.println("Error deleting charging system log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
