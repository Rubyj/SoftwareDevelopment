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
			
			double thisScore = Math.sqrt(Math.pow(this.number, 2) + Math.pow(this.imaginary, 2));
			double cScore = Math.sqrt(Math.pow(c.number, 2) + Math.pow(c.imaginary, 2));
			
			if (thisScore == 0 && cScore == 0) {
				return 1;
			}
			
			if (thisScore < cScore) {
				return (float)(thisScore/cScore);
			} else {
				return (float)(cScore/thisScore);
			}
			
			
			
			
			/*
			if (Math.abs(this.number) < Math.abs(c.number)) {
				realScore = Math.abs(this.number)/Math.abs(c.number);
			} else {
				realScore = Math.abs(c.number)/Math.abs(this.number);
			}
			
			if (Math.abs(this.imaginary) < Math.abs(c.imaginary)) {
				imagScore = Math.abs(this.imaginary)/Math.abs(c.imaginary);
			} else {
				imagScore = Math.abs(c.imaginary)/Math.abs(this.imaginary);
			}
			
			if (this.number == 0 && c.number == 0) {
				realScore = 1;
			}
			
			if (this.imaginary == 0 && c.imaginary == 0) {
				imagScore = 1;
			}
			
			return (realScore + imagScore)/2;
			*/
		} else {
			return 0;
		}
	}
}