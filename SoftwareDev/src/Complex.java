//Class to represent complex numbers
public class Complex {
    float number;
    float imaginary;
    
    Complex(float number, float imaginary) {
        this.number = number;
        this.imaginary = imaginary;
    }
    
    //A complex number with no imag part
    Complex(float number) {
        this.number = number;
        this.imaginary = 0;
    }
    
    //Adding 2 complex numbers
    public Complex add(Complex c) {
        float tempReal;
        float tempImag;
        tempReal = this.number + c.number;
        tempImag = this.imaginary + c.imaginary;
        return new Complex(tempReal, tempImag);
    }
    
    //Subtracting 2 complex numbers
    public Complex minus(Complex c) {
        float tempReal;
        float tempImag;        
        tempReal = this.number - c.number;
        tempImag = this.imaginary - c.imaginary;
        return new Complex(tempReal, tempImag);
    }
    
    //Multiplying 2 complex numbers
    public Complex times(Complex c) {
        float tempReal = (this.number * c.number) - 
                (this.imaginary * c.imaginary);
        float tempImag = (this.number * c.imaginary + 
                this.imaginary * c.number);
        return new Complex(tempReal, tempImag);
    }
    
    //If two complexes are equal
    public boolean equals(Object o){
        if (o != null && o instanceof Complex){
        Complex c = (Complex) o;
        return (this.number == c.number) && (this.imaginary == c.imaginary);
        }
        else return false;
    }
    
    //Method that returns the percentage equality of 2 complex numbers
    public float approxEqual(Complex c) {
        //float realScore;
        //float imagScore;
        
        if (c != null) {
            

            Double score = Math.sqrt(Math.pow(c.number - this.number, 2) + 
                    Math.pow(c.imaginary - this.imaginary, 2));
            return (float)(score/1);

            
        } else {
            return 0;
        }
    }
    
    //Compute the magnitude of this complex
    public Double magnitude() {
        if (this != null) {
            Float temp = ((this.number * this.number) + 
                    (this.imaginary * this.imaginary));
            double dTemp = Math.sqrt(temp.doubleValue());
            return dTemp;
        }
        
        return (double)0;
    }
}