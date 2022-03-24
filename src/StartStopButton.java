import javax.swing.JButton;
import java.awt.Font;

public class StartStopButton extends JButton {
    private final PomodoroTimer pomodoroTimer;
    private final TimerManagement tm;

    public StartStopButton(TimerManagement tm, PomodoroTimer pomodoroTimer) {
        super("Start");
        setBounds(145, 310,200,75);
        setFont(new Font("Gadugi",Font.PLAIN,50));
        setFocusPainted(false);
        setMultiClickThreshhold(500);
        this.tm = tm;
        this.pomodoroTimer = pomodoroTimer;
        addActionListener(e -> {
            if (pomodoroTimer.isActive()) {
                setStop();
            } else {
                setRunning();
            }
        });
        pomodoroTimer.setButton(this);
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

    public void startTimer() {
        setRunning();
    }

    public void stopTimer() {
        setStop();
    }
}