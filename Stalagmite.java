import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stalagmite extends Obstacle{
    private static BufferedImage image;

    public Stalagmite(int x, int speed) {
        super(x, 395, speed);
    }

    public void draw(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("media/stalagmite-1.png")), getX(), getY(), 60, 80, null);
        } catch (IOException e) {
            super.draw(g);
        }
    }
    
}
