import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Schriftart {

    private Font font;

    public Schriftart() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("fonts/DSEG7Classic-Regular.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fontNames = graphicsEnvironment.getAllFonts();
        for (Font s : fontNames) {
            System.out.println(s);
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

    public static void main(String[] args) {
        Schriftart schriftart = new Schriftart();
    }
}