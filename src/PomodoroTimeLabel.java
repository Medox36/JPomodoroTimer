import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public class PomodoroTimeLabel extends JLabel {
    private String min;
    private String sec;

    public PomodoroTimeLabel(String initTime, String name) {
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Gadugi", Font.PLAIN,130));
        this.setTime(initTime);
        this.setBounds(20, 15, 320, 150);
        this.setName(name);
    }

    public void setTime(LocalTime time) {
        this.setText(zeroFill(String.valueOf(time.getMinute())) + ":" + zeroFill(String.valueOf(time.getSecond())));
        min = String.valueOf(time.getMinute());
        sec = String.valueOf(time.getSecond());
    }

    public void setTime(String time) {
        this.setText(time);
        min = String.valueOf(time.charAt(0)) + time.charAt(1);
        sec = String.valueOf(time.charAt(3)) + time.charAt(4);
    }

    public void setTime(String minutes, String seconds) {
        this.setText(zeroFill(minutes) + ":" + zeroFill(seconds));
        min = minutes;
        sec = seconds;
    }

    private String zeroFill(String val) {
        if (Integer.parseInt(val) < 9)
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