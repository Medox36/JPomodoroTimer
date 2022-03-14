import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PomodoroTimer {
    private final PomodoroTimeLabel label;
    private StartStopButton button;
    private LocalTime time;
    private final Timer timer;
    private boolean isActive;

    public PomodoroTimer(PomodoroTimeLabel label) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        this.label = label;
        time = LocalTime.of(0, Integer.parseInt(label.getMin()), Integer.parseInt(label.getSec()));
        time.format(timeFormatter);
        timer = new Timer(1000, e -> decreaseTimer());
        isActive = false;
    }

    public void startTimer() {
        isActive = true;
        timer.start();
        button.setText("Stop");
    }

    public void haltTimer() {
        timer.stop();
        isActive = false;
        button.setText("Start");
    }

    private void decreaseTimer() {
        updateTime();
        time = time.minusSeconds(1);
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

    public void setButton(StartStopButton button) {
        this.button = button;
    }
}