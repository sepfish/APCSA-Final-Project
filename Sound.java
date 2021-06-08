import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Sound {
	private String soundSource;
	private AdvancedPlayer soundPlayer;
	private InputStream soundStream;

	public Sound(String s) {
		soundSource = s;
	}

	public void play() {
		try {
			this.soundStream = new FileInputStream(soundSource);
		} catch (FileNotFoundException e) {
			this.soundStream = this.getClass().getResourceAsStream(soundSource);
		}
		if (soundStream != null) {
			try {
				this.soundPlayer = new AdvancedPlayer(soundStream);
			} catch (Exception e) {

			}
			Thread t = new Thread() {
				public void run() {
					try {
						soundPlayer.play();
					} catch (Exception e) {
						
					}
				}
			};
			t.start();
			System.out.println("Playing " + soundSource);
		} else
			System.err.println("Unable to find " + soundSource);
	}

	public String getSourceName() {
		return soundSource;
	}
	
	public void setSource(String s) {
		soundSource = s;
	}

	public void stop() {
		if (soundPlayer != null) {
			soundPlayer.close();
		}
	}
}