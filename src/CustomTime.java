import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

public class CustomTime extends JFrame {
    private JFrame frame;
    private JPanel root;
    private JLabel minLabel, secLabel;
    private JSpinner minSpinner, secSpinner;
    private JButton confirm;

    public CustomTime(String title) {
        super(title);
        frame = this;
        frame.setSize(300,150);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        root = new JPanel();
        minSpinner = new JSpinner(new SpinnerDateModel());
        minSpinner.setEditor(new JSpinner.DateEditor(minSpinner, "mm"));
        minSpinner.setValue(new Date());
        minSpinner.setSize(100, 25);
        minSpinner.setLocation(25, 40);
        secSpinner = new JSpinner(new SpinnerDateModel());
        secSpinner.setEditor(new JSpinner.DateEditor(secSpinner, "ss"));
        secSpinner.setValue(new Date());
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
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                confirm();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        root.setSize(this.getSize());
        root.setLayout(null);
        root.add(minLabel);
        root.add(secLabel);
        root.add(minSpinner);
        root.add(secSpinner);
        root.add(confirm);
        frame.add(root);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    private void confirm() {
        System.out.println("confirmed");
    }
}
