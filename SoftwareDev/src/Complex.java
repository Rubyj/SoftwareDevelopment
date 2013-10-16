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
		float tempReal = (this.number * c.number) - (this.imaginary * c.imaginary);
		float tempImag = (this.number * c.imaginary + this.imaginary * c.number);
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
		
		if (c != null) {
			
			//Calculate a score for the similarities between Complex numbers
			Double thisScore = Math.sqrt(Math.pow(this.number, 2) + Math.pow(this.imaginary, 2));
			Double cScore = Math.sqrt(Math.pow(c.number, 2) + Math.pow(c.imaginary, 2));
			
			if (thisScore == 0 && cScore == 0) {
				return 1;
			}
			
			if (thisScore < cScore) {
				return (float)(thisScore/cScore);
			} else {
				return (float)(cScore/thisScore);
			}
			
		} else {
			return 0;
		}
	}
}