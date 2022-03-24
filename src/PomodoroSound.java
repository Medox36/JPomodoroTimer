import java.util.Objects;
import java.io.IOException;
import javax.sound.sampled.*;

public class PomodoroSound {
    private static Clip clip;

    static {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playDigitalAlarm() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(Objects.requireNonNull(PomodoroSound.class.getResource("sounds/digital_clock_alarm.wav")));
            clip.open(audio);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playBellAlarm() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(Objects.requireNonNull(PomodoroSound.class.getResource("sounds/bell_alarm.wav")));
            clip.open(audio);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-10.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}