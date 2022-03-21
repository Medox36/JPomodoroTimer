import javax.swing.*;
import java.awt.*;

public class ResetButton extends JButton {

    public ResetButton(PomodoroTimeLabel pomodoroTimeLabel, int colour) {
        super("Reset");
        setBounds(195, 410,100,35);
        setFont(new Font("Gadugi",Font.PLAIN,20));
        setFocusPainted(false);
        setMultiClickThreshhold(500);
        addActionListener(e -> {
            switch (colour) {
                case 0 -> pomodoroTimeLabel.setTime(Settings.getInstance().getRedTime());
                case 1 -> pomodoroTimeLabel.setTime(Settings.getInstance().getBlueTime());
                case 2 -> pomodoroTimeLabel.setTime(Settings.getInstance().getDarkBlueTime());
            }
        });
    }
}
