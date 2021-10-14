import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PomodoroMenu extends JMenuBar {
    private final PomodoroGUI pom;
    private final JMenu settingsMenu, notifications, redTimes, blueTimes, darkBlueTimes;
    private final JMenuItem saveSettings, loadSettings;
    private final JMenuItem red30, red25, red20, red15, redCustom;
    private final JMenuItem blue15, blue10, blue5, blueCustom;
    private final JMenuItem darkBlue20, darkBlue15, darkBlue10, darkBlueCustom;
    private final JCheckBoxMenuItem autoBreaks, autoPomodoro;
    private final JRadioButtonMenuItem off, bell, digital;
    private final ButtonGroup notificationGroup;
    private final ImageIcon settingsSave, settingsLoad, notification, settingsIcon;
    private final ActionListener notificationListener, redTimeMenu, blueTimeMenu, darkBlueTimeMenu;

    public PomodoroMenu(PomodoroGUI pomodoroGUI, ImageIcon tomato, ImageIcon coffeeCup, ImageIcon coffeeCup2) {
        this.pom = pomodoroGUI;
        settingsSave = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting.png")));
        settingsLoad = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/open-file.png")));
        notification = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/notification.png")));
        settingsIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting-lines.png")));
        settingsMenu = new JMenu();
        notifications = new JMenu("Notifications");
        redTimes = new JMenu("Pomodoro Time");
        blueTimes = new JMenu("Short Break Time");
        darkBlueTimes = new JMenu("Long Break Time");
        saveSettings = new JMenuItem("Save Settings", settingsSave);
        loadSettings = new JMenuItem("Load Settings", settingsLoad);
        off = new JRadioButtonMenuItem("off");
        bell = new JRadioButtonMenuItem("Bell");
        digital = new JRadioButtonMenuItem("Digital");
        red30 = new JMenuItem("30 min");
        red25 = new JMenuItem("25 min");
        red20 = new JMenuItem("20 min");
        red15 = new JMenuItem("15 min");
        redCustom = new JMenuItem("Custom Time");
        blue15 = new JMenuItem("15 min");
        blue10 = new JMenuItem("10 min");
        blue5 = new JMenuItem("5 min");
        blueCustom = new JMenuItem("Custom Time");
        darkBlue20 = new JMenuItem("20 min");
        darkBlue15 = new JMenuItem("15 min");
        darkBlue10 = new JMenuItem("10 min");
        darkBlueCustom = new JMenuItem("Custom Time");
        autoBreaks = new JCheckBoxMenuItem("Auto start Breaks");
        autoPomodoro = new JCheckBoxMenuItem("Auto start Pomodoros");

        notificationListener = e -> {
            System.out.println(e.getSource());
            System.out.println("\t" + e.getActionCommand());
            //TODO evaluate the Event
        };
        redTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "30:00", "25:00", "20:00", "15:00" -> pom.getRedTimeLabel().setTime(str);
                case "custom" -> pom.setRedTimeWithFrame();
            }
        };
        blueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "15:00", "10:00", "05:00" -> pom.getBlueTimeLabel().setTime(str);
                case "custom" -> pom.setBlueTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        darkBlueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "20:00", "15:00", "10:00" -> pom.getDarkBlueTimeLabel().setTime(str);
                case "custom" -> pom.setDarkBlueTimeWithFrame();
            }
        };

        settingsMenu.setText("Settings");
        settingsMenu.setIcon(settingsIcon);
        settingsMenu.setIconTextGap(8);

        red30.setActionCommand("30:00");
        red30.addActionListener(redTimeMenu);
        red25.setActionCommand("25:00");
        red25.addActionListener(redTimeMenu);
        red20.setActionCommand("20:00");
        red20.addActionListener(redTimeMenu);
        red15.setActionCommand("15:00");
        red15.addActionListener(redTimeMenu);
        redCustom.setActionCommand("custom");
        redCustom.addActionListener(redTimeMenu);

        blue15.setActionCommand("15:00");
        blue15.addActionListener(blueTimeMenu);
        blue10.setActionCommand("10:00");
        blue10.addActionListener(blueTimeMenu);
        blue5.setActionCommand("05:00");
        blue5.addActionListener(blueTimeMenu);
        blueCustom.setActionCommand("custom");
        blueCustom.addActionListener(blueTimeMenu);

        darkBlue20.setActionCommand("20:00");
        darkBlue20.addActionListener(darkBlueTimeMenu);
        darkBlue15.setActionCommand("15:00");
        darkBlue15.addActionListener(darkBlueTimeMenu);
        darkBlue10.setActionCommand("10:00");
        darkBlue10.addActionListener(darkBlueTimeMenu);
        darkBlueCustom.setActionCommand("custom");
        darkBlueCustom.addActionListener(darkBlueTimeMenu);

        redTimes.setIcon(tomato);
        redTimes.add(red30);
        redTimes.add(red25);
        redTimes.add(red20);
        redTimes.add(red15);
        redTimes.add(redCustom);

        blueTimes.setIcon(coffeeCup);
        blueTimes.add(blue15);
        blueTimes.add(blue10);
        blueTimes.add(blue5);
        blueTimes.add(blueCustom);

        darkBlueTimes.setIcon(coffeeCup2);
        darkBlueTimes.add(darkBlue20);
        darkBlueTimes.add(darkBlue15);
        darkBlueTimes.add(darkBlue10);
        darkBlueTimes.add(darkBlueCustom);

        off.addActionListener(notificationListener);
        off.setActionCommand("off");
        bell.addActionListener(notificationListener);
        bell.setActionCommand("bell");
        digital.addActionListener(notificationListener);
        digital.setActionCommand("digital");

        notifications.setIcon(notification);
        notificationGroup = new ButtonGroup();
        notificationGroup.add(off);
        notificationGroup.add(bell);
        notificationGroup.add(digital);
        notifications.add(off);
        notifications.add(bell);
        notifications.add(digital);

        settingsMenu.add(autoBreaks);
        settingsMenu.add(autoPomodoro);
        settingsMenu.addSeparator();
        settingsMenu.add(redTimes);
        settingsMenu.add(blueTimes);
        settingsMenu.add(darkBlueTimes);
        settingsMenu.addSeparator();
        settingsMenu.add(notifications);
        settingsMenu.addSeparator();
        settingsMenu.add(saveSettings);
        settingsMenu.add(loadSettings);

        //checking Checkboxes if needed
        autoBreaks.setSelected(pomodoroGUI.isAutoBreaks());
        autoPomodoro.setSelected(pomodoroGUI.isAutoPomodoros());

        //select the right radiobutton
        selectRadioButton();

        this.setBackground(new Color(0xE0E0E0));
        this.add(settingsMenu);
    }

    private void selectRadioButton() {
        switch (pom.getNotifications()) {
            case "off" -> off.setSelected(true);
            case "bell" -> bell.setSelected(true);
            case "digital" -> digital.setSelected(true);
        }
    }

}