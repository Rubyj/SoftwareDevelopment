import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

public class Assignment8{
    
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        
        String song1 = "";
        String song2 = "";
        
        //If program is provided more/less than 2 arguments throw error
        if (args.length == 1) {
            if (args[0].equals("file")) {
                System.err.println("ERROR: Invalid file");
                System.exit(1);
            } else if (args[0].equals("directory")) {
                System.err.println("ERROR: Invalid directory");
                System.exit(1);
            } else if (args[0].equals("file/directory")) {
                System.err.println("ERROR: Invalid input");
                System.exit(1);
            }
        }
        
        
        if (args.length != 4) {
            System.err.println("ERROR: This Program Only "
                    + "Supports 2 files or directories. Exiting..");
            System.exit(1);
        }
        
        song1 = args[2];
        song2 = args[3];
        
        String[] shortSong1 = song1.split("/");
        String[] shortSong2 = song2.split("/");
        song1 = shortSong1[shortSong1.length - 1];
        song2 = shortSong2[shortSong2.length - 1];
        
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
        if (bytesOne.length <= bytesTwo.length) {
            shortName = song1;
            longName = song2;
        } else {
            shortName = song2;
            longName = song1;
        }
        
        //If result greater than 50% print match else print no match
        if (result >= .6) {
            System.out.println("MATCH " + shortName + " " + longName);
            System.exit(0);
        } else if (result >= .4 && result < .6) {
            double simScore = fft.longContains(complexOne, complexTwo);
            if (simScore >= .7) {
                System.out.println("MATCH " + shortName + " " + longName + simScore);
                System.exit(0);
            } else {
                System.out.println("NO MATCH " + simScore);
                System.exit(0);
            }
        } else {
            //System.out.println(result);
            System.out.println("NO MATCH");
            System.exit(0);
        }
    }
}