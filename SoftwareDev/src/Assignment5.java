import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Assignment5{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        
        //If program is provided more/less than 2 arguments throw error
        if (args.length != 2) {
            System.err.println("ERROR: This Program Only "
                    + "Supports 2 files. Exiting..");
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
        //PROGRAM SEEMS TO WORK BETTER WITHOUT COMPUTING THE FFT
        //AND RUNNING COMPARISON ON BYTE ARRAY
        FFT fft = new FFT();
        //fft.computeFFT(complexOne);
        //fft.computeFFT(complexTwo);
        
        float result = fft.shortContains(bytesOne, bytesTwo);
        //System.out.println(result);
        
        String shortName;
        String longName;
        if (bytesOne.length < bytesTwo.length) {
            shortName = b1.songName;
            longName = b2.songName;
        } else {
            shortName = b2.songName;
            longName = b1.songName;
        }
        
        //If result greater than 50% print match else print no match
        if (result >= .6) {
            System.out.println("MATCH " + shortName + " " + longName);
            System.exit(0);
        } else if (result >= .4 && result < .6) {
            double simScore = fft.longContains(complexOne, complexTwo);
            if (simScore <= 10000){
                System.out.println("MATCH: " + simScore);
                System.exit(0);
            } else {
                System.out.println("NO MATCH:" + simScore);
                System.exit(0);
            }
        } else {
            System.out.println(result);
            System.out.println("NO MATCH");
            System.exit(0);
        }
    }
}