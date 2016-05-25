
package rompecabezas;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Salvador
 */
public class Reproductor {
    
    URL url;
    AudioInputStream audioIn;
    Clip clip;
    Clip clip2;
    
    public Reproductor(){
         try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/audios/audio.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {  }
         
    }
    
    public void Play(){
        clip.start();
    }
    
    public void Click(){
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/audios/click.wav"));
            clip2 = AudioSystem.getClip();
            clip2.open(audioIn);
            clip2.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {  }
    }
    public void Bloqueo(){
        
    }
    public void Tada(){
        try {
            audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/audios/tada.wav"));
            clip2 = AudioSystem.getClip();
            clip2.open(audioIn);
            clip2.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {  }
    }
        
    
}
