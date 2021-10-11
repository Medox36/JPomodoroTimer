import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

public class PomodoroTimerTask extends TimerTask {
    private TimeTimer timeTimer;
    private LocalTime time;

    public PomodoroTimerTask(TimeTimer timeTimer, LocalTime time) {
        this.timeTimer = timeTimer;
        this.time = time;
    }

    @Override
    public void run() {
        time.minus(1, ChronoUnit.SECONDS);
        timeTimer.updateTime(time);
        if (String.valueOf(time).equals("00:00")) {
            timeTimer.timeExpired();
        }
    }
}