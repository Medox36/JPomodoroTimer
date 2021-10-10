import javax.swing.*;
import java.awt.*;

public class PomodoroGUI {
    private Color red, lightRed, blue, lightBlue, darkBlue, lightDarkBlue;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu settingsMenu, notifications, redTimes, blueTimes, darkBlueTimes;
    private JMenuItem saveSettings, loadSettings, noSound, bell, digital;
    private JCheckBoxMenuItem autoBreaks, autoPomodoro;
    private JTabbedPane tabbedPane;
    private JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private JLabel redTime, blueTime, darkBlueTime;
    private ImageIcon frameIcon, tomato, coffeeCup, coffeeCup2, settings, settingsSave, settingsLoad, notification;

    public PomodoroGUI() {
        red = new Color(0xdb524d);
        lightRed = new Color(0xdf645f);
        blue = new Color(0x468e91);
        lightBlue = new Color(0x599a9c);
        darkBlue = new Color(0x437ea8);
        lightDarkBlue = new Color(0x568bb1);
        frame = new JFrame();

        tabbedPane = new JTabbedPane();
        rootRed = new JPanel();
        rootBlue = new JPanel();
        rootDarkBlue = new JPanel();
        redTimer = new JPanel();
        blueTimer = new JPanel();
        darkBlueTimer = new JPanel();
        redTime = new JLabel();
        blueTime = new JLabel();
        darkBlueTime = new JLabel();
        frameIcon = new ImageIcon("images/intelligenter-timer.png");
        tomato = new ImageIcon("images/tomato.png");
        coffeeCup = new ImageIcon("images/coffee-cup.png");
        coffeeCup2 = new ImageIcon("images/coffee-cup2.png");
        settings = new ImageIcon("images/setting-lines.png");
        settingsSave = new ImageIcon("images/setting.png");
        settingsLoad = new ImageIcon("images/open-file.png");
        notification = new ImageIcon("images/notification.png");
        menuBar = new JMenuBar();
        settingsMenu = new JMenu();
        notifications = new JMenu("Notifications");
        redTimes = new JMenu("Pomodoro Time");
        blueTimes = new JMenu("Short Break Time");
        darkBlueTimes = new JMenu("Long Break Time");
        saveSettings = new JMenuItem("Save Settings", settingsSave);
        loadSettings = new JMenuItem("Load Settings", settingsLoad);
        noSound = new JMenuItem("off");
        bell = new JMenuItem("Bell");
        digital = new JMenuItem("Digital");
        autoBreaks = new JCheckBoxMenuItem("Auto start Breaks");
        autoPomodoro = new JCheckBoxMenuItem("Auto start Pomodoros");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500,600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pomodoro Timer");
        frame.setIconImage(frameIcon.getImage());

        //red parts
        rootRed.setLayout(null);
        rootRed.setBackground(red);
        rootRed.setBounds(tabbedPane.getBounds());
        rootRed.setSize(tabbedPane.getSize());

        redTime.setForeground(Color.WHITE);
        redTime.setFont(new Font("Dialog",Font.PLAIN,125));
        redTime.setText("00:00");
        redTime.setBounds(15, 25, 320, 150);

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

        blueTime.setForeground(Color.WHITE);
        blueTime.setFont(new Font("Dialog",Font.PLAIN,125));
        blueTime.setText("00:00");
        blueTime.setBounds(15, 25, 320, 150);

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

        darkBlueTime.setForeground(Color.WHITE);
        darkBlueTime.setFont(new Font("Dialog",Font.PLAIN,125));
        darkBlueTime.setText("00:00");
        darkBlueTime.setBounds(15, 25, 320, 150);

        darkBlueTimer.setBackground(lightDarkBlue);
        darkBlueTimer.setBounds(71, 50, 350, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);


        menuBar.setBackground(new Color(0xE0E0E0));

        tabbedPane.setBounds(frame.getBounds());
        tabbedPane.setSize(frame.getSize());
        tabbedPane.addTab("Pomodoro", tomato, rootRed);
        tabbedPane.addTab("Short Break", coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", coffeeCup2, rootDarkBlue);

        settingsMenu.setText("Settings");
        settingsMenu.setIcon(settings);
        settingsMenu.setIconTextGap(8);

        redTimes.setIcon(tomato);
        blueTimes.setIcon(coffeeCup);
        darkBlueTimes.setIcon(coffeeCup2);

        notifications.setIcon(notification);
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

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }


}