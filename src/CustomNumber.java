import javax.swing.*;
import java.awt.event.WindowEvent;
import java.text.ParseException;

public class CustomNumber extends JFrame {
    private final JSpinner spinner;

    public CustomNumber() {
        super("Long Break Interval");
        setSize(290,150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(Images.coffeeCup3.getImage());

        spinner = new JSpinner(new SpinnerNumberModel(Settings.getInstance().getBreakInterval(), 0, 6, 1));
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "##"));
        spinner.setSize(100, 25);
        spinner.setLocation(85, 40);

        JPanel root = new JPanel();

        JButton confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setBounds(40, 75, 80, 25);
        confirm.setFocusPainted(false);
        confirm.addActionListener(e -> confirm());
        JButton cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setBounds(150, 75, 80, 25);
        confirm.setFocusPainted(false);
        cancel.addActionListener(e -> closeFrame());

        root.setSize(this.getSize());
        root.setLayout(null);
        root.add(spinner);
        root.add(confirm);
        root.add(cancel);
        add(root);

        setAlwaysOnTop(true);
        getRootPane().setDefaultButton(confirm);
        setVisible(true);
    }

    private void confirm() {
        try {
            spinner.commitEdit();
            int value = (int) spinner.getValue();
            if (value > -1 && value < 7) {
                Settings.getInstance().setBreakInterval(value);
                closeFrame();
            } else {
                String message = "Pleas select a Value between 0 and 6";
                JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void closeFrame() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        setVisible(false);
        dispose();
    }
}