import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.Calendar;

public class CustomTime extends JFrame {
    private final PomodoroTimeLabel timeLabel;
    private final JPanel root;
    private final JLabel minLabel;
    private final JLabel secLabel;
    private final JSpinner minSpinner;
    private final JSpinner secSpinner;
    private final JButton confirm;

    public CustomTime(String title, PomodoroTimeLabel timeLabel, ImageIcon icon) {
        super(title);
        this.timeLabel = timeLabel;
        this.setSize(300,150);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
        root = new JPanel();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970,Calendar.JANUARY,1,0,20,0);
        minSpinner = new JSpinner(new SpinnerDateModel());
        minSpinner.setEditor(new JSpinner.DateEditor(minSpinner, "mm"));
        minSpinner.setValue(calendar.getTime());
        minSpinner.setSize(100, 25);
        minSpinner.setLocation(25, 40);
        secSpinner = new JSpinner(new SpinnerDateModel());
        secSpinner.setEditor(new JSpinner.DateEditor(secSpinner, "ss"));
        secSpinner.setValue(calendar.getTime());
        secSpinner.setSize(100, 25);
        secSpinner.setLocation(159, 40);
        minLabel = new JLabel();
        minLabel.setText("Minutes");
        minLabel.setBounds(25, 15, 100, 25);
        secLabel = new JLabel();
        secLabel.setText("Seconds");
        secLabel.setBounds(159, 15, 100, 25);

        confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setBounds(100, 75, 90, 25);
        confirm.addActionListener(e -> confirm());
        confirm.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                confirm();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        root.setSize(this.getSize());
        root.setLayout(null);
        root.add(minLabel);
        root.add(secLabel);
        root.add(minSpinner);
        root.add(secSpinner);
        root.add(confirm);
        this.add(root);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    private void confirm(){
        String minutes;
        try {
            minSpinner.commitEdit();
            secSpinner.commitEdit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SpinnerDateModel model = (SpinnerDateModel) minSpinner.getModel();
        Calendar time = Calendar.getInstance();
        time.setTime(model.getDate());
        minutes = String.valueOf(time.get(Calendar.MINUTE));
        model = (SpinnerDateModel) secSpinner.getModel();
        time.setTime(model.getDate());
        timeLabel.setTime(minutes, zeroFill(time.get(Calendar.SECOND)));
        closeFrame();
    }

    private String zeroFill(int val) {
        if (val < 9)
            return "0" + val;
        else
            return String.valueOf(val);
    }

    private void closeFrame() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        this.setVisible(false);
        this.dispose();
    }
}
