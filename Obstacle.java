import java.awt.Graphics;
import java.awt.Color;

public class Obstacle {
    private int x;
    private int y;
    private int speed;
    public static final int baseSpeed = 6;

    public Obstacle(int x, int y, int sp) {
        this.x = x;
        this.y = y;
        speed = sp;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 40, 80);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public void update() {
        if(x+40 > 0) {
            x -= speed;
        } else {
            x = 800;
        }
    }

    public void reset() { 
        x = 800;
        setSpeed(baseSpeed);
    }
}
