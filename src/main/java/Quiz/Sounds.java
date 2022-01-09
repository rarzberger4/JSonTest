package Quiz;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sounds {

    private final List<File> sounds = new ArrayList<>();

    public Sounds() {
        sounds.add(new File("mixkit-positive-notification-951.wav"));
        sounds.add(new File("mixkit-ominous-drums-227.wav"));
    }

    public static void playSound(File s) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(s);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            Thread.sleep(400);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void playPosSound() {
        playSound(sounds.get(0));
    }

    public void playNegSound() {
        playSound(sounds.get(1));
    }

}
