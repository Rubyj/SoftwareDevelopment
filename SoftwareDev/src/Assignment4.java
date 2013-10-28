import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Assignment4{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        
        //If program is provided more/less than 2 arguments throw error
        if (args.length != 2) {
            System.err.println("ERROR: This Program Only Supports 2 files. Exiting..");
            System.exit(1);
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
        
        //Compute the FFT of the two Complex[] and store into result
        FFT fft = new FFT();
        fft.computeFFT(complexOne);
        fft.computeFFT(complexTwo);
        
        float result = fft.compare(complexOne, complexTwo);
        
        //If result greater than 50% print match else print no match (with similarity score)
        if (result >= .5) {
            System.out.println("MATCH"); // + result * 100 + "% Similarity Score");
            System.exit(0);
        } else {
            System.out.println("NO MATCH"); // + result * 100 + "% Similarity Score");
            System.exit(0);
        }
    }
}