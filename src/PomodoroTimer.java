import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PomodoroTimer implements Runnable {
    private DateTimeFormatter timeFormatter;
    private PomodoroTimeLabel label;
    private LocalTime time;
    private boolean isActive;

    public PomodoroTimer(PomodoroTimeLabel label) {
        timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        this.label = label;
        time = LocalTime.of(0,0,0);
        time.format(timeFormatter);
        isActive = false;
    }

    public void startTimer() {
        isActive = true;
    }

    public void haltTimer() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isActive = false;
    }

    private void decreaseTimer() {
        updateTime();
        time = time.minusSeconds(1); //time.minus(1, ChronoUnit.SECONDS);
        updateLabel();
    }

    private void updateTime() {
        int h;
        int min;
        int sec;
        if (Integer.parseInt(fetchTimerMin()) >= 60) {
            h = 1;
            min = Integer.parseInt(fetchTimerMin()) - 60;
        } else {
            h = 0;
            min = Integer.parseInt(fetchTimerMin());
        }
        sec = Integer.parseInt(fetchTimerSec());
        time = LocalTime.of(h, min, sec);
    }

    private void updateLabel() {
        label.setTime(time);
    }

    private String fetchTimerMin() {
        return label.getMin();
    }

    private String fetchTimerSec() {
        return label.getSec();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public void run() {

    }
}