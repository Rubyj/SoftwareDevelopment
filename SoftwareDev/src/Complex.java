public class Complex {
	int number;
	char imaginary;
	
	Complex(int number) {
		this.number = number;
	}
	Complex(int number, boolean imaginary) {
		this.number = number;
		if (imaginary) {
			this.imaginary = 'i';
		}
	}
}