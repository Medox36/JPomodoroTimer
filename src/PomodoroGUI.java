import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PomodoroGUI {
    private final JFrame frame;
    private final JMenuBar menuBar;
    private final JMenu settingsMenu, notifications, redTimes, blueTimes, darkBlueTimes;
    private final JMenuItem saveSettings, loadSettings;
    private final JMenuItem red30, red25, red20, red15, redCustom;
    private final JMenuItem blue15, blue10, blue5, blueCustom;
    private final JMenuItem darkBlue20, darkBlue15, darkBlue10, darkBlueCustom;
    private final JCheckBoxMenuItem autoBreaks, autoPomodoro;
    private final JRadioButtonMenuItem noSound, bell, digital;
    private final JTabbedPane tabbedPane;
    private final JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private final PomodoroTimeLabel redTime, blueTime, darkBlueTime;
    private final ImageIcon frameIcon, tomato, coffeeCup, coffeeCup2, settings, settingsSave, settingsLoad, notification;
    private final ButtonGroup notificationGroup;
    private final ActionListener notificationListener, redTimeMenu, blueTimeMenu, darkBlueTimeMenu;

    public PomodoroGUI() {
        Color red = new Color(0xdb524d);
        Color lightRed = new Color(0xdf645f);
        Color blue = new Color(0x468e91);
        Color lightBlue = new Color(0x599a9c);
        Color darkBlue = new Color(0x437ea8);
        Color lightDarkBlue = new Color(0x568bb1);
        frame = new JFrame();
        tabbedPane = new JTabbedPane();
        rootRed = new JPanel();
        rootBlue = new JPanel();
        rootDarkBlue = new JPanel();
        redTimer = new JPanel();
        blueTimer = new JPanel();
        darkBlueTimer = new JPanel();
        redTime = new PomodoroTimeLabel("29:71");
        blueTime = new PomodoroTimeLabel("88:88");
        darkBlueTime = new PomodoroTimeLabel("01:15");
        frameIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/intelligent-timer.png")));
        tomato = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/tomato.png")));
        coffeeCup = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup.png")));
        coffeeCup2 = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup2.png")));
        settings = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting-lines.png")));
        settingsSave = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting.png")));
        settingsLoad = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/open-file.png")));
        notification = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/notification.png")));
        menuBar = new JMenuBar();
        settingsMenu = new JMenu();
        notifications = new JMenu("Notifications");
        redTimes = new JMenu("Pomodoro Time");
        blueTimes = new JMenu("Short Break Time");
        darkBlueTimes = new JMenu("Long Break Time");
        saveSettings = new JMenuItem("Save Settings", settingsSave);
        loadSettings = new JMenuItem("Load Settings", settingsLoad);
        noSound = new JRadioButtonMenuItem("off");
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
        };
        redTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "30:00", "25:00", "20:00", "15:00" -> redTime.setTime(str);
                case "custom" -> setRedTimeWithFrame();
            }
        };
        blueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "15:00", "10:00", "05:00" -> blueTime.setTime(str);
                case "custom" -> setBlueTimeWithFrame();
            }
        };
        darkBlueTimeMenu = e -> {
            String str = e.getActionCommand();
            switch (str) {
                case "20:00", "15:00", "10:00" -> blueTime.setTime(str);
                case "custom" -> setDarkBlueTimeWithFrame();
            }
        };


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pomodoro Timer");
        frame.setIconImage(frameIcon.getImage());

        //red parts
        rootRed.setLayout(null);
        rootRed.setBackground(red);
        rootRed.setBounds(tabbedPane.getBounds());
        rootRed.setSize(tabbedPane.getSize());

        redTimer.setBackground(lightRed);
        redTimer.setBounds(71, 50, 350, 200);
        redTimer.setLayout(null);

        redTimer.add(redTime, BorderLayout.CENTER);
        rootRed.add(redTimer);

        //blue parts
        rootBlue.setLayout(null);
        rootBlue.setBackground(blue);
        rootBlue.setBounds(tabbedPane.getBounds());
        rootBlue.setSize(tabbedPane.getSize());

        blueTimer.setBackground(lightBlue);
        blueTimer.setBounds(71, 50, 350, 200);
        blueTimer.setLayout(null);

        blueTimer.add(blueTime);
        rootBlue.add(blueTimer);

        //darkBlue parts
        rootDarkBlue.setLayout(null);
        rootDarkBlue.setBackground(darkBlue);
        rootDarkBlue.setBounds(tabbedPane.getBounds());
        rootDarkBlue.setSize(tabbedPane.getSize());

        darkBlueTimer.setBackground(lightDarkBlue);
        darkBlueTimer.setBounds(71, 50, 350, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);


        //tabbedPane-stuff
        tabbedPane.setBounds(frame.getBounds());
        tabbedPane.setSize(frame.getSize());
        tabbedPane.addTab("Pomodoro", tomato, rootRed);
        tabbedPane.addTab("Short Break", coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", coffeeCup2, rootDarkBlue);

        //menu-stuff
        menuBar.setBackground(new Color(0xE0E0E0));

        settingsMenu.setText("Settings");
        settingsMenu.setIcon(settings);
        settingsMenu.setIconTextGap(8);

        red30.setActionCommand("30:00");
        red25.setActionCommand("25:00");
        red20.setActionCommand("20:00");
        red15.setActionCommand("15:00");
        redCustom.setActionCommand("custom");
        redCustom.addActionListener(redTimeMenu);

        blue15.setActionCommand("15:00");
        blue10.setActionCommand("10:00");
        blue5.setActionCommand("05:00");
        blueCustom.setActionCommand("custom");
        blueCustom.addActionListener(blueTimeMenu);

        darkBlue20.setActionCommand("20:00");
        darkBlue15.setActionCommand("15:00");
        darkBlue10.setActionCommand("10:00");
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

        noSound.addActionListener(notificationListener);
        noSound.setActionCommand("off");
        bell.addActionListener(notificationListener);
        bell.setActionCommand("bell");
        digital.addActionListener(notificationListener);
        digital.setActionCommand("digital");

        notifications.setIcon(notification);
        notificationGroup = new ButtonGroup();
        notificationGroup.add(noSound);
        notificationGroup.add(bell);
        notificationGroup.add(digital);
        notifications.add(noSound);
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

        menuBar.add(settingsMenu);


        //finish frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }

    private void setRedTimeWithFrame() {
        stopActiveTimers();
        CustomTime customTime = new CustomTime("Custom Time", redTime, tomato);
        customTime.setLocationRelativeTo(frame);
    }

    private void setBlueTimeWithFrame() {
        stopActiveTimers();
        CustomTime customTime = new CustomTime("Custom Time", blueTime, coffeeCup);
        customTime.setLocationRelativeTo(frame);
    }

    private void setDarkBlueTimeWithFrame() {
        stopActiveTimers();
        CustomTime customTime = new CustomTime("Custom Time", darkBlueTime, coffeeCup2);
        customTime.setLocationRelativeTo(frame);
    }

    private void stopActiveTimers() {

    }

    public static void resumeStoppedTimer() {

    }
}