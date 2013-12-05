import java.util.ArrayList;

public class FFT {
        
        //A copy of the two inputs to shortContains 
        //used for magnitude comparisons
        //Instantiated in shortContains
        byte[] magnitudeArray1;
        byte[] magnitudeArray2;
        
        //The index array
        protected ArrayList<Integer> indexStorage;
        
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
            this.indexStorage = new ArrayList<Integer>();
            
            byte[] comp1 = c1;
            byte[] comp2 = c2;
            
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            float complexScore = 0;
            float tempScore = 0;
            for (int i = 0; i < comp1.length; i++) {
                complexScore = Math.abs(complexScore) + Math.abs(comp1[i]);
                tempScore = Math.abs(tempScore) + Math.abs(comp2[i]);
            }
            
            //storage.add(tempScore);
            
            if (comp1.length == comp2.length) {
                this.indexStorage.add(0);
            } else {
                for (int i = 1; i <= comp2.length - comp1.length; i++) {
                    tempScore = Math.abs(tempScore) - Math.abs(comp2[i - 1]);
                    tempScore = Math.abs(tempScore) + 
                            Math.abs(comp2[i + comp1.length - 1]);
                    
                    if (tempScore == complexScore) {
                        indexStorage.add(i);
                    } else if (tempScore < complexScore) {
                        if (tempScore/complexScore > .99999) {
                            indexStorage.add(i);
                        }
                    } else if (complexScore < tempScore) {
                        if (complexScore/tempScore > .99999) {
                            indexStorage.add(i);
                        }
                    }
                }
            }
            
            //System.out.println(indexStorage);
            return indexStorage;

        }
        
        //Check to see if c1 is approximately contained in c2, starting at
        //each index in the precomputed indexStorage 
        //(similarity score is a % out of 100 in decimal form [i.e. .4])
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
                this.indexStorage = 
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
                
                this.indexStorage = 
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
        
        //Method to compare the FFT of a segment of longer input 
        //to the FFT of shorter input
        //NOT CURRENTLY IN USE
        public double longContains(Complex[] c1, Complex[] c2){
            
            Complex[] complexOne = c1;
            Complex[] complexTwo = c2;
            
            if (c1.length > c2.length) {
                complexOne = c2;
                complexTwo = c1;
            }
            
            this.computeFFT(complexOne);
            
            double biggestPerc = 0;
            //System.out.println(indexStorage.size());
            for (int index : this.indexStorage) {
                
                //System.out.println("c1: " + complexOne.length + ", 
                    //c2 length: "+ complexTwo.length);
                
                if (complexOne.length > complexTwo.length){
                    //System.out.println("Abort!");
                    return biggestPerc;
                }
                
                Complex[] tempArray = new Complex[complexOne.length];
                
                for (int i = index; i < complexOne.length + index; i++) {
                    tempArray[i - index] = complexTwo[i];
                }
                this.computeFFT(tempArray);
                
                int i = 0;
                double score = 0;
                
                for (Complex freqValue : tempArray) {
                    
                    double freqMag = freqValue.magnitude();
                    double compMag = complexOne[i].magnitude();

                    if (freqMag < compMag) {
                        if (freqMag/compMag > .9999) {
                            score += 1;
                        }
                    } else {
                        if (freqMag/compMag > .9999) {
                            score += 1;
                        }
                    }
                    i++;
                }
                
                double avgPerc = score/tempArray.length;
                
                //System.out.println("score: " + avgPerc);
                if(avgPerc > biggestPerc){
                    biggestPerc = avgPerc;
                }
                
            }
            
            return biggestPerc;
            
        }
}