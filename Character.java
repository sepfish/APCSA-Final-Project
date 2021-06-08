import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.Graphics;

public class Character {
    private int x;
    private int y;
    private ArrayList<BufferedImage> chara1Images;
    private ArrayList<BufferedImage> chara2Images;

    private int walkCount;
    private int jumpCount;
    private boolean crouch;

    public Character() {
        chara1Images = new ArrayList<BufferedImage>();
        chara2Images = new ArrayList<BufferedImage>();
        try {
            chara1Images.add(ImageIO.read(new File("media/run-1.png")));
            chara1Images.add(ImageIO.read(new File("media/run-2.png")));
            chara1Images.add(ImageIO.read(new File("media/run-3.png")));
            chara1Images.add(ImageIO.read(new File("media/run-4.png")));
            chara1Images.add(ImageIO.read(new File("media/crouch1.png")));
            chara1Images.add(ImageIO.read(new File("media/crouch-2.png")));
            chara1Images.add(ImageIO.read(new File("media/crouch3.png")));
            chara1Images.add(ImageIO.read(new File("media/crouch4.png")));
            chara2Images.add(ImageIO.read(new File("media/char2run1.png")));
            chara2Images.add(ImageIO.read(new File("media/char2run2.png")));
            chara2Images.add(ImageIO.read(new File("media/char2run3.png")));
            chara2Images.add(ImageIO.read(new File("media/char2run4.png")));
            chara2Images.add(ImageIO.read(new File("media/c2crouch-1.png")));
            chara2Images.add(ImageIO.read(new File("media/c2crouch-2.png")));
            chara2Images.add(ImageIO.read(new File("media/c2crouch-3.png")));
            chara2Images.add(ImageIO.read(new File("media/c2crouch-4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setX(0);
        setY(6);
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
                setY(266);
            }
        }
    }

    public void draw(Graphics window, int i) {
        //window.setColor(Color.YELLOW);
        //window.fillRect(x, y, 174, 210);
        if (!crouch) {
            if (i == 0) {
                window.drawImage(chara1Images.get(walkCount/8%4), x, y, 174, 210, null);
            } else {
                window.drawImage(chara2Images.get(walkCount/8%4), x, y, 174, 210, null);
            }
        } else {
            setY(330);
            if (i == 0) {
                window.drawImage(chara1Images.get(walkCount/8%4+4), x, y, 174, 150, null);
            } else {
                window.drawImage(chara2Images.get(walkCount/8%4+4), x, y, 174, 150, null);
            }
                        
        }
     }
}
