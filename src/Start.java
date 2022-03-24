public class Start {
    private static Tray tray;
    private PopUpMenu popUpMenu;
    private PomodoroGUI pomodoroGUI;

    public void initialize() {
        pomodoroGUI = new PomodoroGUI();
        popUpMenu = new PopUpMenu(pomodoroGUI);
        activateSystemTrayIcon();
    }

    private void activateSystemTrayIcon() {
        tray = new Tray(popUpMenu, pomodoroGUI);
    }

    public static Tray getTrayIcon() {
        return tray;
    }
}