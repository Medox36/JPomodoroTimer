public class Start {
    private static Tray tray;
    private Images images;
    private PopUpMenu popUpMenu;
    private PomodoroGUI pomodoroGUI;

    public void initialize() {
        images = new Images();
        pomodoroGUI = new PomodoroGUI(images);
        popUpMenu = new PopUpMenu(pomodoroGUI);
        activateSystemTrayIcon();
    }

    private void activateSystemTrayIcon() {
        tray = new Tray(images, popUpMenu, pomodoroGUI);
    }

    public static Tray getTrayIcon() {
        return tray;
    }
}