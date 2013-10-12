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
}