import javax.swing.ImageIcon;
import java.util.Objects;

public class Images {
    protected ImageIcon frameIcon;
    protected ImageIcon tomato;
    protected ImageIcon coffeeCup;
    protected ImageIcon coffeeCup2;
    protected ImageIcon settingsSave;
    protected ImageIcon settingsLoad;
    protected ImageIcon notification;
    protected ImageIcon settingsIcon;

    public Images() {
        frameIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/intelligent-timer.png")));
        tomato = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/tomato.png")));
        coffeeCup = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup.png")));
        coffeeCup2 = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup2.png")));
        settingsSave = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting.png")));
        settingsLoad = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/open-file.png")));
        notification = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/notification.png")));
        settingsIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting-lines.png")));
    }
}