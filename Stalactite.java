import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;


public class Stalactite extends Obstacle {
    private BufferedImage image;

    public Stalactite(int x, int speed) {
        super(x, 125, speed);
        try {
            image = ImageIO.read(new File("media/stalagmite-2.png"));
        } catch (IOException e) {
            image = null;
        }
    } 

    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, getX(), getY(), 80, 185, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillRect(getX(), getY(), 40, 185);
        }
    }
}  
