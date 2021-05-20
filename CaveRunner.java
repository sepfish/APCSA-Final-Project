import java.awt.Dimension;

import javax.swing.JFrame;

public class CaveRunner {

    private JFrame frame;
	private GamePanel gamePanel;

    public CaveRunner() {
        frame = new JFrame();
        gamePanel = new GamePanel();
    }

    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamePanel);
		frame.setVisible(true);
		frame.getContentPane().setPreferredSize(new Dimension(800, 600));
        frame.addKeyListener(gamePanel);
        frame.setTitle("Cave Runner");
        frame.pack();
    }

    public static void main(String... getReady) {
        CaveRunner c = new CaveRunner();
        c.start();
    }
}
