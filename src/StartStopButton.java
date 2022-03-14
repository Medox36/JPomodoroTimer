import javax.swing.*;
import java.awt.*;

public class StartStopButton extends JButton {
    private PomodoroTimer pomodoroTimer;
    private TimerManagement tm;

    public StartStopButton(TimerManagement tm, PomodoroTimer pomodoroTimer) {
        super("Start");
        this.setBounds(145, 310,200,75);
        this.setFont(new Font("Gadugi",Font.PLAIN,50));
        this.setFocusPainted(false);
        this.setMultiClickThreshhold(500);
        this.tm = tm;
        this.pomodoroTimer = pomodoroTimer;
        this.pomodoroTimer.setButton(this);
        this.addActionListener(e -> {
            if (pomodoroTimer.isActive()) {
                setStop();
            } else {
                setRunning();
            }
        });
    }

    private void setStop() {
        pomodoroTimer.haltTimer();
        setText("Start");
    }

    private void setRunning() {
        tm.stopActiveTimer();
        pomodoroTimer.startTimer();
        setText("Stop");
    }
}