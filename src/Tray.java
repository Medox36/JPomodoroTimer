import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tray extends TrayIcon {
    private Settings settings;
    private final PomodoroGUI pomodoroGUI;

    public Tray(PopUpMenu popUpMenu, PomodoroGUI pomodoroGUI) {
        super(Images.frameIcon.getImage(), "Pomodoro Timer", popUpMenu);
        settings = Settings.getInstance();
        this.pomodoroGUI = pomodoroGUI;
        setImageAutoSize(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    pomodoroGUI.setVisible(true);
                }
            }
        });
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
        displayMessage("Pomodoro Timer", "The Pomodoro-Application is now running!", MessageType.INFO);
    }
}