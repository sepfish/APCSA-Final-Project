import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;


public class Stalactite extends Obstacle{
    private static BufferedImage image;

    public Stalactite(int x, int speed) {
        super(x, 125, speed);
    } 

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(getX(), getY(), 40, 165);
    }
}  
