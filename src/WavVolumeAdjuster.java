import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class WavVolumeAdjuster {
    public static void adjustVolume(String inputFilePath, String outputFilePath, float volumeFactor) throws IOException, UnsupportedAudioFileException {
        // Step 1: Read the audio file
        File inputFile = new File(inputFilePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputFile);

        // Step 2: Get audio format and convert audio stream to bytes
        AudioFormat format = audioInputStream.getFormat();
        byte[] audioBytes = audioInputStream.readAllBytes();

        // Step 3: Adjust volume by modifying the sample values
        for (int i = 0; i < audioBytes.length; i++) {
            audioBytes[i] = (byte) Math.min(255, Math.max(0, audioBytes[i] * volumeFactor));
        }

        // Step 4: Write the modified audio data back to a new file
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(audioBytes);
        AudioInputStream modifiedAudioStream = new AudioInputStream(byteInputStream, format, audioBytes.length / format.getFrameSize());
        File outputFile = new File(outputFilePath);
        AudioSystem.write(modifiedAudioStream, AudioFileFormat.Type.WAVE, outputFile);
    }
}
