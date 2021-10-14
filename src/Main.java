public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        PomodoroGUI pomodoroGUI = new PomodoroGUI();
        PomodoroSound pomodoroSound = new PomodoroSound();
        pomodoroSound.playDigitalAlarm();
    }
}