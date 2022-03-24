import javax.swing.*;
import java.awt.event.WindowEvent;
import java.text.ParseException;

public class CustomTime extends JFrame {
    private final PomodoroTimeLabel timeLabel;
    private final JSpinner minSpinner, secSpinner;
    private final PomodoroGUI gui;

    public CustomTime(String title, PomodoroTimeLabel timeLabel, ImageIcon icon, PomodoroGUI gui) {
        super(title);
        this.timeLabel = timeLabel;
        setSize(300,150);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setIconImage(icon.getImage());
        this.gui = gui;
        JPanel root = new JPanel();

        minSpinner = new JSpinner(new SpinnerNumberModel(20, 0, 90, 1));
        minSpinner.setEditor(new JSpinner.NumberEditor(minSpinner, "##"));
        minSpinner.setSize(100, 25);
        minSpinner.setLocation(25, 40);
        secSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        secSpinner.setEditor(new JSpinner.NumberEditor(secSpinner, "##"));
        secSpinner.setSize(100, 25);
        secSpinner.setLocation(159, 40);
        JLabel minLabel = new JLabel();
        minLabel.setText("Minutes");
        minLabel.setBounds(25, 15, 100, 25);
        JLabel secLabel = new JLabel();
        secLabel.setText("Seconds");
        secLabel.setBounds(159, 15, 100, 25);
        JButton confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setBounds(40, 75, 90, 25);
        confirm.setFocusPainted(false);
        confirm.addActionListener(e -> confirm());
        JButton cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(160, 75, 90, 25);
        confirm.setFocusPainted(false);
        cancel.addActionListener(e -> cancel());

        root.setSize(this.getSize());
        root.setLayout(null);
        root.add(minLabel);
        root.add(secLabel);
        root.add(minSpinner);
        root.add(secSpinner);
        root.add(confirm);
        root.add(cancel);
        add(root);
        setAlwaysOnTop(true);
        getRootPane().setDefaultButton(confirm);
        setVisible(true);
    }

    private void confirm(){
        try {
            minSpinner.commitEdit();
            secSpinner.commitEdit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int minutes = (int) minSpinner.getValue();
        int seconds = (int) secSpinner.getValue();
        if (minutes == 0 && seconds == 0) {
            String message = "Combination of "+ minutes + " minutes and " + seconds + " seconds is not Possible!\nTimer will be finished instantly.\nAt least 1 second is required.";
            JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            timeLabel.setTime(String.valueOf(minutes), String.valueOf(seconds));
            closeFrame();
        }
    }

    private void cancel() {
        gui.resumeStoppedTimer();
        closeFrame();
    }

    private void closeFrame() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        setVisible(false);
        dispose();
    }
}