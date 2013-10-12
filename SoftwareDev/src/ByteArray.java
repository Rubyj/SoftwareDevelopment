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
        		
        		Complex c0 = new Complex(0);
        		Complex c1 = new Complex(1);
        		
        		Complex[] cArray = {c1, c1, c1, c1, c0, c0, c0, c0};
        	
                ByteArray b = new ByteArray();
                byte[] bytesOne = b.toByteArray();
                Complex[] complexOne = b.toComplexArray(bytesOne);
                FFT fft = new FFT();
                
                for (int i = 0; i < cArray.length; i++) {
                	System.out.print(cArray[i].number + ", ");
                }
                
                System.out.println("SEPARATE");
               // Complex[] cArray2 = 
                fft.computeFFT(cArray);
                 
                for (int i = 0; i < cArray.length; i++) {
                	System.out.print("(" + cArray[i].number + ", " + cArray[i].imaginary + ")" + ", ");
                }
        }
        
        public byte[] toByteArray() throws UnsupportedAudioFileException, IOException {
                
                Scanner input = new Scanner(System.in);
                String path = "";
                
                System.out.println("Please enter a file path");
                //Path for the file to analyze
                path = input.nextLine();
                input.close();
                
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
        
        public Complex[] toComplexArray(byte[] byteArray) {
        	Complex[] complexArray = new Complex[byteArray.length];
        	
        	for (int i = 0; i < byteArray.length; i++) {
        		complexArray[i] = new Complex(byteArray[i]);
        	}
        	
        	return complexArray;
        }
}

        