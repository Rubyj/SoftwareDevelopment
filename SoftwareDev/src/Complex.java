public class Complex {
	float number;
	float imaginary;
	
	Complex(float number, float imaginary) {
		this.number = number;
		this.imaginary = imaginary;
	}
	//A real number
	Complex(float number) {
		this.number = number;
		this.imaginary = 0;
	}
	
	//Assuming c has only a real part
	public Complex add(Complex c) {
		float tempReal;
		float tempImag;
		tempReal = this.number + c.number;
		tempImag = this.imaginary + c.imaginary;
		return new Complex(tempReal, tempImag);
	}
	
	//Assuming c has only a real part
	public Complex minus(Complex c) {
		float tempReal;
		float tempImag;		
		tempReal = this.number - c.number;
		tempImag = this.imaginary - c.imaginary;
		return new Complex(tempReal, tempImag);
	}
	
	//Assuming c has only a real part
	public Complex times(Complex c) {
		float tempReal = (this.number * c.number) - (this.imaginary * c.imaginary);
		float tempImag = (this.number * c.imaginary + this.imaginary * c.number);
		return new Complex(tempReal, tempImag);
	}
	
	public boolean equals(Object o){
		if (o != null && o instanceof Complex){
		Complex c = (Complex) o;
		return (this.number == c.number) && (this.imaginary == c.imaginary);
		}
		else return false;
	}
	
	public float approxEqual(Complex c) {
		float realScore;
		float imagScore;
		
		if (c != null) {
			if (this.number < c.number) {
				realScore = this.number/c.number;
			} else {
				realScore = c.number/this.number;
			}
			
			if (this.imaginary < c.imaginary) {
				imagScore = this.imaginary/c.imaginary;
			} else {
				imagScore = c.imaginary/this.imaginary;
			}
			
			return (realScore + imagScore)/2;
		}
		return 0;
	}
}