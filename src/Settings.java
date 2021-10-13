import java.io.*;
import java.net.URI;

public class Settings {
    private final File standardSettingsFile;
    private String redMin, redSec;
    private String blueMin, blueSec;
    private String darkBlueMin, darkBlueSec;
    private boolean notifications;
    private boolean autoPomodoros;
    private boolean autoBreaks;

    public Settings() {
        standardSettingsFile = new File(URI.create(String.valueOf(Settings.class.getResource("settings/settings.txt"))));
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
        redMin = br.readLine();
        redSec = br.readLine();
        blueMin = br.readLine();
        blueSec = br.readLine();
        darkBlueMin = br.readLine();
        darkBlueSec = br.readLine();
        notifications = Boolean.parseBoolean(br.readLine());
        autoPomodoros = Boolean.parseBoolean(br.readLine());
        autoBreaks = Boolean.parseBoolean(br.readLine());
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
        String str = redMin + l + redSec + l + blueMin + l + blueSec + l + darkBlueMin + l + darkBlueSec + l + notifications + l + autoPomodoros + l + autoBreaks;
        fw.write(str);
        fw.flush();
        fw.close();
    }

    private void loadStandardValues() {
        redMin = "20";
        redSec = "00";
        blueMin = "05";
        blueSec = "00";
        darkBlueMin = "15";
        darkBlueSec = "00";
        notifications = false;
        autoPomodoros = false;
        autoBreaks = false;
    }
}