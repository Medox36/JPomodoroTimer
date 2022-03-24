public class TimerManagement {
    private final PomodoroTimer redTimer, blueTimer, darkBlueTimer;
    private int wasActive;

    public TimerManagement(PomodoroTimeLabel redLabel, PomodoroTimeLabel blueLabel, PomodoroTimeLabel darkblueLabel) {
        redTimer = new PomodoroTimer(redLabel, 0);
        blueTimer = new PomodoroTimer(blueLabel, 1);
        darkBlueTimer = new PomodoroTimer(darkblueLabel, 2);
    }

    public void stopActiveTimer() {
        if (redTimer.isActive()) {
            redTimer.haltTimer();
            wasActive = 1;
        } else if (blueTimer.isActive()) {
            blueTimer.haltTimer();
            wasActive = 2;
        } else if (darkBlueTimer.isActive()) {
            darkBlueTimer.haltTimer();
            wasActive = 3;
        } else {
            wasActive = 0;
        }
    }

    public void resumeActiveTimer() {
        switch (wasActive) {
            case 0 -> {}
            case 1 -> redTimer.startTimer();
            case 2 -> blueTimer.startTimer();
            case 3 -> darkBlueTimer.startTimer();
            default -> throw new IllegalStateException("Unexpected value: " + wasActive);
        }
        wasActive = 0;
    }

    public PomodoroTimer getRedTimer() {
        return redTimer;
    }

    public PomodoroTimer getBlueTimer() {
        return blueTimer;
    }

    public PomodoroTimer getDarkBlueTimer() {
        return darkBlueTimer;
    }
}