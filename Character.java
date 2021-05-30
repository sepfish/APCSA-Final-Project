import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

public class Character {
    private int x;
    private int y;
    private static BufferedImage face;
    private static BufferedImage run1;
    private static BufferedImage run2;
    private static BufferedImage run3;
    private static BufferedImage run4;

    private int walkCount;
    private int jumpCount;
    private boolean crouch;

    public Character() {
        try {
            File file = new File("media/run-1.png");
            run1 = ImageIO.read(file);
            file = new File("media/run-2.png");
            run2 = ImageIO.read(file);
            file = new File("media/run-3.png");
            run3 = ImageIO.read(file);
            file = new File("media/run-4.png");
            run4 = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setX(0);
        setY(265);
        jumpCount = 0;
        crouch = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
        walkCount++;
    }

    public boolean collide(Obstacle o) {
        if (o instanceof Stalagmite) {
            return (o.getX() > 75) && (o.getX() < x + 125) && (o.getY() < y + 210);
        } else if (o instanceof Stalactite) {
            return (o.getX() > 75) && (o.getX() < x + 125) && (o.getY() + 165 > y);
        } else {
            return false;
        }        
    }

    public boolean isCrouching() {
        return crouch;
    }

    public void move(String dir) {
        if (dir.equals("crouch")) {
            crouch = true;
        } else {
            crouch = false;
            if (dir.equals("up")) {
                y -= (int)(0.8*jumpCount);
                jumpCount++;
            } else if (dir.equals("down")) {
                jumpCount--;
                y += (int)(0.8*jumpCount);
            } else if (dir.equals("reset")) {
                jumpCount = 0; 
            }
        }
    }

    public void draw(Graphics window) {
        //window.setColor(Color.YELLOW);
        //window.fillRect(x, y, 174, 210);
        if (!crouch) {
            if (walkCount/8 % 4 == 0) {
                window.drawImage(run1, x, y, 174, 210, null);
            } else if (walkCount/8 % 4 == 1) {
                window.drawImage(run2, x, y, 174, 210, null);
            } else if (walkCount/8 % 4 == 2) {
                window.drawImage(run3, x, y, 174, 210, null);
            } else if (walkCount/8 % 4 == 3) {
                window.drawImage(run4, x, y, 174, 210, null);
            }
        } else {
            setY(315);
            window.setColor(Color.YELLOW);
            window.fillRect(x, y, 124, 160);
        }
        
     }

    public void setImages(String stand, String run1, String run2, String run3) {
        try {
            this.face = ImageIO.read(this.getClass().getResourceAsStream(stand));
            this.run1 = ImageIO.read(this.getClass().getResourceAsStream(run1));
            this.run2 = ImageIO.read(this.getClass().getResourceAsStream(run2));
            this.run3 = ImageIO.read(this.getClass().getResourceAsStream(run3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
