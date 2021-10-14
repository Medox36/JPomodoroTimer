import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class PomodoroTimeLabel extends JLabel {

    public PomodoroTimeLabel(String initTime, String name) {
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Gadugi", Font.PLAIN,130));
        this.setTime(initTime);
        this.setBounds(20, 15, 320, 150);
        this.setName(name);
    }

    public void setTime(LocalTime time) {
        this.setText(time.getMinute() + ":" + time.getSecond());
    }

    public void setTime(String time) {
        this.setText(time);
    }

    public void setTime(String minutes, String seconds) {
        this.setText(minutes + ":" + seconds);
    }
}
