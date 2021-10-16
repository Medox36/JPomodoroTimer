import javax.swing.*;
import java.awt.*;

public class StartStopButton extends JButton {
    private boolean state;

    public StartStopButton() {
        super("Start");
        this.setBounds(145, 310,200,75);
        this.setFont(new Font("Gadugi",Font.PLAIN,50));
        this.setFocusPainted(false);
        this.setMultiClickThreshhold(500);
        this.addActionListener(e -> {
            if (state) {
                this.setText("Start");
                state = false;
                //TODO stop the timer
            } else {
                this.setText("Stop");
                state = true;
                //TODO start the timer
            }
        });
        state = false;
    }
}
