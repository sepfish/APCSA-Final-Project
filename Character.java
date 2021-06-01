import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Character {
    private int x;
    private int y;
    private ArrayList<BufferedImage> runImages;
    private ArrayList<BufferedImage> crouchImages;

    private int walkCount;
    private int jumpCount;
    private boolean crouch;

    public Character() {
        runImages = new ArrayList<BufferedImage>();
        crouchImages = new ArrayList<BufferedImage>();
        try {
            runImages.add(ImageIO.read(new File("media/run-1.png")));
            runImages.add(ImageIO.read(new File("media/run-2.png")));
            runImages.add(ImageIO.read(new File("media/run-3.png")));
            runImages.add(ImageIO.read(new File("media/run-4.png")));
            crouchImages.add(ImageIO.read(new File("media/crouch1.png")));
            crouchImages.add(ImageIO.read(new File("media/crouch-2.png")));
            crouchImages.add(ImageIO.read(new File("media/crouch3.png")));
            crouchImages.add(ImageIO.read(new File("media/crouch4.png")));
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
            return (o.getX() < x + 125) && (o.getY() + 165 > y);
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
                setY(265);
            }
        }
    }

    public void draw(Graphics window) {
        //window.setColor(Color.YELLOW);
        //window.fillRect(x, y, 174, 210);
        if (!crouch) {
            window.drawImage(runImages.get(walkCount/8%4), x, y, 174, 210, null);
        } else {
            setY(330);
            window.drawImage(crouchImages.get(walkCount/8%4), x, y, 174, 150, null);            
        }
     }
}
