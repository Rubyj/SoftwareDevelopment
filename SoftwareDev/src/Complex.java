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
}