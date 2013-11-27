import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ByteArray {
    
        protected String songName;
        
        //Method to covert given wav (by file path) to an array of Bytes
        public byte[] toByteArray(String path) {
                
                byte[] audioBytes = null;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                AudioInputStream in;
                try {
                    File f = new File(path);
                    this.songName = f.getName();
                    in = AudioSystem.getAudioInputStream(f);
                    int read;
                    byte[] buff;
                    buff = new byte[1024];
                     
                    while ((read = in.read(buff)) > 0) {
                            out.write(buff, 0, read);
                    }
                    
                    in.close();
                    out.flush();
                    audioBytes = out.toByteArray();
                    out.close();
                } catch (UnsupportedAudioFileException e) {
                    // If Unsupported File Type
                    System.err.println("ERROR: Unsupported Audio File "
                            + "Format Detected. Exiting..");
                    System.exit(1);
                } catch (IOException e) {
                    // If File Not Found
                    System.err.println("ERROR: File Not Found. Exiting..");
                    System.exit(1);
                }
                
                return audioBytes;
        }
        
        //Transform the array of Bytes to an array of Complex
        public Complex[] toComplexArray(byte[] byteArray) {
            Complex[] complexArray = new Complex[byteArray.length];
            
            for (int i = 0; i < byteArray.length; i++) {
                complexArray[i] = new Complex(byteArray[i]);
            }
            
            return complexArray;
        }
}

        
