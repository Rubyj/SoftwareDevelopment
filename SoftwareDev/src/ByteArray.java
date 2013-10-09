import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ByteArray {
        
        
        public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
                ByteArray b = new ByteArray();
                byte[] bytesOne = b.toByteArray();
        }
        
        public byte[] toByteArray() throws UnsupportedAudioFileException, IOException {
                
                Scanner input = new Scanner(System.in);
                String path = "";
                
                System.out.println("Please enter a file path");
                //Path for the file to analyze
                path = input.nextLine();
                
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                AudioInputStream in = AudioSystem.getAudioInputStream(new File(path));
                 
                int read;
                byte[] buff;
                buff = new byte[1024];
                 
                while ((read = in.read(buff)) > 0) {
                        out.write(buff, 0, read);
                }
                 
                out.flush();
                byte[] audioBytes = out.toByteArray();
                return audioBytes;

        }
}

        