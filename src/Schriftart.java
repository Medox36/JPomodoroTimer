import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Schriftart {

    private Font font;

    public Schriftart() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Schriftart.class.getResourceAsStream("fonts/DSEG7Classic-Regular.ttf")));
            font.deriveFont(Font.PLAIN, 26);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont(float size) {
        font.deriveFont(size);
        return font;
    }

    public Font getFont() {
        System.out.println(font.getFontName()+"\n"+font.getSize());
        return font;
    }
}