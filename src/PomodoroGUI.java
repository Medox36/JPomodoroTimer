import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PomodoroGUI {
    private Color red, lightRed, blue, lightBlue, darkBlue, lightDarkBlue;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu settingsMenu, notifications, redTimes, blueTimes, darkBlueTimes;
    private JMenuItem saveSettings, loadSettings;
    private JMenuItem red30, red25, red20, red15, redCustom;
    private JMenuItem blue15, blue10, blue5, blueCustom;
    private JMenuItem darkBlue20, darkBlue15, darkBlue10, darkBlueCustom;
    private JCheckBoxMenuItem autoBreaks, autoPomodoro;
    private JRadioButtonMenuItem noSound, bell, digital;
    private JTabbedPane tabbedPane;
    private JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private JLabel redTime, blueTime, darkBlueTime;
    private ImageIcon frameIcon, tomato, coffeeCup, coffeeCup2, settings, settingsSave, settingsLoad, notification;
    private ButtonGroup notificationGroup;
    private ActionListener notificationListener;

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
        redTime.setFont(new Font("Gadugi", Font.PLAIN,130));
        redTime.setText("29:71");
        redTime.setBounds(20, 15, 320, 150);

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
        blueTime.setFont(new Font("Gadugi", Font.PLAIN,130));
        blueTime.setText("88:88");
        blueTime.setBounds(20, 15, 320, 150);

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
        darkBlueTime.setFont(new Font("Gadugi", Font.PLAIN,130));
        darkBlueTime.setText("18:27");
        darkBlueTime.setBounds(20, 15, 320, 150);

        darkBlueTimer.setBackground(lightDarkBlue);
        darkBlueTimer.setBounds(71, 50, 350, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);



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


        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }


}