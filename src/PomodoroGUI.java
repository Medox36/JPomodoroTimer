import javax.swing.*;
import java.awt.*;

public class PomodoroGUI extends JFrame{
    private final PomodoroMenuBar menuBar;
    private final PomodoroTimeLabel redTime, blueTime, darkBlueTime;
    private final Settings settings;
    private final TimerManagement timerManagement;
    private final JTabbedPane tabbedPane;
    private PopUpMenu popUpMenu;

    public PomodoroGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        settings = Settings.getInstance();

        tabbedPane = new JTabbedPane();
        JPanel rootRed = new JPanel();
        JPanel rootBlue = new JPanel();
        JPanel rootDarkBlue = new JPanel();
        JPanel redTimer = new JPanel();
        JPanel blueTimer = new JPanel();
        JPanel darkBlueTimer = new JPanel();
        redTime = new PomodoroTimeLabel(settings.getRedTime(), "red");
        blueTime = new PomodoroTimeLabel(settings.getBlueTime(), "blue");
        darkBlueTime = new PomodoroTimeLabel(settings.getDarkBlueTime(), "darkblue");
        menuBar = new PomodoroMenuBar(this);
        timerManagement = new TimerManagement(redTime, blueTime, darkBlueTime, this);
        StartStopButton redButton = new StartStopButton(timerManagement, timerManagement.getRedTimer());
        StartStopButton blueButton = new StartStopButton(timerManagement, timerManagement.getBlueTimer());
        StartStopButton darkBlueButton = new StartStopButton(timerManagement, timerManagement.getDarkBlueTimer());
        ResetButton redResetButton = new ResetButton(redTime, 0);
        ResetButton blueResetButton = new ResetButton(blueTime, 1);
        ResetButton darkBlueResetButton = new ResetButton(darkBlueTime, 2);

        timerManagement.setStartStopButtons(redButton, blueButton, darkBlueButton);

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setTitle("Pomodoro Timer");
        setIconImage(Images.frameIcon.getImage());

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
        rootRed.add(redResetButton);

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
        rootBlue.add(blueResetButton);

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
        rootDarkBlue.add(darkBlueResetButton);

        //tabbedPane-stuff
        tabbedPane.setBounds(this.getBounds());
        tabbedPane.setSize(this.getSize());
        tabbedPane.addTab("Pomodoro", Images.tomato, rootRed);
        tabbedPane.addTab("Short Break", Images.coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", Images.coffeeCup2, rootDarkBlue);
        tabbedPane.addChangeListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = tabbedPane.getSelectedIndex();
            //System.out.println(selectedIndex);
        });

        //finish frame
        setJMenuBar(menuBar);
        add(tabbedPane);
    }

    public void setRedTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", redTime, Images.tomato, this);
        customTime.setLocationRelativeTo(this);
    }

    public void setBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", blueTime, Images.coffeeCup, this);
        customTime.setLocationRelativeTo(this);
    }

    public void setDarkBlueTimeWithFrame() {
        stopActiveTimer();
        CustomTime customTime = new CustomTime("Custom Time", darkBlueTime, Images.coffeeCup2, this);
        customTime.setLocationRelativeTo(this);
    }

    private void stopActiveTimer() {
        timerManagement.stopActiveTimer();
    }

    public void resumeStoppedTimer() {
        timerManagement.resumeActiveTimer();
    }

    public void setSelectedTab(int tab) {
        tabbedPane.setSelectedIndex(tab);
    }

    public void reloadSettings() {
        stopActiveTimer();
        redTime.setTime(settings.getRedTime());
        blueTime.setTime(settings.getBlueTime());
        darkBlueTime.setTime(settings.getDarkBlueTime());
        menuBar.setAutoBreaks(settings.isAutoBreaks());
        menuBar.setAutoPomodoro(settings.isAutoPomodoros());
        popUpMenu.setNotifications(settings.getNotifications());
    }

    public void setPopUpMenu(PopUpMenu popUpMenu) {
        this.popUpMenu = popUpMenu;
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