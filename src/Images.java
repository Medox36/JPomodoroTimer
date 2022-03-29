import javax.swing.ImageIcon;
import java.util.Objects;

public class Images {
    protected static ImageIcon frameIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/intelligent-timer.png")));
    protected static ImageIcon tomato = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/tomato.png")));
    protected static ImageIcon coffeeCup = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup.png")));
    protected static ImageIcon coffeeCup2 = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup2.png")));
    protected static ImageIcon coffeeCup3 = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/coffee-cup3.png")));
    protected static ImageIcon settingsSave = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting.png")));
    protected static ImageIcon settingsLoad = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/open-file.png")));
    protected static ImageIcon notification = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/notification.png")));
    protected static ImageIcon settingsIcon = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/setting-lines.png")));
    protected static ImageIcon systemTray = new ImageIcon(Objects.requireNonNull(PomodoroGUI.class.getResource("images/systemtray.png")));
}