import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PomodoroGUI extends Settings{
    private final JFrame frame;
    private final PomodoroMenu menuBar;
    private final JTabbedPane tabbedPane;
    private final JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private final PomodoroTimeLabel redTime, blueTime, darkBlueTime;
    private final ImageIcon frameIcon, tomato, coffeeCup, coffeeCup2;
    private final StartStopButton redButton, blueButton, darkBlueButton;
    private final TimerManagement timerManagement;
    private TrayIcon trayIcon;

    public PomodoroGUI() {
        super();
        frame = new JFrame();
        tabbedPane = new JTabbedPane();
        rootRed = new JPanel();
        rootBlue = new JPanel();
        rootDarkBlue = new JPanel();
        redTimer = new JPanel();
        blueTimer = new JPanel();
        darkBlueTimer = new JPanel();
        redTime = new PomodoroTimeLabel(getRedTime(), "red");
        blueTime = new PomodoroTimeLabel(getBlueTime(), "blue");
        darkBlueTime = new PomodoroTimeLabel(getDarkBlueTime(), "darkblue");
        frameIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/intelligent-timer.png")));
        tomato = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/tomato.png")));
        coffeeCup = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup.png")));
        coffeeCup2 = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup2.png")));
        menuBar = new PomodoroMenu(this, tomato, coffeeCup, coffeeCup2);
        timerManagement = new TimerManagement(redTime, blueTime, darkBlueTime);
        redButton = new StartStopButton();
        blueButton = new StartStopButton();
        darkBlueButton = new StartStopButton();
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(frameIcon.getImage(), "Pomodoro Timer");
            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pomodoro Timer");
        frame.setIconImage(frameIcon.getImage());

        //red parts
        rootRed.setLayout(null);
        rootRed.setBackground(new Color(0xdb524d));
        rootRed.setBounds(tabbedPane.getBounds());
        rootRed.setSize(tabbedPane.getSize());

        redTimer.setBackground(new Color(0xdf645f));
        redTimer.setBounds(71, 50, 350, 200);
        redTimer.setLayout(null);

        redTimer.add(redTime, BorderLayout.CENTER);
        rootRed.add(redTimer);
        rootRed.add(redButton);

        //blue parts
        rootBlue.setLayout(null);
        rootBlue.setBackground(new Color(0x468e91));
        rootBlue.setBounds(tabbedPane.getBounds());
        rootBlue.setSize(tabbedPane.getSize());

        blueTimer.setBackground(new Color(0x599a9c));
        blueTimer.setBounds(71, 50, 350, 200);
        blueTimer.setLayout(null);

        blueTimer.add(blueTime);
        rootBlue.add(blueTimer);
        rootBlue.add(blueButton);

        //darkBlue parts
        rootDarkBlue.setLayout(null);
        rootDarkBlue.setBackground(new Color(0x437ea8));
        rootDarkBlue.setBounds(tabbedPane.getBounds());
        rootDarkBlue.setSize(tabbedPane.getSize());

        darkBlueTimer.setBackground(new Color(0x568bb1));
        darkBlueTimer.setBounds(71, 50, 350, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);
        rootDarkBlue.add(darkBlueButton);

        //tabbedPane-stuff
        tabbedPane.setBounds(frame.getBounds());
        tabbedPane.setSize(frame.getSize());
        tabbedPane.addTab("Pomodoro", tomato, rootRed);
        tabbedPane.addTab("Short Break", coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", coffeeCup2, rootDarkBlue);
        tabbedPane.addChangeListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();
            System.out.println(selectedIndex);
        });

        //finish frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);

    }

    public void setRedTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", redTime, tomato, this);
        customTime.setLocationRelativeTo(frame);
    }

    public void setBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", blueTime, coffeeCup, this);
        customTime.setLocationRelativeTo(frame);
    }

    public void setDarkBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", darkBlueTime, coffeeCup2, this);
        customTime.setLocationRelativeTo(frame);
    }

    private void stopActiveTimer() {
        timerManagement.stopActiveTimer();
    }

    public void resumeStoppedTimer() {
        timerManagement.resumeActiveTimer();
    }

    public PomodoroTimeLabel getRedTimeLabel() {
        return redTime;
    }

    public PomodoroTimeLabel getBlueTimeLabel() {
        return blueTime;
    }

    public PomodoroTimeLabel getDarkBlueTimeLabel() {
        return darkBlueTime;
    }
}