import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class PopUpMenu extends PopupMenu {
    private final MenuItem open, setting,exit;
    private final CheckboxMenuItem notifications;
    private final ActionListener actionListener;

    public PopUpMenu(Settings settings, PomodoroGUI pomodoroGUI) {
        super();
        open = new MenuItem("open");
        setting = new MenuItem("settings");
        notifications = new CheckboxMenuItem("turn off notifications");
        notifications.setState(Objects.equals(settings.getNotifications(), "off"));
        exit = new MenuItem("exit");
        actionListener = e -> {
            if (open.equals(e.getSource())) {
                if (pomodoroGUI.isShowing()) {
                    pomodoroGUI.setState(JFrame.NORMAL);
                } else {
                    pomodoroGUI.setVisible(true);
                }
            } else if (setting.equals(e.getSource())) {
                if (pomodoroGUI.isShowing()) {
                    pomodoroGUI.setState(JFrame.NORMAL);
                } else {
                    pomodoroGUI.setVisible(true);
                }
                pomodoroGUI.getBar().getMenu(0).doClick();
            } else if (exit.equals(e.getSource())) {
                //TODO save settings and close
                pomodoroGUI.dispatchEvent(new WindowEvent(pomodoroGUI, WindowEvent.WINDOW_CLOSING));
                pomodoroGUI.setVisible(false);
                pomodoroGUI.dispose();
                TrayIcon[] trayIcons = SystemTray.getSystemTray().getTrayIcons();
                for (TrayIcon trayIcon : trayIcons) {
                    if (trayIcon.equals(Start.getTrayIcon())) {
                        SystemTray.getSystemTray().remove(trayIcon);
                        break;
                    }
                }
            }
        };
        open.addActionListener(actionListener);
        setting.addActionListener(actionListener);
        exit.addActionListener(actionListener);
        this.add(open);
        this.add(setting);
        this.addSeparator();
        this.add(notifications);
        this.addSeparator();
        this.add(exit);
    }
}