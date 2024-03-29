import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PopUpMenu extends PopupMenu {
    private final MenuItem open, setting, exit;
    private final CheckboxMenuItem notifications;

    public PopUpMenu(PomodoroGUI pomodoroGUI) {
        super();
        open = new MenuItem("open");
        setting = new MenuItem("settings");
        notifications = new CheckboxMenuItem("turn off notifications");
        notifications.setState(Settings.getInstance().isMuted());
        exit = new MenuItem("exit");
        ActionListener actionListener = e -> {
            if (e.getSource().equals(open)) {
                if (pomodoroGUI.isShowing()) {
                    pomodoroGUI.setState(JFrame.NORMAL);
                } else {
                    pomodoroGUI.setVisible(true);
                }
            } else if (e.getSource().equals(setting)) {
                if (pomodoroGUI.isShowing()) {
                    pomodoroGUI.setState(JFrame.NORMAL);
                } else {
                    pomodoroGUI.setVisible(true);
                }
                pomodoroGUI.getBar().getMenu(0).doClick();
            } else if (e.getSource().equals(notifications)) {
                Settings.getInstance().setMuted(((CheckboxMenuItem) e.getSource()).getState());
            } else if (e.getSource().equals(exit)) {
                try {
                    Settings.getInstance().saveToStandardFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
        notifications.addActionListener(actionListener);
        exit.addActionListener(actionListener);
        add(open);
        add(setting);
        addSeparator();
        add(notifications);
        addSeparator();
        add(exit);

        pomodoroGUI.setPopUpMenu(this);
    }

    public void setMute(boolean state) {
        this.notifications.setState(state);
    }
}