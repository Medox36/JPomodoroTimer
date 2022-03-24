public class TimerManagement {
    private final PomodoroTimer redTimer, blueTimer, darkBlueTimer;
    private StartStopButton redButton, blueButton, darkBlueButton;
    private final PomodoroGUI pomodoroGUI;
    private int wasActive;
    private int breakIntervalCounter;

    public TimerManagement(PomodoroTimeLabel redLabel, PomodoroTimeLabel blueLabel, PomodoroTimeLabel darkblueLabel, PomodoroGUI pomodoroGUI) {
        redTimer = new PomodoroTimer(redLabel, 0, this);
        blueTimer = new PomodoroTimer(blueLabel, 1, this);
        darkBlueTimer = new PomodoroTimer(darkblueLabel, 2, this);
        this.pomodoroGUI = pomodoroGUI;
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

    public void timerEnded(int colour) {
        Settings.getInstance().playRightSound();
        if (colour == 0) {
            if (breakIntervalCounter >= Settings.getInstance().getBreakInterval()) {
                pomodoroGUI.setSelectedTab(2);
                breakIntervalCounter = 0;
                if (Settings.getInstance().isAutoBreaks()) {
                    darkBlueButton.startTimer();
                }
            } else {
                breakIntervalCounter++;
                pomodoroGUI.setSelectedTab(1);
                if (Settings.getInstance().isAutoBreaks()) {
                    blueButton.startTimer();
                }
            }
        } else if (colour == 1 || colour == 2) {
            pomodoroGUI.setSelectedTab(0);
            if (Settings.getInstance().isAutoPomodoros()) {
                redButton.startTimer();
            }
        }
    }

    public void setStartStopButtons(StartStopButton redButton, StartStopButton blueButton, StartStopButton darkBlueButton) {
        this.redButton = redButton;
        this.blueButton = blueButton;
        this.darkBlueButton = darkBlueButton;
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