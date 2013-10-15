public class FFT {

		FFT() {}
		
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
        
        public float compare(Complex[] c1, Complex[] c2) {
        	
        	Complex[] comp1 = c1;
        	Complex[] comp2 = c2;
        	// This will ensure that whichever order the arguments are passed, comp1 is shorter
        	if (c1.length > c2.length) {
        		comp1 = c2;
        		comp2 = c1;
        	}
        	
        	Complex e1 = comp1[0];
        	
        	// This assumes that a match, 1, occurs only when comp1 is completely contained within comp2
        	float biggestMatch = 0;
        	
        	if (comp2.length - comp1.length == 0) {
        		return this.contains(comp1, comp2, 0);
        	} else {
        		for (int x = 0; x <= comp2.length - comp1.length; x++){
        		
        			if (comp2[x].approxEqual(e1) >= .8){
        				float tempMatch = this.contains(comp1, comp2, x);
        				//System.out.println(tempMatch);
        				if (tempMatch > biggestMatch) {
        					biggestMatch = tempMatch;
        				}
        			}
        		}
        		return biggestMatch;
        	}
        	
        }
        
        public float contains(Complex[] c1, Complex[] c2, int i){
        	float counter = 0;
        	
        	for (int x = 0; x < c1.length; x++){
        		counter += c1[x].approxEqual(c2[x + i]);
        	}        	
        	return counter/c1.length;
        }
}