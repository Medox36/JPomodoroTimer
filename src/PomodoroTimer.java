import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PomodoroTimer {
    private final PomodoroTimeLabel label;
    private final TimerManagement tm;
    private LocalTime time;
    private final Timer timer;
    private final int colour;
    private boolean isActive;

    public PomodoroTimer(PomodoroTimeLabel label, int colour, TimerManagement tm) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        this.label = label;
        this.colour = colour;
        this.tm = tm;
        time = LocalTime.of(0, Integer.parseInt(label.getMin()), Integer.parseInt(label.getSec()));
        time.format(timeFormatter);
        timer = new Timer(1000, e -> decreaseTimer());
        isActive = false;
    }

    public void startTimer() {
        isActive = true;
        timer.start();
    }

    public void haltTimer() {
        timer.stop();
        isActive = false;
    }

    private void decreaseTimer() {
        updateTime();
        time = time.minusSeconds(1);
        if (Objects.equals(label.getMin(), "0") && Objects.equals(label.getSec(), "0")) {
            tm.timerEnded(colour);
        }
        updateLabel();
    }

    private void updateTime() {
        int h = 0;
        int min = Integer.parseInt(label.getMin());
        int sec = Integer.parseInt(label.getSec());
        if (min >= 60) {
            h = 1;
            min -= 60;
        }
        time = LocalTime.of(h, min, sec);
    }

    private void updateLabel() {
        label.setTime(time);
    }

    public boolean isActive() {
        return isActive;
    }
}