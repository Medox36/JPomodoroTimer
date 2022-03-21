import javax.swing.*;
import java.awt.*;

public class PomodoroGUI extends JFrame{
    private final PomodoroMenuBar menuBar;
    private final JTabbedPane tabbedPane;
    private final JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private final PomodoroTimeLabel redTime, blueTime, darkBlueTime;
    private final Images images;
    private final Settings settings;
    private final StartStopButton redButton, blueButton, darkBlueButton;
    private final TimerManagement timerManagement;

    public PomodoroGUI(Images images, Settings settings) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.images = images;
        this.settings = settings;

        tabbedPane = new JTabbedPane();
        rootRed = new JPanel();
        rootBlue = new JPanel();
        rootDarkBlue = new JPanel();
        redTimer = new JPanel();
        blueTimer = new JPanel();
        darkBlueTimer = new JPanel();
        redTime = new PomodoroTimeLabel(settings.getRedTime(), "red");
        blueTime = new PomodoroTimeLabel(settings.getBlueTime(), "blue");
        darkBlueTime = new PomodoroTimeLabel(settings.getDarkBlueTime(), "darkblue");
        menuBar = new PomodoroMenuBar(this, settings, images);
        timerManagement = new TimerManagement(redTime, blueTime, darkBlueTime);
        redButton = new StartStopButton(timerManagement, timerManagement.getRedTimer());
        blueButton = new StartStopButton(timerManagement, timerManagement.getBlueTimer());
        darkBlueButton = new StartStopButton(timerManagement, timerManagement.getDarkBlueTimer());

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Pomodoro Timer");
        this.setIconImage(images.frameIcon.getImage());

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
        tabbedPane.setBounds(this.getBounds());
        tabbedPane.setSize(this.getSize());
        tabbedPane.addTab("Pomodoro", images.tomato, rootRed);
        tabbedPane.addTab("Short Break", images.coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", images.coffeeCup2, rootDarkBlue);
        tabbedPane.addChangeListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();
            //System.out.println(selectedIndex);
        });

        //finish frame
        this.setJMenuBar(menuBar);
        this.add(tabbedPane);
    }

    public void setRedTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", redTime, images.tomato, this);
        customTime.setLocationRelativeTo(this);
    }

    public void setBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", blueTime, images.coffeeCup, this);
        customTime.setLocationRelativeTo(this);
    }

    public void setDarkBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", darkBlueTime, images.coffeeCup2, this);
        customTime.setLocationRelativeTo(this);
    }

    private void stopActiveTimer() {
        timerManagement.stopActiveTimer();
    }

    public void resumeStoppedTimer() {
        timerManagement.resumeActiveTimer();
    }

    public void updateSettings() {

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

    public PomodoroMenuBar getBar() {
        return menuBar;
    }
}