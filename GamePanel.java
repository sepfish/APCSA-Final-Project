import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Timer;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.MouseInfo;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
    Character c;
    Timer t;
    int currentState;
    AdvancedPlayer buttonClick;
    Song pain;
    boolean painStart;

    int count = 0;
    boolean canJump = false;

    public GamePanel() {
        t = new Timer(1000/60, this);
        c = new Character();
        c.setY(240);
        currentState = 0;
        loadSongs();
        painStart = false;
        pain = new Song("media/water-click.mp3");
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
        int mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
        int mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();
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
        g.setColor(new Color(200, 210, 255));
        g.fillRect(0, 0, 800, 600);
    }

    public void drawGame(Graphics g) {
        if (!painStart) {
            pain.play();
            painStart = true;
        } else {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 150, 800, 300);
        
        c.update();
        c.draw(g);

        if (canJump) {
            if (count < 16) {
                c.move("up");
            } else if (count < 32) {
                c.move("down");
            } 
            count++;
            if (count == 32) {
                count = 0;
                canJump = false;
            }
        }
    }
    }

    public void paintComponent(Graphics g) {
       if (currentState == 0) {
           drawMenu(g);
       } else if (currentState == 1) {
           drawHelp(g);
       } else if (currentState == 2) {
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
            }
        }
        if (mouseX > 439 && mouseX < 630 && mouseY > 344 && mouseY < 397) {
            if (currentState == 0) {
                currentState = 1;
            }
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
