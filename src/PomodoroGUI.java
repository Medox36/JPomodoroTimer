import javax.swing.*;
import java.awt.*;


public class PomodoroGUI {
    private Color red, lightRed, blue, lightBlue, darkBlue, lightDarkBlue;
    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JTabbedPane tabbedPane;
    private JPanel rootRed, rootBlue, rootDarkBlue, redTimer, blueTimer, darkBlueTimer;
    private JLabel redTime, blueTime, darkBlueTime;
    private ImageIcon frameIcon, tomato, coffeeCup, coffeeCup2;

    public PomodoroGUI() {
        red = new Color(0xdb524d);
        lightRed = new Color(0xdf645f);
        blue = new Color(0x468e91);
        lightBlue = new Color(0x599a9c);
        darkBlue = new Color(0x437ea8);
        lightDarkBlue = new Color(0x568bb1);
        frame = new JFrame();
        menuBar = new JMenuBar();

        tabbedPane = new JTabbedPane();
        rootRed = new JPanel();
        rootBlue = new JPanel();
        rootDarkBlue = new JPanel();
        redTimer = new JPanel();
        blueTimer = new JPanel();
        darkBlueTimer = new JPanel();
        redTime = new JLabel();
        blueTime = new JLabel();
        darkBlueTime = new JLabel();
        frameIcon = new ImageIcon("images/intelligenter-timer.png");
        tomato = new ImageIcon("images/tomato.png");
        coffeeCup = new ImageIcon("images/coffee-cup.png");
        coffeeCup2 = new ImageIcon("images/coffee-cup2.png");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pomodoro Timer");
        frame.setIconImage(frameIcon.getImage());

        //red parts
        rootRed.setLayout(null);
        rootRed.setBackground(red);
        rootRed.setBounds(tabbedPane.getBounds());
        rootRed.setSize(tabbedPane.getSize());

        redTime.setForeground(Color.WHITE);
        redTime.setFont(new Font("Dialog",Font.PLAIN,135));
        redTime.setText("00:00");
        redTime.setBounds(30, 25, 340, 150);

        redTimer.setBackground(lightRed);
        redTimer.setBounds(146, 100, 400, 200);
        redTimer.setLayout(null);

        redTimer.add(redTime, BorderLayout.CENTER);
        rootRed.add(redTimer);

        //blue parts
        rootBlue.setLayout(null);
        rootBlue.setBackground(blue);
        rootBlue.setBounds(tabbedPane.getBounds());
        rootBlue.setSize(tabbedPane.getSize());

        blueTime.setForeground(Color.WHITE);
        blueTime.setFont(new Font("Dialog",Font.PLAIN,135));
        blueTime.setText("00:00");
        blueTime.setBounds(30, 25, 340, 150);

        blueTimer.setBackground(lightBlue);
        blueTimer.setBounds(146, 100, 400, 200);
        blueTimer.setLayout(null);

        blueTimer.add(blueTime);
        rootBlue.add(blueTimer);

        //darkBlue parts
        rootDarkBlue.setLayout(null);
        rootDarkBlue.setBackground(darkBlue);
        rootDarkBlue.setBounds(tabbedPane.getBounds());
        rootDarkBlue.setSize(tabbedPane.getSize());

        darkBlueTime.setForeground(Color.WHITE);
        darkBlueTime.setFont(new Font("Dialog",Font.PLAIN,135));
        darkBlueTime.setText("00:00");
        darkBlueTime.setBounds(30, 25, 340, 150);

        darkBlueTimer.setBackground(lightDarkBlue);
        darkBlueTimer.setBounds(146, 100, 400, 200);
        darkBlueTimer.setLayout(null);

        darkBlueTimer.add(darkBlueTime);
        rootDarkBlue.add(darkBlueTimer);



        tabbedPane.setBounds(frame.getBounds());
        tabbedPane.setSize(frame.getSize());
        tabbedPane.addTab("Pomodoro", tomato, rootRed);
        tabbedPane.addTab("Short Break", coffeeCup, rootBlue);
        tabbedPane.addTab("Long Break", coffeeCup2, rootDarkBlue);


        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);
    }


}