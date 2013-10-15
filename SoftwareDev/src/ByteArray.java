import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


public class ByteArray {
        
        public byte[] toByteArray(String path) {
                
        		byte[] audioBytes = null;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                AudioInputStream in;
				try {
					in = AudioSystem.getAudioInputStream(new File(path));
	                int read;
	                byte[] buff;
	                buff = new byte[1024];
	                 
	                while ((read = in.read(buff)) > 0) {
	                        out.write(buff, 0, read);
	                }
	                 
	                out.flush();
	                audioBytes = out.toByteArray();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR: Unsupported Audio File Format Detected. Exiting..");
					System.exit(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ERROR: File Not Found. Exiting..");
					System.exit(1);
				}
				
				return audioBytes;
        }
        
        public Complex[] toComplexArray(byte[] byteArray) {
        	Complex[] complexArray = new Complex[byteArray.length];
        	
        	for (int i = 0; i < byteArray.length; i++) {
        		complexArray[i] = new Complex(byteArray[i]);
        	}
        	
        	return complexArray;
        }
}

        