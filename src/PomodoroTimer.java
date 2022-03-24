import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PomodoroTimer {
    private final PomodoroTimeLabel label;
    private StartStopButton button;
    private LocalTime time;
    private final Timer timer;
    private final int colour;
    private boolean isActive;

    public PomodoroTimer(PomodoroTimeLabel label, int colour) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        this.label = label;
        this.colour = colour;
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
        if (Objects.equals(label.getMin(), "0") && Objects.equals(label.getSec(), "0")) {
            //TODO play finished sound and check if Autobreak and or Autopomodoro is active
            Settings.getInstance().playRightSound();
            if (colour == 0) {
                if (Settings.getInstance().isAutoBreaks()) {
                    //TODO find out if short or long break is coming and start the break
                }
            } else if (colour == 1 || colour == 2) {
                if (Settings.getInstance().isAutoPomodoros()) {
                    //TODO start the Pomodoro Timer aka Work Timer
                }
            }
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

    public void setButton(StartStopButton button) {
        this.button = button;
    }
}