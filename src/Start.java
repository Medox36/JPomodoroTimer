public class Start {
    private static Tray tray;
    private Images images;
    private Settings settings;
    private PopUpMenu popUpMenu;
    private PomodoroGUI pomodoroGUI;

    public Start() {

    }

    public void initialize() {
        images = new Images();
        settings = new Settings();
        pomodoroGUI = new PomodoroGUI(images, settings);
        popUpMenu = new PopUpMenu(settings, pomodoroGUI);
        activateSystemTrayIcon();
    }

    private void activateSystemTrayIcon() {
        tray = new Tray(images, settings, popUpMenu, pomodoroGUI);
    }

    public static Tray getTrayIcon() {
        return tray;
    }
}