public class FFT {
    
        public byte[] computeFFT(byte[] byteArr) {
                int N = byteArr.length;

            // base case
            if (N == 1) { 
                return new byte[] { byteArr[0] };
            }

            // radix 2 Cooley-Tukey FFT
            if (N % 2 != 0) { 
                throw new RuntimeException("N is not a power of 2"); 
            }
            
            byte[] even = new byte[N/2];
            for (int i = 0; i < N/2; i++) {
                even[i] = byteArr[2*i];
            }
            byte[] evenArray = computeFFT(even);
            
            byte[] odd = new byte[N/2];
            for (int i = 0; i < N/2; i++) {
                odd[i] = byteArr[2*i + 1];
            }
            byte[] oddArray = computeFFT(even);
            
            byte[] finalBytes = new byte[N/2];
            for (int i = 0; i < N/2; i++) {
                double k = -2 * i * Math.PI / N;
                //Do stuff here
            }
            
            return finalBytes;
                
        }
}