import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.Timer;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.MouseInfo;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    //game objects
    Character c;
    Timer t;
    AdvancedPlayer buttonClick;
    Song temp;
    Obstacle o;
    int score;

    //other things
    boolean tempStart;
    int currentState;
    int count = 0;
    boolean canJump;
    int oneSixtiethSeconds;

    public GamePanel() {
        t = new Timer(1000/60, this);
        c = new Character();
        c.setY(265);
        currentState = 0;
        loadSongs();
        tempStart = true;
        canJump = false;
        temp = new Song("media/water-click.mp3");
        o = new Obstacle(800, 395, Obstacle.baseSpeed);
        score = 0;
    }

    public void start() {
        t.start();
    }

    private void loadSongs() {
        InputStream sound1 = null;
        try {
			sound1 = new FileInputStream("media/water-click.mp3");
		} catch (FileNotFoundException e) {
			sound1 = this.getClass().getResourceAsStream("media/water-click.mp3");
		}
        try {
			buttonClick = new AdvancedPlayer(sound1);
		} catch (Exception e) {
            e.printStackTrace();
		}
    }

    private void playSound(AdvancedPlayer a, int duration) {
		Thread t = new Thread() {
			public void run() {
				try {
					if (duration > 0)
						a.play(duration);
					else
						a.play();
				} catch (Exception e) {
				}
			}
		};
		t.start();
	}


    public void drawMenu(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("media/menu.png")), 0, 0, 800, 600, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
        if (!tempStart) {
            temp.play();
            tempStart = true;
        }
        g.setColor(new Color(200, 210, 255));
        g.fillRect(0, 0, 800, 600);
    }

    public void drawSelect(Graphics g) {
        if (!tempStart) {
            temp.play();
            tempStart = true;
        }
        try {
            g.drawImage(ImageIO.read(new File("media/chara-select-alt.png")), 0, 0, 800, 600, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public void drawGame(Graphics g) {
        if (!tempStart) {
            temp.play();
            tempStart = true;
        } else {
            g.setColor(new Color(38, 44, 71));
            g.fillRect(0, 0, 800, 600);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 125, 800, 350);
            int mouseX = (int)(MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX() + 8);
            int mouseY = (int)(MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY() + 31);
            try {
                g.drawImage(ImageIO.read(new File("media/back.png")), 0, 0, 800, 600, null);
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
            o.update();
            o.draw(g);

            c.update();
            c.draw(g);

            if (c.collide(o)) {
                score = 0;
                o.reset();
            }

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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

            g.setColor(new Color(38, 44, 71));
            g.drawString("Score: " + score, 30, 500);
            g.drawString("High Score: " + highScore, 30, 520);
            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 30, 500);
            g.drawString("High Score: " + highScore, 30, 520);

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
            oneSixtiethSeconds++;
            if (oneSixtiethSeconds%15 == 0) {
                score++;
            }
            if (oneSixtiethSeconds%120 == 0) {
                o.setSpeed(o.getSpeed()+1);
            }
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            canJump = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            c.move("down");
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (currentState == 0) {
                currentState = 2;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println("(" + mouseX + ", " + mouseY + ")");
        if (mouseX > 182 && mouseX < 375 && mouseY > 344 && mouseY < 397) {
            if (currentState == 0) {
                currentState = 2;
                tempStart = false;
            }
        }
        if (mouseX > 439 && mouseX < 630 && mouseY > 344 && mouseY < 397) {
            if (currentState == 0) {
                currentState = 1;
                tempStart = false;
            }
        }
        if (mouseX > 273 && mouseX < 541 && mouseY > 517 && mouseY < 573) {
            if (currentState == 2) {
                currentState = 3;
                tempStart = false;
            }
        }
        if (mouseX > 23 && mouseX < 90 && mouseY > 58 && mouseY < 126) {
            currentState = 0;
            tempStart = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
