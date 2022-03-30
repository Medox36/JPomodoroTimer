import java.io.*;

public class Settings {
    private static Settings INSTANCE;

    private String redMin, redSec;
    private String blueMin, blueSec;
    private String darkBlueMin, darkBlueSec;
    private String alarm;
    private int breakInterval;
    private boolean muted;
    private boolean minimizeToTray;
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
            System.err.println("Either a custom settings.txt file was found but couldn't be loaded or there are some problems with the program.");
            System.err.println("If this error persists try re-downloading the Jar-File or EXE-File.");
            e.printStackTrace();
            System.err.println("]");
            loadStandardValues();
        }
    }

    public void playRightSound() {
        switch (alarm) {
            case "off" -> {}
            case "bell" -> PomodoroSound.playBellAlarm();
            case "digital" -> PomodoroSound.playDigitalAlarm();
            default -> throw new IllegalStateException("Unexpected value: " + alarm);
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
            alarm = str;
        } else {
            alarm = "off";
        }
        try {
            breakInterval = Integer.parseInt(fr.readLine());
            if (breakInterval < 0 || breakInterval > 6) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            breakInterval = 3;
        }
        muted = Boolean.parseBoolean(fr.readLine());
        minimizeToTray = Boolean.parseBoolean(fr.readLine());
        autoBreaks = Boolean.parseBoolean(fr.readLine());
        autoPomodoros = Boolean.parseBoolean(fr.readLine());
    }

    public void saveToStandardFile() throws IOException {
        saveContentsToCustomFile(new File("settings.txt"));
    }

    public void saveContentsToCustomFile(File file) throws IOException {
        saveToFile(new FileWriter(file));
    }

    private void saveToFile(FileWriter fw) throws IOException {
        String l = System.lineSeparator();
        String str = zeroFill(redMin) + l + zeroFill(redSec) + l + zeroFill(blueMin) + l + zeroFill(blueSec) + l
                + zeroFill(darkBlueMin) + l + zeroFill(darkBlueSec) + l + alarm + l + breakInterval + l + muted + l
                + minimizeToTray + l + autoBreaks + l + autoPomodoros;
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
        alarm = "off";
        breakInterval = 3;
        muted = false;
        minimizeToTray = true;
        autoBreaks = false;
        autoPomodoros = false;
    }

    private String zeroFill(String val) {
        if (val.length() < 2)
            return "0" + val;
        else
            return val;
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

    public int getBreakInterval() {
        return breakInterval;
    }

    public boolean isMuted() {
        return muted;
    }

    public boolean isMinimizeToTray() {
        return minimizeToTray;
    }

    public boolean isAutoBreaks() {
        return autoBreaks;
    }

    public boolean isAutoPomodoros() {
        return autoPomodoros;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setRedTime(String redMin, String redSec) {
        this.redMin = redMin;
        this.redSec = redSec;
    }

    public void setRedTime(String time) {
        this.redMin = String.valueOf(time.charAt(0)) + time.charAt(1);
        this.redSec = String.valueOf(time.charAt(3)) + time.charAt(4);
    }

    public void setBlueTime(String blueMin, String blueSec) {
        this.blueMin = blueMin;
        this.blueSec = blueSec;
    }

    public void setBlueTime(String time) {
        this.blueMin = String.valueOf(time.charAt(0)) + time.charAt(1);
        this.blueSec = String.valueOf(time.charAt(3)) + time.charAt(4);
    }

    public void setDarkBlueTime(String darkBlueMin, String darkBlueSec) {
        this.darkBlueMin = darkBlueMin;
        this.darkBlueSec = darkBlueSec;
    }

    public void setDarkBlueTime(String time) {
        this.darkBlueMin = String.valueOf(time.charAt(0)) + time.charAt(1);
        this.darkBlueSec = String.valueOf(time.charAt(3)) + time.charAt(4);
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public void setBreakInterval(int breakInterval) {
        this.breakInterval = breakInterval;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public void setMinimizeToTray(boolean minimizeToTray) {
        this.minimizeToTray = minimizeToTray;
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