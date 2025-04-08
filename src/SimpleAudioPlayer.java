import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;
    private String name;

    public SimpleAudioPlayer(String file, String name) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.name = name;
    }


    // plays sound once
    public void playOnce() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.close();
        resetAudioStream();
        clip.start(); // play sound
        status = "play";
    }

    public void playOnce1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.close();
        resetAudioStream1();
        clip.start(); // play sound
        status = "play";
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File("img\\" + name + "_sound.wav").getAbsoluteFile());
        clip.open(audioInputStream);
    }

    public void resetAudioStream1() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File("img\\1-09 - Battle! (Wild Pokémon).wav").getAbsoluteFile());
        clip.open(audioInputStream);
    }

}

