import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ByteArray {
        
        
        public static void main(String[] args) throws UnsupportedAudioFileException, IOException, Exception {
        		
        		if (args.length > 2) {
        			throw new Exception("This Program Only Supports 2 files. Exiting..");
        		}
        	
        		String path1 = args[0];
        		String path2 = args[1];
        	
        		//Test Data
        		Complex c0 = new Complex(0);
        		Complex c1 = new Complex(1);
        		Complex[] cArray = {c1, c1, c1, c1, c0, c0, c0, c0};
                
                ByteArray b1 = new ByteArray();
                byte[] bytesOne = b1.toByteArray(path1);
                Complex[] complexOne = b1.toComplexArray(bytesOne);
                ByteArray b2 = new ByteArray();
                byte[] bytesTwo = b2.toByteArray(path2);
                Complex[] complexTwo = b2.toComplexArray(bytesTwo);
                
                FFT fft = new FFT();
                fft.computeFFT(complexOne);
                fft.computeFFT(complexTwo);
                
                System.out.print(fft.compare(complexOne, complexTwo) * 100 + "% Similarity Score");
                System.exit(0);
        }
        
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

        