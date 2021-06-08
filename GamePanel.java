import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    //game objects
    Character c;
    Timer t;
    Sound music;
    Sound click;
    Sound ouch;
    int score;
    ArrayList<Obstacle> obstacles;
    int charaSelect = 0;

    //other things
    boolean clickStart;
    boolean ouchStart;
    boolean musicStart;
    int currentState;
    int count = 0;
    boolean canJump;
    int oneSixtiethSeconds;
    int songSixtiethSeconds;
    int speed;
    int obsIndex;

    public GamePanel() {
        t = new Timer(1000/60, this);
        c = new Character();
        currentState = 0;
        clickStart = true;
        ouchStart = false;
        musicStart = false;
        canJump = false;
        music = new Sound("media/grey-surii.mp3");
        ouch = new Sound("media/ouch.mp3");
        click = new Sound("media/water-click.mp3");
        score = 0;
        obsIndex = 0;
        speed = Obstacle.baseSpeed;
        obstacles = new ArrayList<Obstacle>();
        initializeObs();
    }

    private void initializeObs() {
        for (int i = 0; i < 10; i++) {
            int random = (int)(Math.random()*2);
            switch (random) {
                case 0:
                    obstacles.add(new Stalagmite(800, speed));
                    break;
                case 1:
                    obstacles.add(new Stalactite(800, speed));
                    break;
            }
        }
    }

    public void start() {
        t.start();
    }

    public void drawMenu(Graphics g) {
        songSixtiethSeconds = 0;
        //playing sound
        if (!clickStart) {
            click.play();
            clickStart = true;
        } 
        if (musicStart) {
            musicStart = false;
            music.stop();
        }
        //drawing images
        try {
            g.drawImage(ImageIO.read(new File("media/menu-alt.png")), 0, 0, 800, 600, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //for "buttons"
        int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() + 8);
        int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() + 31);
        if (mouseX > 182 && mouseX < 375 && mouseY > 344 && mouseY < 397) {
            try {
                g.drawImage(ImageIO.read(new File("media/start-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mouseX > 439 && mouseX < 630 && mouseY > 344 && mouseY < 397) {
            try {
                g.drawImage(ImageIO.read(new File("media/help-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void drawHelp(Graphics g) {
        //playing sound
        if (!clickStart) {
            click.play();
            clickStart = true;
        //drawing images
        } else {
            try {
                g.drawImage(ImageIO.read(new File("media/help.png")), 0, 0, 800, 600, null);
                g.drawImage(ImageIO.read(new File("media/back.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        //"buttons"
            int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() + 8);
            int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() + 31);
            if (mouseX > 23 && mouseX < 90 && mouseY > 58 && mouseY < 126) {
                try {
                    g.drawImage(ImageIO.read(new File("media/back-overlay.png")), 0, 0, 800, 600, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void drawSelect(Graphics g) {
        //playing sound
        if (!clickStart) {
            click.play();
            clickStart = true;
        }
        //read character selection
        try {
            Scanner sc = new Scanner(new File("chara.txt"));
            charaSelect = Integer.parseInt(sc.nextLine().trim());
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //drawing images
        try {
            g.drawImage(ImageIO.read(new File("media/chara-select-alt.png")), 0, 0, 800, 600, null);
            if (charaSelect == 0) {
                g.drawImage(ImageIO.read(new File("media/chara1-profile.png")), 237, 125, 319, 385, null);
            } else if (charaSelect == 1) {
                g.drawImage(ImageIO.read(new File("media/char2portrait.png")), 237, 125, 319, 385, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //for "buttons"
        int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() + 8);
        int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() + 31);
        if (mouseX > 703 && mouseX < 784 && mouseY > 287 && mouseY < 398) {
            try {
                g.drawImage(ImageIO.read(new File("media/rightarrow-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mouseX > 30 && mouseX < 111 && mouseY > 287 && mouseY < 398) {
            try {
                g.drawImage(ImageIO.read(new File("media/leftarrow-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mouseX > 273 && mouseX < 541 && mouseY > 517 && mouseY < 573) {
            try {
                g.drawImage(ImageIO.read(new File("media/letsgo-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mouseX > 23 && mouseX < 90 && mouseY > 58 && mouseY < 126) {
            try {
                g.drawImage(ImageIO.read(new File("media/back-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reset() {
        obstacles.clear();
        initializeObs();
        obsIndex = 0;
        score = 0;
        oneSixtiethSeconds = 0;
        speed = Obstacle.baseSpeed;
        if (!canJump) {
            c.setY(266);
        }
    }

    public void drawGame(Graphics g) {
        if (!clickStart) {
            reset();
            click.play();
            clickStart = true;
        }
        if (!musicStart) {
            music.play();
            musicStart = true;
        }
        g.setColor(new Color(18, 24, 51));
        g.fillRect(0, 0, 800, 600);
        g.setColor(new Color(88, 91, 107));
        g.fillRect(0, 125, 800, 350);
        //for "buttons"
        int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() + 8);
        int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() + 31);
        try {
            g.drawImage(ImageIO.read(new File("media/back.png")), 0, 0, 800, 600, null);
            g.drawImage(ImageIO.read(new File("media/test.png")), 0, 125, 800, 350, null);
            g.drawImage(ImageIO.read(new File("media/test2.png")), -score*2, 125, 800, 350, null);
            g.drawImage(ImageIO.read(new File("media/test2.png")), 800-score*2, 125, 800, 350, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mouseX > 23 && mouseX < 90 && mouseY > 58 && mouseY < 126) {
            try {
                    g.drawImage(ImageIO.read(new File("media/back-overlay.png")), 0, 0, 800, 600, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //update position of obstacles and character
        obstacles.get(obsIndex).update();
        obstacles.get(obsIndex).draw(g);
        c.update();
        c.draw(g, charaSelect);
        //collisions between character and obstacles
        if (c.collide(obstacles.get(obsIndex))) {
            if (!ouchStart) {
                ouch.play();
                ouchStart = true;
            }
            reset();
            ouchStart = false;
        }
        //read+write high score & drawing score and high score
        int highScore = 0;
        try {
            Scanner sc = new Scanner(new File("scores.txt"));
            highScore = Integer.parseInt(sc.nextLine().trim());
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter f = new FileWriter(new File("scores.txt"), false);
            if (score > highScore) {
                f.write(score + "");
            } else {
                f.write(highScore + "");
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(new Color(18, 24, 51));
        g.drawString("Score: " + score, 30, 500);
        g.drawString("High Score: " + highScore, 30, 520);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 30, 500);
        g.drawString("High Score: " + highScore, 30, 520);
            //character jumping
            if (canJump) {
                if (count < 20) {
                    c.move("up");
                } else if (25 <= count && count < 45) {
                    c.move("down");
                } 
                count++;
                if (count == 45) {
                    count = 0;
                    canJump = false;
                    c.move("reset");
                }
            }
            //updating score and obstacle speed
            oneSixtiethSeconds++;
            songSixtiethSeconds++;
            if (oneSixtiethSeconds%15 == 0) {
                score++;
            }
            if (oneSixtiethSeconds%120 == 0) {
                speed++;
            }
            //update for obstacle index
            if (obstacles.get(obsIndex).getX() < -30) {
                obsIndex++;
            } 
            if (obsIndex == obstacles.size()) {
                obsIndex = 0;
            }
            obstacles.get(obsIndex).setSpeed(speed);
            if (songSixtiethSeconds/60 == 275 && musicStart) {
                music.stop();
                musicStart = false;
                songSixtiethSeconds = 0;
            }
        
    }

    public void paintComponent(Graphics g) {
       if (currentState == 0) {
           drawMenu(g);
       } else if (currentState == 1) {
           drawHelp(g);
       } else if (currentState == 2) {
           drawSelect(g);
       } else if (currentState == 3) {
           drawGame(g);
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && !c.collide(obstacles.get(obsIndex))) {
            if (!c.isCrouching()) {
                canJump = true;
            }
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            canJump = false;
            c.move("crouch");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (c.isCrouching()) {
                c.move("reset");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        //System.out.println("(" + mouseX + ", " + mouseY + ")");
        if (mouseX > 182 && mouseX < 375 && mouseY > 344 && mouseY < 397) {
            if (currentState == 0) {
                currentState = 2;
                clickStart = false;
            }
        }
        if (mouseX > 439 && mouseX < 630 && mouseY > 344 && mouseY < 397) {
            if (currentState == 0) {
                currentState = 1;
                clickStart = false;
            }
        }
        if (mouseX > 273 && mouseX < 541 && mouseY > 517 && mouseY < 573) {
            if (currentState == 2) {
                currentState = 3;
                clickStart = false;
            }
        }
        if (mouseX > 23 && mouseX < 90 && mouseY > 58 && mouseY < 126) {
            currentState = 0;
            clickStart = false;
        }
        if (mouseX > 703 && mouseX < 784 && mouseY > 287 && mouseY < 398) {
            clickStart = false;
            if (currentState == 2) {
                //write choice to file
                try {
                    FileWriter f = new FileWriter(new File("chara.txt"), false);
                    f.write(1 + "");
                    f.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                //play sound
                if (!clickStart) {
                    click.play();
                    clickStart = true;
                }
            }
        }
        if (mouseX > 30 && mouseX < 111 && mouseY > 287 && mouseY < 398) {
            clickStart = false;
            if (currentState == 2) {
                //write choice to file
                try {
                    FileWriter f = new FileWriter(new File("chara.txt"), false);
                    f.write(0 + "");
                    f.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                //play sound
                if (!clickStart) {
                    click.play();
                    clickStart = true;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {        
    }
}
