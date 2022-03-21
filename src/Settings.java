import java.io.*;

public class Settings {
    private static Settings INSTANCE;

    private String redMin, redSec;
    private String blueMin, blueSec;
    private String darkBlueMin, darkBlueSec;
    private String notifications;
    private boolean autoBreaks;
    private boolean autoPomodoros;

    public Settings() {
        checkFiles();
    }

    private void checkFiles() {
        try {
            if (!new File("settings.txt").exists()) {
                loadStandardValues();
            } else {
                File file = new File("settings.txt");
                loadContentsFromCustomFile(file);
            }
        } catch (Exception e) {
            System.err.println("[");
            System.err.println("The program had issues reading from the \"settings.txt\" file(s) so the standard values were loaded.");
            System.err.println("Either the default settings.txt within the jar-file or a custom settings.txt file was found or couldn't be loaded.");
            System.err.println("If this error persists try re-downloading the Jar-File");
            e.printStackTrace();
            System.err.println("]");
            loadStandardValues();
        }
    }


    public void loadContentsFromCustomFile(File file) throws IOException {
        loadFromFile(new BufferedReader(new FileReader(file)));
    }

    private void loadFromFile(BufferedReader fr) throws IOException {
        String str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            redMin = str;
        } else {
            redMin = "25";
        }
        str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            redSec = str;
        } else {
            redSec = "00";
        }
        str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            blueMin = str;
        } else {
            blueMin = "05";
        }
        str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            blueSec = str;
        } else {
            blueSec = "00";
        }
        str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            darkBlueMin = str;
        } else {
            darkBlueMin = "15";
        }
        str = fr.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            darkBlueSec = str;
        } else {
            darkBlueSec = "00";
        }
        str = fr.readLine();
        if (str.equals("off") || str.equals("bell") || str.equals("digital")) {
            notifications = str;
        } else {
            notifications = "off";
        }
        autoBreaks = Boolean.parseBoolean(fr.readLine());
        autoPomodoros = Boolean.parseBoolean(fr.readLine());
    }

    public void saveContentsToCustomFile(File file) throws IOException {
        saveToFile(new FileWriter(file));
    }

    private void saveToFile(FileWriter fw) throws IOException {
        String l = System.lineSeparator();
        String str = redMin + l + redSec + l + blueMin + l + blueSec + l + darkBlueMin + l + darkBlueSec + l + notifications + l + autoBreaks + l + autoPomodoros;
        fw.write(str);
        fw.flush();
        fw.close();
    }

    private void loadStandardValues() {
        redMin = "25";
        redSec = "00";
        blueMin = "05";
        blueSec = "00";
        darkBlueMin = "15";
        darkBlueSec = "00";
        notifications = "off";
        autoBreaks = false;
        autoPomodoros = false;
    }

    public String getRedTime() {
        return redMin + ":" + redSec;
    }

    public String getBlueTime() {
        return blueMin + ":" + blueSec;
    }

    public String getDarkBlueTime() {
        return darkBlueMin + ":" + darkBlueSec;
    }

    public boolean isAutoBreaks() {
        return autoBreaks;
    }

    public boolean isAutoPomodoros() {
        return autoPomodoros;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public void setAutoBreaks(boolean autoBreaks) {
        this.autoBreaks = autoBreaks;
    }

    public void setAutoPomodoros(boolean autoPomodoros) {
        this.autoPomodoros = autoPomodoros;
    }

    public static Settings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Settings();
        }
        return INSTANCE;
    }
}