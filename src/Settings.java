import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Settings {
    private File standardSettingsFile;
    private String redMin, redSec;
    private String blueMin, blueSec;
    private String darkBlueMin, darkBlueSec;
    private String notifications;
    private boolean autoBreaks;
    private boolean autoPomodoros;

    public Settings() {
        System.out.println(Settings.class.getResource("settings/settings.txt"));
        try {
            standardSettingsFile = new File(Objects.requireNonNull(Settings.class.getResource("settings/settings.txt")).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        checkFiles();
    }

    private void checkFiles() {
        try {
            if (!new File("settings.txt").exists()) {
                loadContentsFromStandardFile();
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

    private void loadContentsFromStandardFile() throws IOException {
        loadFromFile(new FileReader(standardSettingsFile));
    }

    private void loadContentsFromCustomFile(File file) throws IOException {
        loadFromFile(new FileReader(file));
    }

    private void loadFromFile(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        String str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            redMin = str;
        } else {
            redMin = "25";
        }
        str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            redSec = str;
        } else {
            redSec = "00";
        }
        str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            blueMin = str;
        } else {
            blueMin = "05";
        }
        str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            blueSec = str;
        } else {
            blueSec = "00";
        }
        str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            darkBlueMin = str;
        } else {
            darkBlueMin = "15";
        }
        str = br.readLine();
        if (Integer.parseInt(str) > -1 && Integer.parseInt(str) < 100) {
            darkBlueSec = str;
        } else {
            darkBlueSec = "00";
        }
        str = br.readLine();
        if (str.equals("off") || str.equals("bell") || str.equals("digital")) {
            notifications = str;
        } else {
            notifications = "off";
        }
        autoBreaks = Boolean.parseBoolean(br.readLine());
        autoPomodoros = Boolean.parseBoolean(br.readLine());
    }

    public void saveContentsToCustomFile(File file){
        try {
            saveToFile(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}