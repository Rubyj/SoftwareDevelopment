import java.util.ArrayList;

public class FFT {
        
        byte[] magnitudeArray1;
        byte[] magnitudeArray2;
        
        FFT() {}
        
        //Function to compute FFT of given Complex[]
        public void computeFFT(Complex[] complexArr) {
            int N = complexArr.length;
            
            if (N <= 1) {
                return;
            }
            
            Complex[] evenArray = new Complex[N/2];
            Complex[] oddArray = new Complex[N/2];
            
            for (int i = 0; i < N/2; i++) {
                evenArray[i] = new Complex(complexArr[2 * i].number);
                oddArray[i] = new Complex(complexArr[(2 * i) + 1].number);
            }
            
            computeFFT(oddArray);
            computeFFT(evenArray);
            
            for (int i = 0; i < N/2; i++) {
                Complex cNumber = new Complex((float)Math.cos(-2 * Math.PI 
                        * i / N), 
                        (float)Math.sin(-2 * Math.PI * i / N));
                cNumber = cNumber.times(oddArray[i]);
                complexArr[i] = cNumber.add(evenArray[i]);
                complexArr[i + N/2] = evenArray[i].minus(cNumber);
            }
            
        }
        
        //Builds a list of likely indexes that match for the two given inputs
        public ArrayList<Integer> shortCompare(byte[] c1, byte[] c2) {
            
            //ArrayList<Float> storage = new ArrayList<Float>();
            ArrayList<Integer> indexStorage = new ArrayList<Integer>();
            
            byte[] comp1 = c1;
            byte[] comp2 = c2;
            
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            float complexScore = 0;
            float tempScore = 0;
            for (int i = 0; i < comp1.length; i++) {
                complexScore = complexScore + comp1[i];
                tempScore = tempScore + comp2[i];
            }
            
            //storage.add(tempScore);
            
            if (comp1.length == comp2.length) {
                indexStorage.add(0);
            } else {
                for (int i = 1; i <= comp2.length - comp1.length; i++) {
                    tempScore = tempScore - comp2[i - 1];
                    tempScore = tempScore + comp2[i + comp1.length - 1];
                    
                    if ((Math.sqrt(Math.pow(tempScore - complexScore, 2))/
                            comp1.length) < 0.00000001) {
                        //storage.add(tempScore);
                        indexStorage.add(i);
                    }
                }
            }
            
            return indexStorage;

        }
        
        //Check to see if c1 is approximately contained in c2, starting at
        //each index in the precomputed indexStorage 
        //(similarity score is a % out of 100)
        public float shortContains(byte[] c1, byte[] c2){
            byte[] comp1 = c1;
            byte[] comp2 = c2;
            
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            magnitudeArray1 = comp1.clone();
            magnitudeArray2 = comp2.clone();
            
            if (comp1.length == comp2.length) {
                ArrayList<Integer> indexStorage = 
                        this.shortCompare(comp1, comp2);
                
                //System.out.println(indexStorage);
                
                float counter;
                float smallestMatch = 0;
                for (int j = 0; j < indexStorage.size(); j++) {
                    counter = 0;
                    for (int x = 0; x < comp1.length; x++) {
                        //System.out.println(comp1[x].approxEqual(comp2[x + 
                                                //indexStorage.get(j)]));
                        
                        Double comp1MagX = Math.sqrt(this.magnitudeArray1[x] 
                                * this.magnitudeArray1[x]); 
                        Double comp2MagX = Math.sqrt(this.magnitudeArray2[x + 
                            indexStorage.get(j)] * this.magnitudeArray2[x + 
                                indexStorage.get(j)]);
                        
                        if (comp1MagX < comp2MagX) {
                            if (comp1MagX/comp2MagX >= .6) {
                                counter += 1;
                            }
                        } else {
                            if (comp2MagX/comp1MagX >= .6) {
                                counter += 1;
                            }
                            //counter += comp2MagX/comp1MagX;
                        }
                        //counter = comp1[x].approxEqual(comp2[x + 
                                                   //indexStorage.get(j)]);
                        //System.out.println(counter);
                    }
                    counter = counter/comp1.length;
                   // System.out.println(counter);
                    if (counter > smallestMatch) {
                        smallestMatch = counter;
                    }
                }
                return smallestMatch;
            
            } else {
                
                ArrayList<Integer> indexStorage = 
                        this.shortCompare(comp1, comp2);
            
                //System.out.println(indexStorage);
                //System.out.println(comp2.length);
                //System.out.println(comp1.length);
                
                float counter;
                float smallestMatch = 0;
                for (int j = 0; j < indexStorage.size(); j++) {
                    counter = 0;
                    for (int x = 0; x < comp1.length; x++) {
                        //System.out.println(comp1[x].approxEqual(comp2[x + 
                                                     //indexStorage.get(j)]));
                        Double comp1MagX = Math.sqrt(this.magnitudeArray1[x] * 
                                this.magnitudeArray1[x]); 
                        Double comp2MagX = Math.sqrt(this.magnitudeArray2[x + 
                               indexStorage.get(j)] * this.magnitudeArray2[x + 
                                    indexStorage.get(j)]);
                        if (comp1MagX < comp2MagX) {
                            if (comp1MagX/comp2MagX > .43) {
                                counter += 1;
                            }
                        } else {
                            if (comp2MagX/comp1MagX > .43) {
                                counter += 1;
                            }
                            //counter += comp2MagX/comp1MagX;
                        }
                        //counter = comp1[x].approxEqual(comp2[x + 
                                                   //indexStorage.get(j)]);
                        //System.out.println(counter);
                    }
                    counter = counter/comp1.length;
                   // System.out.println(counter);
                    if (counter > smallestMatch) {
                        smallestMatch = counter;
                    }
                }
                return smallestMatch;
            }
        }
}