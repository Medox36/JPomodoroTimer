import javax.swing.*;
import java.awt.*;

public class Tray extends TrayIcon {
    private Images images;
    private Settings settings;
    private PomodoroGUI pomodoroGUI;

    public Tray(Images images, Settings settings, PopUpMenu popUpMenu, PomodoroGUI pomodoroGUI) {
        super(images.frameIcon.getImage(), "Pomodoro Timer", popUpMenu);
        this.images = images;
        this.settings = settings;
        this.pomodoroGUI = pomodoroGUI;
        this.setImageAutoSize(true);
        addIfTrayIsSupported();
    }

    private void addIfTrayIsSupported() {
        if (SystemTray.isSupported()) {
            try {
                SystemTray.getSystemTray().add(this);
                showTrayMessage();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        } else {
            runWithoutTray();
        }
    }

    private void runWithoutTray() {
        pomodoroGUI.setVisible(true);
        pomodoroGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showTrayMessage() {
        displayMessage("Pomodoro Timer", "The Pomodora-Application is now running!", MessageType.INFO);
    }
}