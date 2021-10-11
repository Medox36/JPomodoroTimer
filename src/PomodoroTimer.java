import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PomodoroTimer {
    private TimeTimer redTimer;
    private TimeTimer blueTimer;
    private TimeTimer darkBlueTimer;
    private DateTimeFormatter timeFormatter;

    public PomodoroTimer(LocalTime redTime, LocalTime blueTime, LocalTime darkBlueTime) {
        timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        redTimer = new TimeTimer(redTime);
        blueTimer = new TimeTimer(blueTime);
        darkBlueTimer = new TimeTimer(darkBlueTime);
    }

    public void startRedTime() {

    }

}