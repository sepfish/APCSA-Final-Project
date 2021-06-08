import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Stalagmite extends Obstacle {
    private BufferedImage image;

    public Stalagmite(int x, int speed) {
        super(x, 395, speed);
        try {
            image = ImageIO.read(new File("media/stalagmite-1.png"));
        } catch (IOException e) {
            image = null;
        }
    }

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), 60, 80, null);
        } else {
            super.draw(g);
        }
    }
    
}
