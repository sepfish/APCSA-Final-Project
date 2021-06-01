import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;


public class Stalactite extends Obstacle{

    public Stalactite(int x, int speed) {
        super(x, 125, speed);
    } 

    public void draw(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("media/stalagmite-2.png")), getX(), getY(), 80, 185, null);
        } catch (IOException e) {
            g.setColor(Color.CYAN);
            g.fillRect(getX(), getY(), 40, 185);
        }
    }
}  
