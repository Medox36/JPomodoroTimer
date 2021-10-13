import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;

public class Settings {
    private File standardSettingsFile;
    private File customFile;
    private String redMin, redSec;
    private String blueMin, blueSec;
    private String darkBlueMin, darkBlueSec;
    private boolean notifications;
    private boolean autoPomodoros;
    private boolean autoBreaks;

    public Settings() {
        standardSettingsFile = new File(URI.create(String.valueOf(Settings.class.getResource("settings/settings.txt"))));
        redMin = "20";
        redSec = "10";
    }

    private void loadContentsFromFile() {

    }

    private void loadContentsFromFile(File file) {

    }

    private void saveContentsToFile() throws IOException {
        System.out.println(standardSettingsFile.isFile() + ", " + standardSettingsFile.getName());
        FileWriter fw = new FileWriter(standardSettingsFile);
        fw.write(redMin);
        fw.write(redSec);
    }

    private void saveContentsToFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
    }
}