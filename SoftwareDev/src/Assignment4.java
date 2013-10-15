import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Assignment4{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, Exception {
		
		if (args.length > 2) {
			throw new Exception("This Program Only Supports 2 files. Exiting..");
		}
	
		String path1 = args[0];
		String path2 = args[1];
	
		/* Test Data
		Complex c0 = new Complex(0);
		Complex c1 = new Complex(1);
		Complex[] cArray = {c1, c1, c1, c1, c0, c0, c0, c0};
        */
		
		//Compute two inputs to ByteArrays
        ByteArray b1 = new ByteArray();
        byte[] bytesOne = b1.toByteArray(path1);
        Complex[] complexOne = b1.toComplexArray(bytesOne);
        
        ByteArray b2 = new ByteArray();
        byte[] bytesTwo = b2.toByteArray(path2);
        Complex[] complexTwo = b2.toComplexArray(bytesTwo);
        
        FFT fft = new FFT();
        fft.computeFFT(complexOne);
        fft.computeFFT(complexTwo);
        
        float result = fft.compare(complexOne, complexTwo);
        
        if (result >= .5) {
        	System.out.println("Match " + result * 100 + "% Similarity Score");
        } else {
        	System.out.println("No Match " + result * 100 + "% Similarity Score");
        }
        System.exit(0);
    }
}