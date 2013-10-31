import java.util.ArrayList;

public class FFT {

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
                Complex cNumber = new Complex((float)Math.cos(-2 * Math.PI * i / N), (float)Math.sin(-2 * Math.PI * i / N));
                cNumber = cNumber.times(oddArray[i]);
                complexArr[i] = cNumber.add(evenArray[i]);
                complexArr[i + N/2] = evenArray[i].minus(cNumber);
            }
            
        }
        
        public ArrayList<Integer> shortCompare(Complex[] c1, Complex[] c2) {
            
            ArrayList<Complex> storage = new ArrayList<Complex>();
            ArrayList<Integer> indexStorage = new ArrayList<Integer>();
            
            Complex[] comp1 = c1;
            Complex[] comp2 = c2;
            
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            Complex complexScore = new Complex(0, 0);
            Complex tempScore = new Complex(0, 0);
            for (int i = 0; i < comp1.length; i++) {
                complexScore = complexScore.add(comp1[i]);
                tempScore = tempScore.add(comp2[i]);
            }
            storage.add(tempScore);
            
            for (int i = 1; i <= comp2.length - comp1.length; i++) {
                tempScore = tempScore.minus(comp2[i - 1]);
                tempScore = tempScore.add(comp2[i + comp1.length - 1]);
                storage.add(tempScore);
            }
            
            //DO WE NEED TO IMPLEMENT ITEMS PAST COMP2LENGTH - COMP1LENGTH IN COMP2
//            for (int i = comp2.length - comp1.length; i < comp2.length; i ++) {
//                tempScore = tempScore.minus(comp2[i - 1]);
//                storage.add(tempScore);
//            }
            
            float tempAVG;
            for (int i = 0; i < storage.size(); i ++) {
                tempAVG = complexScore.approxEqual(storage.get(i));
                tempAVG = tempAVG/comp1.length;
                if (tempAVG < .3) {
                    System.out.println(tempAVG);
                    indexStorage.add(i);
                    System.out.println(i);
                }
            }
            System.out.println(storage.size());
            return indexStorage;
        }
        
//        //Compare two complex arrays
//        public float longCompare(Complex[] c1, Complex[] c2) {
//            
//            Complex[] comp1 = c1;
//            Complex[] comp2 = c2;
//            // This will ensure that whichever order the arguments are passed, comp1 is shorter
//            if (c1.length > c2.length) {
//                comp1 = c2;
//                comp2 = c1;
//            }
//            
//            Complex e1 = comp1[0];
//            
//            float smallestMatch = 100000;
//            int comp1Length = comp1.length;
//            int comp2Length = comp2.length;
//            
//            //Compare first element of comp1 to elements in comp2
//            //If match see if rest of comp1 is contained in comp2 at that location
//            //Return greatest similarity of matches found
//            if (comp2Length == comp1Length) {
//                return this.longContains(comp1, comp2, 0);
//            } else {
//                for (int x = 0; x <= comp2Length - comp1Length; x++){
//                        float tempMatch = this.longContains(comp1, comp2, x);
//                        if (tempMatch < smallestMatch) {
//                            smallestMatch = tempMatch;
//                        }
//                }
//                System.out.println(smallestMatch);
//                return smallestMatch;
//            }
//            
//        }
        
        //Check to see if c1 is approximately contained in c2, starting at i
        //Return the summation of the similarity scores divided by length of c1
        public float shortContains(Complex[] c1, Complex[] c2){
            Complex[] comp1 = c1;
            Complex[] comp2 = c2;
            
            if (c1.length == c2.length) {
                System.out.println(comp2.length);
                
                float counter = 0;
                    for (int x = 0; x < comp1.length; x++) {
                        counter += comp1[x].approxEqual(comp2[x]);
                    }
                    counter = counter/comp1.length;
                
                return counter; 
            }
            
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            ArrayList<Integer> indexStorage = this.shortCompare(comp1, comp2);
            System.out.println(comp2.length);
            
            float counter;
            float smallestMatch = 50000;
            for (int j = 0; j < indexStorage.size(); j++) {
                counter = 0;
                for (int x = 0; x < comp1.length; x++) {
                    //System.out.println(comp1[x].approxEqual(comp2[x + indexStorage.get(j)]));
                    counter += comp1[x].approxEqual(comp2[x + indexStorage.get(j)]);
                }
                counter = counter/comp1.length;
                System.out.println(counter);
                if (counter < smallestMatch) {
                    smallestMatch = counter;
                }
            }
            return smallestMatch;
        }
}