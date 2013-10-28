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
        
        //Compare two complex arrays
        public float compare(Complex[] c1, Complex[] c2) {
            
            Complex[] comp1 = c1;
            Complex[] comp2 = c2;
            // This will ensure that whichever order the arguments are passed, comp1 is shorter
            if (c1.length > c2.length) {
                comp1 = c2;
                comp2 = c1;
            }
            
            Complex e1 = comp1[0];
            
            float smallestMatch = 100000;
            
            //Compare first element of comp1 to elements in comp2
            //If match see if rest of comp1 is contained in comp2 at that location
            //Return greatest similarity of matches found
            if (comp2.length - comp1.length == 0) {
                float temp = this.contains(comp1, comp2, 0);
                System.out.println(temp);
                return temp;
            } else {
                for (int x = 0; x <= comp2.length - comp1.length; x++){
                    //if (comp2[x].approxEqual(e1) <= (float)30000) {
                        float tempMatch = this.contains(comp1, comp2, x);
                        if (tempMatch < smallestMatch) {
                            smallestMatch = tempMatch;
                        }
                    //}
                }
                System.out.println(smallestMatch);
                return smallestMatch;
            }
            
        }
        
        //Check to see if c1 is approximately contained in c2, starting at i
        //Return the summation of the similarity scores divided by length of c1
        public float contains(Complex[] c1, Complex[] c2, int i){
            float counter = 0;
            
            for (int x = 0; x < c1.length; x++){
                float tempVal = c1[x].approxEqual(c2[x + i]);
                //if (tempVal >= .5) {
                    counter += tempVal;
                //}
            }
            return counter/c1.length;
        }
}