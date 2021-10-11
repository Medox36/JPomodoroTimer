import java.time.LocalTime;
import java.util.Timer;

public class TimeTimer {
    private LocalTime startTime;
    private LocalTime time;
    private Timer timer;

    public TimeTimer(LocalTime startTime) {
        timer = new Timer();
        this.startTime = startTime;
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new PomodoroTimerTask(this, time), 0, 1000);

    }

    public void updateTime(LocalTime time) {
        this.time = time;
    }

    public void timeExpired() {

    }

}