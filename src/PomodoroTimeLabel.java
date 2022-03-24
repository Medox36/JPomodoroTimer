import javax.swing.JLabel;
import java.awt.*;
import java.time.LocalTime;

public class PomodoroTimeLabel extends JLabel {
    private String min;
    private String sec;

    public PomodoroTimeLabel(String initTime, String name) {
        setForeground(Color.WHITE);
        setFont(new Font("Gadugi", Font.PLAIN,130));
        setTime(initTime);
        setBounds(20, 15, 320, 150);
        setName(name);
    }

    public void setTime(LocalTime time) {
        setText(zeroFill(String.valueOf(time.getMinute())) + ":" + zeroFill(String.valueOf(time.getSecond())));
        min = String.valueOf(time.getMinute());
        sec = String.valueOf(time.getSecond());
    }

    public void setTime(String time) {
        setText(time);
        min = String.valueOf(time.charAt(0)) + time.charAt(1);
        sec = String.valueOf(time.charAt(3)) + time.charAt(4);
    }

    public void setTime(String minutes, String seconds) {
        setText(zeroFill(minutes) + ":" + zeroFill(seconds));
        min = minutes;
        sec = seconds;
    }

    private String zeroFill(String val) {
        if (Integer.parseInt(val) < 10)
            return "0" + val;
        else
            return val;
    }

    public String getMin() {
        return min;
    }

    public String getSec() {
        return sec;
    }
}