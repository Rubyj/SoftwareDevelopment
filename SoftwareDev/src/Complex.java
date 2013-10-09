public class Complex {
	int number;
	int imaginary;
	
	Complex(int number, int imaginary) {
		this.number = number;
		this.imaginary = imaginary;
	}
	//A real number
	Complex(int number) {
		this.number = number;
		this.imaginary = 0;
	}
}