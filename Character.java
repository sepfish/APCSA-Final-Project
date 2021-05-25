import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

public class Character {
    private int x;
    private int y;
    private static BufferedImage stand;
    private static BufferedImage run1;
    private static BufferedImage run2;
    private static BufferedImage run3;
    private static BufferedImage run4;

    private int walkCount;
    private int jumpCount;

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
        x = 0;
        y = 0;
        jumpCount = 0;
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
        return (o.getX() > 75) && (o.getX() < x + 125) && (o.getY() < y + 210);
    }

    public void move(String dir) {
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

    public void draw(Graphics window) {
        //window.setColor(Color.YELLOW);
        //window.fillRect(x, y, 174, 210);
        
        if (walkCount/8 % 4 == 0) {
            window.drawImage(run1, x, y, 174, 210, null);
        } else if (walkCount/8 % 4 == 1) {
            window.drawImage(run2, x, y, 174, 210, null);
        } else if (walkCount/8 % 4 == 2) {
            window.drawImage(run3, x, y, 174, 210, null);
        } else if (walkCount/8 % 4 == 3) {
            window.drawImage(run4, x, y, 174, 210, null);
        }
        
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
