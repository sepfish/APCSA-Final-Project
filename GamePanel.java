import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
    Character c;
    Timer t;

    public GamePanel() {
        t = new Timer(1000/60, this);
        c = new Character();
    }

    public void start() {
        t.start();
    }

    public void paintComponent(Graphics g) {
        c.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
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
}
