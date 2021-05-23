import java.awt.Graphics;
import java.awt.Color;

public class Obstacle {
    int x;
    int y;
    int speed;

    public Obstacle(int x, int y, int sp) {
        this.x = x;
        speed = sp;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 100, 200);
    }
}
