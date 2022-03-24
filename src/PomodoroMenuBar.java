import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class PomodoroMenuBar extends JMenuBar {
    private final Settings settings;
    private final PomodoroGUI pomodoroGUI;
    private final JRadioButtonMenuItem off, bell, digital;
    private final JCheckBoxMenuItem autoBreaks;
    private final JCheckBoxMenuItem autoPomodoro;

    public PomodoroMenuBar(PomodoroGUI pomodoroGUI) {
        this.pomodoroGUI = pomodoroGUI;
        settings = Settings.getInstance();
        JMenu settingsMenu = new JMenu();
        JMenu notifications = new JMenu("Notifications");
        JMenu redTimes = new JMenu("Pomodoro Time");
        JMenu blueTimes = new JMenu("Short Break Time");
        JMenu darkBlueTimes = new JMenu("Long Break Time");
        JMenuItem breakInterval = new JMenuItem("Long Break Interval");
        JMenuItem saveSettings = new JMenuItem("Save Settings", Images.settingsSave);
        JMenuItem loadSettings = new JMenuItem("Load Settings", Images.settingsLoad);
        off = new JRadioButtonMenuItem("off");
        bell = new JRadioButtonMenuItem("Bell");
        digital = new JRadioButtonMenuItem("Digital");
        JMenuItem red30 = new JMenuItem("30 min");
        JMenuItem red25 = new JMenuItem("25 min");
        JMenuItem red20 = new JMenuItem("20 min");
        JMenuItem red15 = new JMenuItem("15 min");
        JMenuItem redCustom = new JMenuItem("Custom Time");
        JMenuItem blue15 = new JMenuItem("15 min");
        JMenuItem blue10 = new JMenuItem("10 min");
        JMenuItem blue5 = new JMenuItem("5 min");
        JMenuItem blueCustom = new JMenuItem("Custom Time");
        JMenuItem darkBlue20 = new JMenuItem("20 min");
        JMenuItem darkBlue15 = new JMenuItem("15 min");
        JMenuItem darkBlue10 = new JMenuItem("10 min");
        JMenuItem darkBlueCustom = new JMenuItem("Custom Time");
        autoBreaks = new JCheckBoxMenuItem("Auto start Breaks");
        autoPomodoro = new JCheckBoxMenuItem("Auto start Pomodoros");

        ActionListener notificationListener = e -> {
            if (e.getSource().equals(off) || e.getSource().equals(bell) || e.getSource().equals(digital)) {
                settings.setNotifications(e.getActionCommand());
            } else if (e.getSource().equals(autoBreaks)) {
                settings.setAutoBreaks(autoBreaks.getState());
            } else if (e.getSource().equals(autoPomodoro)) {
                settings.setAutoPomodoros(autoPomodoro.getState());
            }
        };
        ActionListener redTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "30:00", "25:00", "20:00", "15:00" -> pomodoroGUI.getRedTimeLabel().setTime(str);
                case "custom" -> pomodoroGUI.setRedTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        ActionListener blueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "15:00", "10:00", "05:00" -> pomodoroGUI.getBlueTimeLabel().setTime(str);
                case "custom" -> pomodoroGUI.setBlueTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        ActionListener darkBlueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "20:00", "15:00", "10:00" -> pomodoroGUI.getDarkBlueTimeLabel().setTime(str);
                case "custom" -> pomodoroGUI.setDarkBlueTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        ActionListener breakIntervalListener = e -> new CustomNumber();
        ActionListener saveSettingsListener = e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
            }
            URI uri = null;
            try {
                uri = new URI(PomodoroMenuBar.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            JFileChooser fileChooser = new JFileChooser(Objects.requireNonNull(uri).getPath());
            fileChooser.setSelectedFile(new File("settings.txt"));
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileHidingEnabled(true);
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.getName().endsWith(".txt") || f.isDirectory());
                }

                @Override
                public String getDescription() {
                    return "Textfiles (*.txt)";
                }
            });
            Action details = fileChooser.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int val = fileChooser.showSaveDialog(this.pomodoroGUI);
            if (val == JFileChooser.APPROVE_OPTION) {
                try {
                    settings.saveContentsToCustomFile(fileChooser.getSelectedFile());
                    //TODO show a Dialog
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
        ActionListener loadSettingsListener = e -> {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
                ex.printStackTrace();
            }
            URI uri = null;
            try {
                uri = new URI(PomodoroMenuBar.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            JFileChooser fileChooser = new JFileChooser(Objects.requireNonNull(uri).getPath());
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileHidingEnabled(true);
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.getName().endsWith(".txt") || f.isDirectory());
                }

                @Override
                public String getDescription() {
                    return "Textfiles (*.txt)";
                }
            });
            Action details = fileChooser.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int val = fileChooser.showOpenDialog(this.pomodoroGUI);
            if (val == JFileChooser.APPROVE_OPTION) {
                try {
                    settings.loadContentsFromCustomFile(fileChooser.getSelectedFile());
                    pomodoroGUI.reloadSettings();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        settingsMenu.setText("Settings");
        settingsMenu.setIcon(Images.settingsIcon);
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

        redTimes.setIcon(Images.tomato);
        redTimes.add(red30);
        redTimes.add(red25);
        redTimes.add(red20);
        redTimes.add(red15);
        redTimes.add(redCustom);

        blueTimes.setIcon(Images.coffeeCup);
        blueTimes.add(blue15);
        blueTimes.add(blue10);
        blueTimes.add(blue5);
        blueTimes.add(blueCustom);

        darkBlueTimes.setIcon(Images.coffeeCup2);
        darkBlueTimes.add(darkBlue20);
        darkBlueTimes.add(darkBlue15);
        darkBlueTimes.add(darkBlue10);
        darkBlueTimes.add(darkBlueCustom);

        breakInterval.addActionListener(breakIntervalListener);

        off.addActionListener(notificationListener);
        off.setActionCommand("off");
        bell.addActionListener(notificationListener);
        bell.setActionCommand("bell");
        digital.addActionListener(notificationListener);
        digital.setActionCommand("digital");

        notifications.setIcon(Images.notification);
        ButtonGroup notificationGroup = new ButtonGroup();
        notificationGroup.add(off);
        notificationGroup.add(bell);
        notificationGroup.add(digital);
        notifications.add(off);
        notifications.add(bell);
        notifications.add(digital);

        saveSettings.addActionListener(saveSettingsListener);
        loadSettings.addActionListener(loadSettingsListener);

        autoBreaks.addActionListener(notificationListener);
        autoPomodoro.addActionListener(notificationListener);

        settingsMenu.add(autoBreaks);
        settingsMenu.add(autoPomodoro);
        settingsMenu.addSeparator();
        settingsMenu.add(redTimes);
        settingsMenu.add(blueTimes);
        settingsMenu.add(darkBlueTimes);
        settingsMenu.add(breakInterval);
        settingsMenu.addSeparator();
        settingsMenu.add(notifications);
        settingsMenu.addSeparator();
        settingsMenu.add(saveSettings);
        settingsMenu.add(loadSettings);

        settingsMenu.setOpaque(true);
        settingsMenu.setBackground(new Color(218, 206, 206));

        //checking Checkboxes if needed
        autoBreaks.setSelected(settings.isAutoBreaks());
        autoPomodoro.setSelected(settings.isAutoPomodoros());

        //select the right radiobutton
        selectRadioButton();

        setOpaque(true);
        setBackground(new Color(0xD3D3D3));
        add(settingsMenu);
    }

    private void selectRadioButton() {
        switch (settings.getNotifications()) {
            case "off" -> off.setSelected(true);
            case "bell" -> bell.setSelected(true);
            case "digital" -> digital.setSelected(true);
            default -> throw new IllegalStateException("Unexpected value: " + settings.getNotifications());
        }
    }

    public void setAutoBreaks(boolean sel) {
        autoBreaks.setSelected(sel);
    }

    public void setAutoPomodoro(boolean sel) {
        autoPomodoro.setSelected(sel);
    }
}