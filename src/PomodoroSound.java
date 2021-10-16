import java.util.Objects;
import java.io.IOException;
import javax.sound.sampled.*;

public class PomodoroSound {
    private Clip clip;

    public PomodoroSound() {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playDigitalAlarm() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(Objects.requireNonNull(PomodoroSound.class.getResource("sounds/digital_clock_alarm.wav")));
            clip.open(audio);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-22.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}