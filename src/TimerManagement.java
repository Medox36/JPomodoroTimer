public class TimerManagement {
    private PomodoroTimer redTimer, blueTimer, darkBlueTimer;
    private Thread rT, bT, dbT;
    private int wasActive;

    public TimerManagement(PomodoroTimeLabel redLabel, PomodoroTimeLabel blueLabel, PomodoroTimeLabel darkblueLabel) {
        redTimer = new PomodoroTimer(redLabel);
        blueTimer = new PomodoroTimer(blueLabel);
        darkBlueTimer = new PomodoroTimer(darkblueLabel);
        rT = new Thread(redTimer);
        rT.start();
        bT = new Thread(blueTimer);
        bT.start();
        dbT = new Thread(darkBlueTimer);
        dbT.start();
    }

    public void resumeRedTimer() {
        rT.notify();
        redTimer.setActive(true);
    }

    public void resumeBlueTimer() {
        bT.notify();
        blueTimer.setActive(true);
    }

    public void resumeDarkBlueTimer() {
        dbT.notify();
        darkBlueTimer.setActive(true);
    }

    public void haltRedTime() {
        try {
            rT.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redTimer.setActive(false);
    }

    public void haltBlueTime() {
        try {
            bT.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blueTimer.setActive(false);
    }

    public void  haltDarkBlueTime() {
        try {
            dbT.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        darkBlueTimer.setActive(false);
    }

    public void stopActiveTimer() {
        if (redTimer.isActive()) {
            haltRedTime();
            wasActive = 1;
        }
        if (blueTimer.isActive()) {
            haltBlueTime();
            wasActive = 2;
        }
        if (darkBlueTimer.isActive()) {
            haltDarkBlueTime();
            wasActive = 3;
        }
    }

    public void resumeActiveTimer() {
        switch (wasActive) {
            case 1 -> resumeRedTimer();
            case 2 -> resumeBlueTimer();
            case 3 -> resumeDarkBlueTimer();
            default -> throw new IllegalStateException("Unexpected value: " + wasActive);
        }
        wasActive = 0;
    }
}