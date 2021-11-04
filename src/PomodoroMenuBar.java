import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PomodoroMenuBar extends JMenuBar {
    private final Settings settings;
    private final PomodoroGUI pomodoroGUI;
    private final JMenu settingsMenu, notifications, redTimes, blueTimes, darkBlueTimes;
    private final JMenuItem saveSettings, loadSettings;
    private final JMenuItem red30, red25, red20, red15, redCustom;
    private final JMenuItem blue15, blue10, blue5, blueCustom;
    private final JMenuItem darkBlue20, darkBlue15, darkBlue10, darkBlueCustom;
    private final JCheckBoxMenuItem autoBreaks, autoPomodoro;
    private final JRadioButtonMenuItem off, bell, digital;
    private final ButtonGroup notificationGroup;
    private final ActionListener notificationListener, redTimeMenu, blueTimeMenu, darkBlueTimeMenu, saveSettingsListener, loadSettingsListener;

    public PomodoroMenuBar(PomodoroGUI pomodoroGUI, Settings settings, Images images) {
        this.pomodoroGUI = pomodoroGUI;
        this.settings = settings;
        settingsMenu = new JMenu();
        notifications = new JMenu("Notifications");
        redTimes = new JMenu("Pomodoro Time");
        blueTimes = new JMenu("Short Break Time");
        darkBlueTimes = new JMenu("Long Break Time");
        saveSettings = new JMenuItem("Save Settings", images.settingsSave);
        loadSettings = new JMenuItem("Load Settings", images.settingsLoad);
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
                case "30:00", "25:00", "20:00", "15:00" -> this.pomodoroGUI.getRedTimeLabel().setTime(str);
                case "custom" -> this.pomodoroGUI.setRedTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        blueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "15:00", "10:00", "05:00" -> this.pomodoroGUI.getBlueTimeLabel().setTime(str);
                case "custom" -> this.pomodoroGUI.setBlueTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        darkBlueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "20:00", "15:00", "10:00" -> this.pomodoroGUI.getDarkBlueTimeLabel().setTime(str);
                case "custom" -> this.pomodoroGUI.setDarkBlueTimeWithFrame();
                default -> throw new IllegalStateException("Unexpected value: " + str);
            }
        };
        saveSettingsListener = e -> {

        };
        loadSettingsListener = e -> {
            URI uri = null;
            try {
                uri = new URI(PomodoroMenuBar.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            JFileChooser fileChooser = new JFileChooser(Objects.requireNonNull(uri).toString());

            System.out.println();
            System.out.println(PomodoroMenuBar.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            Objects.requireNonNull(fileChooser).setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.getName().endsWith(".txt") || f.isDirectory());
                }

                @Override
                public String getDescription() {
                    return null;
                }
            });
            Action details = fileChooser.getActionMap().get("viewTypeDetails");
            details.actionPerformed(null);
            int val = fileChooser.showOpenDialog(this.pomodoroGUI);
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
            }
        };

        settingsMenu.setText("Settings");
        settingsMenu.setIcon(images.settingsIcon);
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

        redTimes.setIcon(images.tomato);
        redTimes.add(red30);
        redTimes.add(red25);
        redTimes.add(red20);
        redTimes.add(red15);
        redTimes.add(redCustom);

        blueTimes.setIcon(images.coffeeCup);
        blueTimes.add(blue15);
        blueTimes.add(blue10);
        blueTimes.add(blue5);
        blueTimes.add(blueCustom);

        darkBlueTimes.setIcon(images.coffeeCup2);
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

        notifications.setIcon(images.notification);
        notificationGroup = new ButtonGroup();
        notificationGroup.add(off);
        notificationGroup.add(bell);
        notificationGroup.add(digital);
        notifications.add(off);
        notifications.add(bell);
        notifications.add(digital);

        saveSettings.addActionListener(saveSettingsListener);
        loadSettings.addActionListener(loadSettingsListener);

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
        autoBreaks.setSelected(settings.isAutoBreaks());
        autoPomodoro.setSelected(settings.isAutoPomodoros());

        //select the right radiobutton
        selectRadioButton();

        this.setBackground(new Color(0xE0E0E0));
        this.add(settingsMenu);
    }

    private void selectRadioButton() {
        switch (settings.getNotifications()) {
            case "off" -> off.setSelected(true);
            case "bell" -> bell.setSelected(true);
            case "digital" -> digital.setSelected(true);
            default -> throw new IllegalStateException("Unexpected value: " + settings.getNotifications());
        }
    }

}