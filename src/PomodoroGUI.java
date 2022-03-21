import javax.swing.*;
import java.awt.*;

public class PomodoroGUI extends JFrame{
    private final PomodoroMenuBar menuBar;
    private final PomodoroTimeLabel redTime, blueTime, darkBlueTime;
    private final Images images;
    private final Settings settings;
    private final TimerManagement timerManagement;

    public PomodoroGUI(Images images) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        this.images = images;
        settings = Settings.getInstance();

        JTabbedPane tabbedPane1 = new JTabbedPane();
        JPanel rootRed = new JPanel();
        JPanel rootBlue = new JPanel();
        JPanel rootDarkBlue = new JPanel();
        JPanel redTimer = new JPanel();
        JPanel blueTimer = new JPanel();
        JPanel darkBlueTimer = new JPanel();
        redTime = new PomodoroTimeLabel(settings.getRedTime(), "red");
        blueTime = new PomodoroTimeLabel(settings.getBlueTime(), "blue");
        darkBlueTime = new PomodoroTimeLabel(settings.getDarkBlueTime(), "darkblue");
        menuBar = new PomodoroMenuBar(this, images);
        timerManagement = new TimerManagement(redTime, blueTime, darkBlueTime);
        StartStopButton redButton = new StartStopButton(timerManagement, timerManagement.getRedTimer());
        StartStopButton blueButton = new StartStopButton(timerManagement, timerManagement.getBlueTimer());
        StartStopButton darkBlueButton = new StartStopButton(timerManagement, timerManagement.getDarkBlueTimer());
        ResetButton redResetButton = new ResetButton(redTime, 0);
        ResetButton blueResetButton = new ResetButton(blueTime, 1);
        ResetButton darkBlueResetButton = new ResetButton(darkBlueTime, 2);

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setTitle("Pomodoro Timer");
        setIconImage(images.frameIcon.getImage());

        //red parts
        rootRed.setLayout(null);
        rootRed.setBackground(new Color(0xdb524d));
        rootRed.setBounds(tabbedPane1.getBounds());
        rootRed.setSize(tabbedPane1.getSize());

        redTimer.setBackground(new Color(0xdf645f));
        redTimer.setBounds(71, 50, 350, 200);
        redTimer.setLayout(null);

        redTimer.add(redTime, BorderLayout.CENTER);
        rootRed.add(redTimer);
        rootRed.add(redButton);
        rootRed.add(redResetButton);

        //blue parts
        rootBlue.setLayout(null);
        rootBlue.setBackground(new Color(0x468e91));
        rootBlue.setBounds(tabbedPane1.getBounds());
        rootBlue.setSize(tabbedPane1.getSize());

        blueTimer.setBackground(new Color(0x599a9c));
        blueTimer.setBounds(71, 50, 350, 200);
        blueTimer.setLayout(null);

        blueTimer.add(blueTime);
        rootBlue.add(blueTimer);
        rootBlue.add(blueButton);
        rootBlue.add(blueResetButton);

        //darkBlue parts
        rootDarkBlue.setLayout(null);
        rootDarkBlue.setBackground(new Color(0x437ea8));
        rootDarkBlue.setBounds(tabbedPane1.getBounds());
        rootDarkBlue.setSize(tabbedPane1.getSize());

        darkBlueTimer.setBackground(new Color(0x568bb1));
        darkBlueTimer.setBounds(71, 50, 350, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);
        rootDarkBlue.add(darkBlueButton);
        rootDarkBlue.add(darkBlueResetButton);

        //tabbedPane-stuff
        tabbedPane1.setBounds(this.getBounds());
        tabbedPane1.setSize(this.getSize());
        tabbedPane1.addTab("Pomodoro", images.tomato, rootRed);
        tabbedPane1.addTab("Short Break", images.coffeeCup, rootBlue);
        tabbedPane1.addTab("Long Break", images.coffeeCup2, rootDarkBlue);
        tabbedPane1.addChangeListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();
            //System.out.println(selectedIndex);
        });

        //finish frame
        setJMenuBar(menuBar);
        add(tabbedPane1);
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