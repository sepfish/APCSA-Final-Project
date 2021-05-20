import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Character {
    public static BufferedImage stand;
    public static BufferedImage run1;
    public static BufferedImage run2;
    public static BufferedImage run3;

    public Character() {
        try {
            File file = new File("megamix-rin.png");
            stand = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics window) {
        window.drawImage(stand, 0, 0, 178, 290, null);
     }

    public void setImages(String stand, String run1, String run2, String run3) {
        try {
            this.stand = ImageIO.read(this.getClass().getResourceAsStream(stand));
            this.run1 = ImageIO.read(this.getClass().getResourceAsStream(run1));
            this.run2 = ImageIO.read(this.getClass().getResourceAsStream(run2));
            this.run3 = ImageIO.read(this.getClass().getResourceAsStream(run3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
