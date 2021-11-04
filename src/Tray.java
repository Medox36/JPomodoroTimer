import java.awt.*;

public class Tray extends TrayIcon {
    private SystemTray systemTray;
    private Images images;
    private Settings settings;
    private PomodoroGUI pomodoroGUI;

    public Tray(Images images, Settings settings, PopUpMenu popUpMenu, PomodoroGUI pomodoroGUI) {
        super(images.frameIcon.getImage(), "Pomodoro Timer", popUpMenu);
        this.images = images;
        this.settings = settings;
        this.pomodoroGUI = pomodoroGUI;
        this.setImageAutoSize(true);
        addIfSupported();
    }

    private void addIfSupported() {
        if (SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
            try {
                systemTray.add(this);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            runWithoutTray();
        }
    }

    private void runWithoutTray() {
        pomodoroGUI.setVisible(true);
    }
}