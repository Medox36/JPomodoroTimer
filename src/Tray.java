import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tray extends TrayIcon {
    private final PomodoroGUI pomodoroGUI;

    public Tray(PopUpMenu popUpMenu, PomodoroGUI pomodoroGUI) {
        super(Images.frameIcon.getImage(), "Pomodoro Timer", popUpMenu);
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
        if (SystemTray.isSupported() && Settings.getInstance().isMinimizeToTray()) {
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

    public void runWithoutTray() {
        pomodoroGUI.setVisible(true);
        pomodoroGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addTrayOnSettingsChange() {
        if (SystemTray.isSupported() && Settings.getInstance().isMinimizeToTray()) {
            try {
                SystemTray.getSystemTray().add(this);
                pomodoroGUI.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private void showTrayMessage() {
        if (!Settings.getInstance().isMuted()) {
            displayMessage("Pomodoro Timer", "The Pomodoro-Application is now running!", MessageType.INFO);
        }
    }
}